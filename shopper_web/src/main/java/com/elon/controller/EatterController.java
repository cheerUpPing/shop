package com.elon.controller;

import com.alibaba.fastjson.JSON;
import com.elon.entity.Food;
import com.elon.entity.Shopper;
import com.elon.service.FoodService;
import com.elon.service.ShopperService;
import com.elon.util.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 2017/11/22 9:37.
 * <p>
 * Email: cheerUpPing@163.com
 * <p>
 * 吃饭者 控制器
 */
@Controller
@RequestMapping("/eat")
public class EatterController {

    @Autowired
    private FoodService foodService;
    @Autowired
    private ShopperService shopperService;

    /**
     * 商店商品食物列表[用来微信二维码扫描]
     *
     * @param shopperNickName 商户名字
     * @param guestSeatNo     座位编号
     * @return
     */
    @RequestMapping("/foodListIndex.do")
    public String foodListIndex(String shopperNickName, String guestSeatNo, HttpServletRequest request) {
        request.setAttribute("shopperNickName", shopperNickName);
        request.setAttribute("guestSeatNo", guestSeatNo);
        Shopper shopper = shopperService.selectByNickName(shopperNickName);
        List<Food> foodList = foodService.selectByShopperNickName(shopperNickName);
        Map<Food, List<Food>> foodListMap = DataUtil.sortFoods(foodList);
        System.out.println(JSON.toJSONString(foodList));
        request.setAttribute("foodListMap", foodListMap);
        request.setAttribute("shopper", shopper);
        return "eat/food_list";
    }


}
