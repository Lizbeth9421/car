package com.ict.system.interceptor;

import com.ict.system.constant.constant;
import com.ict.system.util.WebUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 *
 * @Author: Lizbeth9421
 * @Date: 2021/08/19/0:07
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
        if (WebUtils.getHttpSession().getAttribute(constant.USER_IN_SESSION) == null) {
            response.sendRedirect("/views/login.jsp");
            return false;//阻止往后放行
        }
        return true;//放行,放行给下一个拦截器或最终的处理器
    }
}
