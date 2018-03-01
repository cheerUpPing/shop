package com.elon.controller;

import com.alibaba.fastjson.JSON;
import com.elon.contants.Contants;
import com.elon.entity.Food;
import com.elon.entity.MenuLink;
import com.elon.entity.Shopper;
import com.elon.service.FoodService;
import com.elon.service.MenuLinkService;
import com.elon.util.DataUtil;
import com.elon.util.ImageUtil;
import com.elon.util.LogUtil;
import com.elon.util.MsgWrapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.elon.contants.Contants.Error.*;

@Controller
@RequestMapping("/food")
public class FoodController {

    private Logger logger = Logger.getLogger(FoodController.class);

    @Autowired
    private FoodService foodService;
    @Autowired
    private MenuLinkService menuLinkService;

    /**
     * 返回菜品列表页面
     *
     * @param menuId
     * @param request
     * @return
     */
    @RequestMapping("/foodListIndex.do")
    public String foodList(Integer menuId, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Shopper shopper = (Shopper) session.getAttribute(Contants.curr_user);
        List<Food> foodList = foodService.selectByShopperNickName(shopper.getNick_name());
        Map<Food, List<Food>> foodListMap = DataUtil.sortFoods(foodList);

        //编辑菜单连接
        MenuLink menuLink = menuLinkService.selectByMenuUrl("food/addOrModifyFoodIndex.do");
        System.out.println(JSON.toJSON(foodListMap));
        request.setAttribute("foodListMap", foodListMap);
        request.setAttribute("menuId", menuId);
        request.setAttribute("menuLink", menuLink);
        return "food/food_list";
    }

    /**
     * 获取商品菜单图片[微信扫描]
     *
     * @param shopperNickName
     * @param imageName
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/getFoodImage.do")
    public void getFoodImage(String shopperNickName, String imageName, Integer isScaleSmall, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //将图片输出给浏览器
        String servletPath = request.getServletContext().getRealPath("/");
        File rootFile = new File(servletPath + "/image/" + shopperNickName);
        if (!rootFile.exists()) {
            rootFile.mkdirs();
        }
        File imageFile = null;
        if (StringUtils.isEmpty(imageName)) {
            imageFile = new File(servletPath + "/image/default.jpg");
        } else {
            imageFile = new File(rootFile + "/" + imageName);
        }
        BufferedImage image = ImageIO.read(imageFile);
        //图片缩放大小
        if (isScaleSmall != null && isScaleSmall == 0) {
            image = ImageUtil.resize(image, 75, 100);
        }
        response.setContentType("image/jpeg");
        OutputStream os = response.getOutputStream();
        ImageIO.write(image, "jpg", os);
    }

    /**
     * 删除菜品,小类别
     *
     * @param foodId
     * @return
     */
    @RequestMapping("/deleteFoodById.do")
    @ResponseBody
    public MsgWrapper deleteFoodById(Integer foodId) {
        foodService.deleteById(foodId);
        return new MsgWrapper();
    }

    /**
     * 删除大类别
     *
     * @param foodId
     * @return
     */
    @RequestMapping("/deleteBigFoodById.do")
    @ResponseBody
    public MsgWrapper deleteBigFoodById(Integer foodId) {
        foodService.deleteBigById(foodId);
        return new MsgWrapper();
    }

    /**
     * 返回菜品增加/更改页面
     *
     * @param foodId
     * @param request
     * @return
     */
    @RequestMapping("/addOrModifyFoodIndex.do")
    public String addOrModifyFoodIndex(Integer foodId, HttpServletRequest request) {
        Shopper shopper = (Shopper) request.getSession().getAttribute(Contants.curr_user);
        Food food = foodService.selectById(foodId);
        List<Food> parentFoods = foodService.selectParentOrChildren(shopper.getNick_name(), 0);
        request.setAttribute("food", food);
        request.setAttribute("parentFoods", parentFoods);
        return "food/add_modify";
    }

