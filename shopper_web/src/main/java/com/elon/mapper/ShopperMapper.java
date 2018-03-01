package com.elon.mapper;

import com.elon.entity.Shopper;

import java.util.List;

/**
 * 2017/11/21 10:11.
 * <p>
 * Email: cheerUpPing@163.com
 */
public interface ShopperMapper extends BaseMapper<Shopper> {

    List<Shopper> selectAll();

    Shopper selectByNickName(String shopperNickName);
}
