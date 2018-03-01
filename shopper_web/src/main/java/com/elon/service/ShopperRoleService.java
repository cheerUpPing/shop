package com.elon.service;

import com.elon.entity.ShopperRole;
import com.elon.mapper.ShopperRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 2017/11/21 10:48.
 * <p>
 * Email: cheerUpPing@163.com
 */
@Service
public class ShopperRoleService extends BaseService<ShopperRole> {

    @Autowired
    private ShopperRoleMapper shopperRoleMapper;

    @Override
    public void insert(ShopperRole shopper) {
        shopperRoleMapper.insert(shopper);
    }

    public List<ShopperRole> selectAll() {
        return shopperRoleMapper.selectAll();
    }

    public ShopperRole selectByShopperNickName(String shopperNickName){
        return shopperRoleMapper.selectByShopperNickName(shopperNickName);
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void update(ShopperRole shopperRole) {

    }

    @Override
    public ShopperRole selectById(Integer id) {
        return null;
    }
}
