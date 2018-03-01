package com.elon.mapper;

import com.elon.entity.OrderTrade;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 2017/11/21 10:11.
 * <p>
 * Email: cheerUpPing@163.com
 */
public interface OrderTradeMapper extends BaseMapper<OrderTrade> {

    List<OrderTrade> selectAll();

    OrderTrade selectByOrderNo(String orderNo);

    List<OrderTrade> selectByShopperNickName(String shopperNickName, PageBounds pageBounds);

    List<OrderTrade> selectByConditions(@Param("orderNo") String orderNo, @Param("wxOrderNo") String wxOrderNo, @Param("isPacked") Integer isPacked, @Param("payStatus") Integer payStatus,@Param("isComplete")Integer isComplete, @Param("startTime") Date startTime, @Param("endTime") Date endTime, @Param("shopperNickName") String shopperNickName, PageBounds pageBounds);

    void updateComplete(@Param("orderNo") String orderNo,@Param("isComplete") Integer isComplete);

}
