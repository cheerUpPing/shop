package com.elon.controller;

import com.elon.contants.Contants;
import com.elon.entity.OrderTrade;
import com.elon.entity.OrderTradeDetail;
import com.elon.entity.Shopper;
import com.elon.service.OrderTradeDetailService;
import com.elon.service.OrderTradeService;
import com.elon.util.MsgWrapper;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderTradeService orderTradeService;
    @Autowired
    private OrderTradeDetailService orderTradeDetailService;

    /**
     * 获取订单列表
     *
     * @param payStatus 支付状态,0未支付 1已支付 2支付失败
     * @param isPacked  是否打包 0打包 1不打包
     * @param request
     * @return
     */
    @RequestMapping("/orderListIndex.do")
    public String orderListIndex(Integer page, Integer limit, Integer payStatus, Integer isPacked, Integer isComplete, HttpServletRequest request) {
        Shopper shopper = (Shopper) request.getSession().getAttribute(Contants.curr_user);
        PageBounds pageBounds = new PageBounds(page == null ? 1 : page, limit == null ? 10 : limit);
        List<OrderTrade> orderTradeList = orderTradeService.selectByConditions(null, null, isPacked, payStatus, isComplete, null, null, shopper.getNick_name(), pageBounds);
        Map<OrderTrade, List<OrderTradeDetail>> orderTradeListMap = new HashMap<OrderTrade, List<OrderTradeDetail>>();
        for (OrderTrade orderTrade : orderTradeList) {
            List<OrderTradeDetail> orderTradeDetailList = orderTradeDetailService.selectByParentOrderNo(orderTrade.getOrder_no());
            orderTradeListMap.put(orderTrade, orderTradeDetailList);
        }
        request.setAttribute("orderTradeListMap", orderTradeListMap);
        return "order/order_list";
    }

    /**
     * 获取订单列表[json数据]
     *
     * @param request
     * @return
     */
    @RequestMapping("/orderList.do")
    @ResponseBody
    public MsgWrapper orderList(Integer page, Integer limit, HttpServletRequest request) {
        Shopper shopper = (Shopper) request.getSession().getAttribute(Contants.curr_user);
        PageBounds pageBounds = new PageBounds(page, limit);
        List<OrderTrade> orderTradeList = orderTradeService.selectByConditions(null, null, null, null, null,null, null, shopper.getNick_name(), pageBounds);
        Map<OrderTrade, List<OrderTradeDetail>> orderTradeListMap = new HashMap<OrderTrade, List<OrderTradeDetail>>();
        for (OrderTrade orderTrade : orderTradeList) {
            List<OrderTradeDetail> orderTradeDetailList = orderTradeDetailService.selectByParentOrderNo(orderTrade.getOrder_no());
            orderTradeListMap.put(orderTrade, orderTradeDetailList);
        }
        return new MsgWrapper(orderTradeListMap);
    }

    /**
     * 更新订单明细为完成状态
     *
     * @param childrenOrderNo
     * @param parentOrderNo
     * @return
     */
    @RequestMapping("/completeOrderDetail.do")
    @ResponseBody
    public MsgWrapper completeOrderDetail(String childrenOrderNo,String parentOrderNo){
        return orderTradeDetailService.completeOrderDetail(childrenOrderNo, parentOrderNo);
    }

}
