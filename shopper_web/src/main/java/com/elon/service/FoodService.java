package com.elon.service;

import com.elon.entity.Food;
import com.elon.mapper.FoodMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 2017/11/21 10:48.
 * <p>
 * Email: cheerUpPing@163.com
 */
@Service
public class FoodService extends BaseService<Food> {

    @Autowired
    private FoodMapper foodMapper;

    @Override
    public void insert(Food food) {
        foodMapper.insert(food);
    }

    public List<Food> selectAll() {
        return foodMapper.selectAll();
    }

    /**
     * 查询商户所有的商品/菜单
     *
     * @param shopperNickName
     * @return
     */
    public List<Food> selectByShopperNickName(String shopperNickName) {
        return foodMapper.selectByShopperNickName(shopperNickName);
    }

    /**
     * 查询到商户的大类/小类菜单
     * 0查询大类 1查询小类
     *
     * @param shopperNickName
     * @return
     */
    public List<Food> selectParentOrChildren(String shopperNickName,Integer isParent){
        return foodMapper.selectParentOrChildren(shopperNickName, isParent);
    }

    @Override
    public void deleteById(Integer id) {
        foodMapper.deleteById(id);
    }

    public void deleteBigById(Integer foodId) {
        foodMapper.deleteBigById(foodId);
    }

    @Override
    public void update(Food food) {
        foodMapper.update(food);
    }

    @Override
    public Food selectById(Integer id) {
        return foodMapper.selectById(id);
    }

}
