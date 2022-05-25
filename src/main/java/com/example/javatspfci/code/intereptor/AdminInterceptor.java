package com.example.javatspfci.code.intereptor;

import com.example.javatspfci.code.util.JWTUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 厂家权限拦截器
 */
@Component
public class AdminInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        String url = request.getRequestURL().toString();
        if (token == null || token.equals("")){
            response.sendRedirect(request.getContextPath() + "/base/noLogin");
            return false;
        } else {
            Integer code = JWTUtil.verify(token);
            if (code == 1){
                String role = JWTUtil.getString(token,"role");
                if (!url.contains(role)){
                    response.sendRedirect(request.getContextPath() + "/base/noPerm");
                    return false;
                }
            } else if (code == -1){
                response.sendRedirect(request.getContextPath() + "/base/error");
                return false;
            } else if (code == -2){
                response.sendRedirect(request.getContextPath() + "/base/timeout");
                return false;
            }
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
