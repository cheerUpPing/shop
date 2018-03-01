package com.elon.controller;

import com.elon.service.ShopperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 2017/11/21 11:26.
 * <p>
 * Email: cheerUpPing@163.com
 * <p>
 * 商店拥有者
 */
@Controller
@RequestMapping("/shopper")
public class ShopperController {

    @Autowired
    private ShopperService shopperService;

    @RequestMapping("/getAll.do")
    @ResponseBody
    public Object getAll(Integer mark) {
        return shopperService.selectAll();
    }


}
