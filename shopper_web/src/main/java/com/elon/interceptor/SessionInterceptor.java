package com.elon.interceptor;

import com.alibaba.fastjson.JSON;
import com.elon.contants.Contants;
import com.elon.util.MsgWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

import static com.elon.contants.Contants.Error.session_invalid;

/**
 * session拦截器
 */
public class SessionInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        HttpSession session = httpServletRequest.getSession();
        //未登录
        if (session.getAttribute(Contants.curr_user) == null) {
            String requestType = httpServletRequest.getHeader("X-Requested-With");
            if (StringUtils.isNotEmpty(requestType)){
                MsgWrapper msgWrapper = new MsgWrapper(session_invalid, Contants.Error.getErrorMsg(session_invalid));
                String backJson = JSON.toJSONString(msgWrapper);
                httpServletResponse.setHeader("Content-Type", "text/html,charset=utf-8");
                httpServletResponse.setCharacterEncoding("utf-8");
                PrintWriter writer = httpServletResponse.getWriter();
                writer.print(backJson);
                writer.flush();
            }else {
                httpServletResponse.sendRedirect("/loginIndex.do");
            }
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
