package com.elon.mapper;

import com.elon.entity.Food;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 2017/11/21 10:11.
 * <p>
 * Email: cheerUpPing@163.com
 */
public interface FoodMapper extends BaseMapper<Food> {

    List<Food> selectAll();

    List<Food> selectByShopperNickName(String shopperNickName);

    List<Food> selectParentOrChildren(@Param("shopperNickName") String shopperNickName,@Param("isParent") Integer isParent);

    void deleteBigById(Integer foodId);

}
