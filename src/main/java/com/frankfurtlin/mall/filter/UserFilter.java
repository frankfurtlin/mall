package com.frankfurtlin.mall.filter;

import com.frankfurtlin.mall.common.Constant;
import com.frankfurtlin.mall.model.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Frankfurtlin
 * @version 1.0
 * @date 2022/5/4 9:18
 * 用户过滤器类
 */
public class UserFilter implements Filter {
    public static User currentUser;

    @Override
    public void init(FilterConfig filterConfig){

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //首先，获取Session对象；以便可以尝试从Session对象中，获取当前登录用户；
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpServletRequest.getSession();

        //尝试获取当前登录用户
        currentUser = (User) session.getAttribute(Constant.MALL_USER);
        //如果获取不到，说明当前没有用户登录；就返回【用户未登录】的信息
        if (currentUser == null) {
            PrintWriter out = new HttpServletResponseWrapper((HttpServletResponse) servletResponse).getWriter();
            out.write("{\n" +
                    "    \"status\": 10006,\n" +
                    "    \"msg\": \"NEED_LOGIN\",\n" +
                    "    \"data\": null\n" +
                    "}");
            out.flush();
            out.close();
            //直接return的意思是，直接结束方法；不会执行后面的内容了；（自然，这儿直接结束方法的结果就是：这个请求不会进入Controller）
            return;
        }

        //如果当前有用户登录，那么就放行
        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {

    }
}
