package com.yc.snacknet.websocket;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

import com.yc.snacknet.util.StringUtil;

@Component
@ServerEndpoint("/websocket/{id}") 
public class WebServerSocket {
	private static int onlineCount = 0; // 当前在线人数
	
	// 用来存放每个客户端对应的WebSocket对象
	private static CopyOnWriteArraySet<WebServerSocket> webSocketSet = new CopyOnWriteArraySet<WebServerSocket>();
	
	private Session session; // 注意：这个会话是webSocket中的
	
	private String usid;
	
	/**
	 * 建立连接是调用的方法
	 * @param session
	 * @param usid
	 */
	@OnOpen
	public void onOpen(Session session, @PathParam("id")String usid) {
		this.session = session;
		this.usid = usid;
		
		webSocketSet.add(this);
		
		addOnlineCount(); // 在线人数+1
		
		sendMessage("连接WebSocket服务器成功...");
		
		System.out.println("有新用户  " + usid + " 上线了，当前在线用户人数为 " +  onlineCount);
	}

	/**
	 * 当用户下线时
	 */
	@OnClose
	public void close() {
		webSocketSet.remove(this); // 从当前在线用户列表中，移除当前对象
		subOnlineCount(); // 在线人数 - 1
		System.out.println("用户  " + usid + " 下线了，当前在线用户人数为 " +  onlineCount);
	}
	
	@OnMessage
	public void onMessage(String message, Session session) {
		System.out.println("接收到来自 " + usid + " 的信息，内容为：" + message);

		// 群发
		for (WebServerSocket wss : webSocketSet) {
			wss.sendMessage(message);
		}
	}
	
	@OnError
	public void onError(Session session, Throwable error) {
		error.printStackTrace();
	}
	
	/**
	 * 单聊
	 * @param msg
	 * @param id
	 */
	public static void sendInfo(String msg, @PathParam("id")String id) {
		if (StringUtil.checkNull(id)) {
			return;
		}
		
		for (WebServerSocket wss : webSocketSet) {
			if (id.equals(wss.usid)) {
				wss.sendMessage(msg);
			}
		}
	}
	
	/**
	 * 发送信息给客户端的方法
	 * @param msg
	 */
	public void sendMessage(String msg) {
		try {
			this.session.getBasicRemote().sendText(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static synchronized void addOnlineCount() {
		WebServerSocket.onlineCount ++;
	}
	
	private static synchronized void subOnlineCount() {
		WebServerSocket.onlineCount --;
	}
	
	public static void getOnlineCount() {
		WebServerSocket.onlineCount ++;
	}
	
	/**
	 * 根据登录的用户ID查找连接对象
	 * @param usid
	 * @return
	 */
	public static WebServerSocket getWebSocket(String usid) {
		if (webSocketSet.isEmpty()) {
			return null;
		}
		
		for (WebServerSocket wss : webSocketSet) {
			if (usid.equals(wss.usid)) {
				return wss;
			}
		}
		
		return null;
	}
}
