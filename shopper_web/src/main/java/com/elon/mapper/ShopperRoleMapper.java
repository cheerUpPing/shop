package com.elon.mapper;

import com.elon.entity.ShopperRole;

import java.util.List;

/**
 * 2017/11/21 10:11.
 * <p>
 * Email: cheerUpPing@163.com
 */
public interface ShopperRoleMapper extends BaseMapper<ShopperRole> {

    List<ShopperRole> selectAll();

    ShopperRole selectByShopperNickName(String shopperNickName);
}
