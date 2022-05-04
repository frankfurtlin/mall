package com.frankfurtlin.mall.filter;

import com.frankfurtlin.mall.common.Constant;
import com.frankfurtlin.mall.model.entity.User;
import com.frankfurtlin.mall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Frankfurtlin
 * @version 1.0
 * @date 2022/4/29 14:14
 * 校验管理员身份 过滤器类
 */
public class AdminFilter implements Filter {
    @Autowired
    IUserService iUserService;

    @Override
    public void init(FilterConfig filterConfig){

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //获取session
        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
        HttpSession httpSession = httpServletRequest.getSession();

        //尝试获取用户
        User user = (User)httpSession.getAttribute(Constant.MALL_USER);
        if(user == null){
            PrintWriter printWriter = new HttpServletResponseWrapper((HttpServletResponse)servletResponse).getWriter() ;
            printWriter.write("{\n" +
                    "    \"status\": 10006,\n" +
                    "    \"msg\": \"NEED LOGIN\",\n" +
                    "    \"data\": null\n" +
                    "}");
            printWriter.flush();
            printWriter.close();
            //直接return的意思是，直接结束方法；不会执行后面的内容了；（自然，这儿直接结束方法的结果就是：这个请求不会进入Controller）
            return;
        }

        //校验当前用户是否是管理员用户
        boolean isAdminRole = iUserService.checkAdminRole(user);

        if (isAdminRole) {
            //如果是管理用户；就放行这个请求；
            // 执行【chain.doFilter(request, response);】会把这个请求放行到下一个过滤器或者Controller
            filterChain.doFilter(servletRequest, servletResponse);
        } else {//如果不是管理员用户；就没有放行这个请求，而是给出一个错误提示；
            PrintWriter out = new HttpServletResponseWrapper((HttpServletResponse) servletResponse).getWriter();
            out.write("{\n" +
                    "    \"status\": 10008,\n" +
                    "    \"msg\": \"NEED ADMIN\",\n" +
                    "    \"data\": null\n" +
                    "}");
            out.flush();
            out.close();
        }
    }

    @Override
    public void destroy() {

    }
}
