package com.elon.service;

import com.elon.entity.OrderTrade;
import com.elon.mapper.OrderTradeMapper;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 2017/11/21 14:04.
 * <p>
 * Email: cheerUpPing@163.com
 */
@Service
public class OrderTradeService extends BaseService<OrderTrade> {

    @Autowired
    private OrderTradeMapper orderTradeMapper;

    @Override
    public void insert(OrderTrade orderTrade) {
        orderTradeMapper.insert(orderTrade);
    }

    public List<OrderTrade> selectAll() {
        return orderTradeMapper.selectAll();
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void update(OrderTrade orderTrade) {

    }

    @Override
    public OrderTrade selectById(Integer id) {
        return null;
    }

    public OrderTrade selectByOrderNo(String orderNo) {
        return orderTradeMapper.selectByOrderNo(orderNo);
    }

    public List<OrderTrade> selectByShopperNickName(String shopperNickName, PageBounds pageBounds) {
        return orderTradeMapper.selectByShopperNickName(shopperNickName, pageBounds);
    }

    /**
     * 多条件查询
     *
     * @param orderNo
     * @param wxOrderNo
     * @param isPacked
     * @param payStatus
     * @param startTime
     * @param endTime
     * @param shopperNickName
     * @param pageBounds
     * @return
     */
    public List<OrderTrade> selectByConditions(String orderNo, String wxOrderNo, Integer isPacked, Integer payStatus, Integer isComplete, Date startTime, Date endTime, String shopperNickName, PageBounds pageBounds) {
        return orderTradeMapper.selectByConditions(orderNo, wxOrderNo, isPacked, payStatus, isComplete, startTime, endTime, shopperNickName, pageBounds);
    }

    public void updateComplete(String orderNo,Integer isComplete){
        orderTradeMapper.updateComplete(orderNo, isComplete);
    }
}
