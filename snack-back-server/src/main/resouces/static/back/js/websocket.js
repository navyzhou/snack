function openWebSocket(usid) {
	var socket;
	if (typeof(WebSocket) == undefined) {
		alert("对不起，您的浏览器不支持WebSocket通信...");
		return;
	}
	
	socket = new WebSocket("ws://127.0.0.1:8080/websocket/" +usid);
	
	socket.onopen = function() {
		console.info("Socket已连接...");
	}
	
	socket.onclose = function() {
		console.info("Socket已关闭...");
	}
	
	socket.error = function() {
		console.info("Socket服务器访问失败...");
	}
	
	socket.onmessage = function(msg) {
		console.info(msg);
		if (msg.data == "101") { // 我规定，如果返回的编码是101，则说明是挤退信息
			alert("对不起，您已经在其它地方登录，若非本人操作，请及时修改密码...");
			location.href="../loginout";
		}
	}
}