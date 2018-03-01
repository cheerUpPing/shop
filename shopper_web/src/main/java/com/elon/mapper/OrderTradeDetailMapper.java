package com.elon.mapper;

import com.elon.entity.OrderTradeDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 2017/11/21 10:11.
 * <p>
 * Email: cheerUpPing@163.com
 */
public interface OrderTradeDetailMapper extends BaseMapper<OrderTradeDetail> {

    List<OrderTradeDetail> selectAll();

    List<OrderTradeDetail> selectByParentOrderNo(String parentOrderNO);

    void updateComplete(@Param("childrenOrderNo") String childrenOrderNo, @Param("isComplete") Integer isComplete);

}
