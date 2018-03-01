package com.elon.listener;

import com.elon.contants.Contants;
import com.elon.entity.Shopper;
import org.apache.log4j.Logger;

import javax.servlet.http.*;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * session监听器
 */
public class SessionListener implements HttpSessionListener, HttpSessionAttributeListener {

    private Logger logger = Logger.getLogger(SessionListener.class);

    private Set<HttpSession> sessionSet = Collections.synchronizedSet(new HashSet<HttpSession>());

    /**
     * 当session中添加属性时的监听器回调方法(避免多地登录)
     *
     * @param httpSessionBindingEvent
     */
    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 如果添加的属性名是 Contants.curr_user 则说明有一个用户登录了
        if (httpSessionBindingEvent.getName().equals(Contants.curr_user)) {
            // 将该用户信息添加到在线用户列表中
            Shopper shopper = (Shopper) httpSessionBindingEvent.getValue();
            logger.info("商户登录成功,商户:" + ((Shopper) httpSessionBindingEvent.getValue()).getNick_name() + " 时间:" + sdf.format(new Date()));
            for (HttpSession session : sessionSet) {
                //如果一起存在这个商户的session那么说明多个地方登录
                if (shopper.equals(session.getAttribute(Contants.curr_user))) {
                    session.invalidate();
                }
            }
            sessionSet.add(httpSessionBindingEvent.getSession());
        }
    }

    /**
     * 当session中移出属性时的监听器回调方法
     * 可以被session销毁触发
     *
     * @param httpSessionBindingEvent
     */
    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 如果移出的属性名是USER_INFO，则说明有一个用户注销了
        if (httpSessionBindingEvent.getName().equals(Contants.curr_user)) {
            // 从在线用户列表中将该用户信息移出
            sessionSet.remove(httpSessionBindingEvent.getSession());
            logger.info("session属性被移除:" + httpSessionBindingEvent.getName() + " 商户:" + ((Shopper) httpSessionBindingEvent.getValue()).getNick_name() + " 时间:" + sdf.format(new Date()));
        }
    }

    /**
     * 当session中属性值被替换时的监听器回调方法
     *
     * @param httpSessionBindingEvent
     */
    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {
        // 如果替换的属性名是USER_INFO，则说明有一个用户变更登录身份了
        if (httpSessionBindingEvent.getName().equals(Contants.curr_user)) {
            // 更新当前用户的登录信息

        }
    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

    }

    /**
     * session销毁,会触发属性移除
     *
     * @param httpSessionEvent
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        logger.info("session销毁, 商户:" + ((Shopper) httpSessionEvent.getSession().getAttribute(Contants.curr_user)).getNick_name() + " 时间:" + sdf.format(new Date()));
        sessionSet.remove(httpSessionEvent.getSession());
    }
}
