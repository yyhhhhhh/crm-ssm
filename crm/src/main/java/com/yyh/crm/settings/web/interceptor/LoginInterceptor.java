package com.yyh.crm.settings.web.interceptor;

import com.yyh.crm.commons.contants.Contants;
import com.yyh.crm.settings.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author yyh
 * @date 2022-03-09 21:16
 * 登录验证拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {

    //登录验证
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(Contants.SESSION_USER);
        if(user == null){
            response.sendRedirect(request.getContextPath());//重定向时,url必须加项目的名称
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
