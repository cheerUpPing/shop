package com.elon.util;

import com.elon.entity.Food;

import java.util.*;

/**
 * 公共数据工具类
 */
public class DataUtil {
    /**
     * 分类商品/食物--大类-小类(包含排序)
     *
     * @param foodList
     * @return
     */
    public static Map<Food, List<Food>> sortFoods(List<Food> foodList) {
        Map<Food, List<Food>> foodListMap = new TreeMap<Food, List<Food>>(new Comparator<Food>() {
            @Override
            public int compare(Food o1, Food o2) {
                return o1.getFood_order() - o2.getFood_order();
            }
        });
        //父级菜单
        for (Food food : foodList) {
            if (food.getIs_parent() == 0) {
                foodListMap.put(food, new ArrayList<Food>());
            }
        }
        //对应子菜单
        Set<Map.Entry<Food, List<Food>>> entries = foodListMap.entrySet();
        for (Map.Entry<Food, List<Food>> entry : entries) {
            List<Food> list = entry.getValue();
            Food food = entry.getKey();
            for (Food tem : foodList) {
                if (food.getId().equals(tem.getParent_food_id())) {
                    list.add(tem);
                }
            }
        }
        //对子菜单进行排序
        for (Map.Entry<Food, List<Food>> entry : entries) {
            List<Food> list = entry.getValue();
            Collections.sort(list, new Comparator<Food>() {
                @Override
                public int compare(Food o1, Food o2) {
                    return o1.getFood_order() - o2.getFood_order();
                }
            });
        }
        return foodListMap;
    }
}
