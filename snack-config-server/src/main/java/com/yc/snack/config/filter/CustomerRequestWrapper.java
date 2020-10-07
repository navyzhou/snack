package com.yc.snack.config.filter;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 请求包装处理，不处理通过bus-refresh自动刷新配置文件的时候总是报错，长度超出限制   Wrapper包装
 * 源辰信息  
 * @author navy
 * @date 2020年9月27日
 * @email haijunzhou@hnit.edu.cn
 */
public class CustomerRequestWrapper extends HttpServletRequestWrapper {

    public CustomerRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        byte[] bytes = new byte[0];
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);

        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return byteArrayInputStream.read() == -1 ? true:false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            @Override
            public int read() throws IOException {
                return byteArrayInputStream.read();
            }
        };
    }
}