package com.elon.service;

import com.elon.entity.Shopper;
import com.elon.mapper.ShopperMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 2017/11/21 10:48.
 * <p>
 * Email: cheerUpPing@163.com
 */
@Service
public class ShopperService extends BaseService<Shopper> {

    @Autowired
    private ShopperMapper shopperMapper;

    @Override
    public void insert(Shopper shopper) {
        shopperMapper.insert(shopper);
    }

    public List<Shopper> selectAll(){
        return shopperMapper.selectAll();
    }

    /**
     * 通过nickName查询商铺
     * @param shopperNickName
     * @return
     */
    public Shopper selectByNickName(String shopperNickName){
        return shopperMapper.selectByNickName(shopperNickName);
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void update(Shopper shopper) {

    }

    @Override
    public Shopper selectById(Integer id) {
        return null;
    }
}