    /**
     * 保存增加和修改
     *
     * @param food
     */
    @RequestMapping("/saveFood.do")
    @ResponseBody
    public MsgWrapper saveFood(Food food, HttpServletRequest request) {
        Shopper shopper = (Shopper) request.getSession().getAttribute(Contants.curr_user);
        MsgWrapper msgWrapper = new MsgWrapper();
        try {
            //更新
            if (food.getId() != null) {
                foodService.update(food);
            } else {//插入
                food.setIs_parent(1);
                food.setFood_sale(0);
                food.setShopper_nick_name(shopper.getNick_name());
                food.setAdd_time(new Date());
                foodService.insert(food);
            }
        } catch (Exception e) {
            logger.error("保存/更新菜品出现异常,菜品id:" + food.getId() + " 异常:" + LogUtil.getStackTrace(e));
            msgWrapper.setMsg(Contants.Error.getErrorMsg(save_update_wrong));
            msgWrapper.setCode(save_update_wrong);
        }
        return msgWrapper;
    }

    @RequestMapping("/saveImage.do")
    @ResponseBody
    public MsgWrapper saveImage(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        String servletPath = request.getServletContext().getRealPath("/");
        Shopper shopper = (Shopper) request.getSession().getAttribute(Contants.curr_user);
        MsgWrapper msgWrapper = new MsgWrapper();
        if (file != null) {
            String originalFileName = file.getOriginalFilename();
            String suffix = originalFileName.substring(originalFileName.lastIndexOf("."), originalFileName.length());
            if (suffix.equals(".jpg") || suffix.equals(".jpeg") || suffix.equals(".jpe") || suffix.equals(".JPG") || suffix.equals(".JPEG") || suffix.equals(".JPE")) {
                InputStream is = file.getInputStream();
                //将图片输出给浏览器
                File imageRoot = new File(servletPath + "image/" + shopper.getNick_name());
                if (!imageRoot.exists()) {
                    imageRoot.mkdirs();
                }
                String fileName = sdf.format(new Date()) + suffix;
                File imageFile = new File(imageRoot, fileName);
                //获取输出流
                OutputStream os = new FileOutputStream(imageFile);
                int temp;
                byte[] bytes = new byte[10240];
                //一个一个字节的读取并写入
                while ((temp = is.read(bytes)) != -1) {
                    os.write(bytes, 0, temp);
                }
                os.flush();
                os.close();
                is.close();
                msgWrapper.setData(fileName);
            } else {
                msgWrapper.setCode(image_format_wrong);
                msgWrapper.setMsg(Contants.Error.getErrorMsg(image_format_wrong));
            }
        } else {
            msgWrapper.setCode(param_not_empty);
            msgWrapper.setMsg(Contants.Error.getErrorMsg(param_not_empty));
        }
        return msgWrapper;
    }

    /**
     * 添加/修改大类页面
     *
     * @param menuId
     * @param request
     * @return
     */
    @RequestMapping("/addOrModifyBigFoodIndex.do")
    public String addOrModifyBigFoodIndex(Integer menuId,HttpServletRequest request) {
        Shopper shopper = (Shopper) request.getSession().getAttribute(Contants.curr_user);
        List<Food> foodList = foodService.selectParentOrChildren(shopper.getNick_name(),0);
        Collections.sort(foodList, new Comparator<Food>() {
            @Override
            public int compare(Food o1, Food o2) {
                return o1.getFood_order() - o2.getFood_order();
            }
        });
        request.setAttribute("menuId", menuId);
        request.setAttribute("foodList", foodList);
        return "food/add_modify_big";
    }

    /**
     * 保存菜品大类
     *
     * @param food
     * @return
     */
    @RequestMapping("/saveBigFood.do")
    @ResponseBody
    public MsgWrapper saveBigFood(Food food, HttpServletRequest request) {
        Shopper shopper = (Shopper) request.getSession().getAttribute(Contants.curr_user);
        MsgWrapper msgWrapper = new MsgWrapper();
        try {
            if (food.getId() != null) {
                foodService.update(food);
            } else {
                food.setIs_parent(0);
                food.setShopper_nick_name(shopper.getNick_name());
                //大类别的父类id是0
                food.setParent_food_id(0);
                food.setAdd_time(new Date());
                foodService.insert(food);
            }
        } catch (Exception e) {
            logger.error("保存/更新菜品出现异常,菜品id:" + food.getId() + " 异常:" + LogUtil.getStackTrace(e));
            msgWrapper.setMsg(Contants.Error.getErrorMsg(save_update_wrong));
            msgWrapper.setCode(save_update_wrong);
        }
        return msgWrapper;
    }
}
