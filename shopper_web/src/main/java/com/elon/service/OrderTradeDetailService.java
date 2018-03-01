package com.elon.service;

import com.elon.contants.Contants;
import com.elon.entity.OrderTradeDetail;
import com.elon.mapper.OrderTradeDetailMapper;
import com.elon.util.MsgWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.elon.contants.Contants.Error.all_order_detail_complete;
import static com.elon.contants.Contants.Error.param_not_empty;

/**
 * 2017/11/21 14:04.
 * <p>
 * Email: cheerUpPing@163.com
 */
@Service
public class OrderTradeDetailService extends BaseService<OrderTradeDetail> {

    @Autowired
    private OrderTradeDetailMapper orderTradeDetailMapper;
    @Autowired
    private OrderTradeService orderTradeService;

    @Override
    public void insert(OrderTradeDetail orderTradeDetail) {
        orderTradeDetailMapper.insert(orderTradeDetail);
    }

    public List<OrderTradeDetail> selectAll() {
        return orderTradeDetailMapper.selectAll();
    }

    public List<OrderTradeDetail> selectByParentOrderNo(String parentOrderNO) {
        return orderTradeDetailMapper.selectByParentOrderNo(parentOrderNO);
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void update(OrderTradeDetail orderTradeDetail) {

    }

    @Override
    public OrderTradeDetail selectById(Integer id) {
        return null;
    }

    /**
     * 更新子菜单是否完成
     *
     * @param childrenOrderNo
     * @param isComplete
     */
    public void updateComplete(String childrenOrderNo,Integer isComplete) {
        orderTradeDetailMapper.updateComplete(childrenOrderNo, isComplete);
    }

    @Transactional(rollbackFor = Exception.class)
    public MsgWrapper completeOrderDetail(String childrenOrderNo,String parentOrderNo){
        MsgWrapper msgWrapper = new MsgWrapper();
        if (StringUtils.isEmpty(childrenOrderNo) || StringUtils.isEmpty(parentOrderNo)){
            msgWrapper.setCode(param_not_empty);
            msgWrapper.setMsg(Contants.Error.getErrorMsg(param_not_empty));
        }else {
            updateComplete(childrenOrderNo,0);
        }
        List<OrderTradeDetail> orderTradeDetailList = selectByParentOrderNo(parentOrderNo);
        boolean allOrderDetailComplete = true;
        for (OrderTradeDetail orderTradeDetail : orderTradeDetailList){
            if (orderTradeDetail.getIs_complete() == 1){
                allOrderDetailComplete = false;
                break;
            }
        }
        //该订单全部完成
        if (allOrderDetailComplete){
            orderTradeService.updateComplete(parentOrderNo,0);
            msgWrapper.setCode(all_order_detail_complete);
            msgWrapper.setMsg(Contants.Error.getErrorMsg(all_order_detail_complete));
            throw new RuntimeException();
        }
        return msgWrapper;
    }
}
