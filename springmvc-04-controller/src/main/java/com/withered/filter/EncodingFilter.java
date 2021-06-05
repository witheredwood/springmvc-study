package com.withered.filter;

import javax.servlet.*;
import java.io.IOException;
import java.util.logging.LogRecord;


// 需要在web.xml中注册该过滤器
// 这个自己编写的过滤器只能解决get请求的乱码，不能解决post方法的乱码问题
public class EncodingFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        chain.doFilter(request, response);
    }

    public void destroy() {

    }
}
