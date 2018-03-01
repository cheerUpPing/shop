package com.elon.controller;

import com.elon.contants.Contants;
import com.elon.entity.Shopper;
import com.elon.entity.WxConfig;
import com.elon.service.WxConfigService;
import com.elon.util.LogUtil;
import com.elon.util.MsgWrapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

import static com.elon.contants.Contants.Error.*;

/**
 * 2017/11/24 15:56.
 * <p>
 * Email: cheerUpPing@163.com
 * <p>
 * 获取微信公众账户配置
 */
@Controller
@RequestMapping("/wx")
public class WxConfigController {

    private Logger logger = Logger.getLogger(WxConfigController.class);

    @Autowired
    private WxConfigService wxConfigService;

    @RequestMapping("/getAll.do")
    @ResponseBody
    public MsgWrapper getAll() {
        List<WxConfig> wxConfigList = wxConfigService.selectAll();
        return new MsgWrapper(wxConfigList);
    }

    /**
     * 根据商户名获取对应的微信公众账户配置
     *
     * @param shopperNickName
     * @return
     */
    @RequestMapping("/getByNickName.do")
    @ResponseBody
    public MsgWrapper getByNickName(String shopperNickName) {
        List<WxConfig> wxConfigList = wxConfigService.selectByNickName(shopperNickName);
        return new MsgWrapper(wxConfigList);
    }

    /***
     * 跳转到微信公众号添加/修改页面
     *
     * @param menuId
     * @param request
     * @return
     */
    @RequestMapping("/addOrModifyWxIndex.do")
    public String addOrModifyWxIndex(Integer menuId,HttpServletRequest request) {
        Shopper shopper = (Shopper) request.getSession().getAttribute(Contants.curr_user);
        List<WxConfig> wxConfigList = wxConfigService.selectByNickName(shopper.getNick_name());
        request.setAttribute("wxConfigList",wxConfigList);
        request.setAttribute("menuId",menuId);
        return "wx/add_modify";
    }

    @RequestMapping("/deleteById.do")
    @ResponseBody
    public MsgWrapper deleteById(Integer wxId,HttpServletRequest request){
        MsgWrapper msgWrapper = new MsgWrapper();
        Shopper shopper = (Shopper) request.getSession().getAttribute(Contants.curr_user);
        try {
            WxConfig wxConfig = wxConfigService.selectById(wxId);
            if (wxConfig == null){
                msgWrapper.setCode(wx_not_exist);
                msgWrapper.setMsg(Contants.Error.getErrorMsg(wx_not_exist));
            }else {
                if (!shopper.getNick_name().equals(wxConfig.getShopper_nick_name())){
                    msgWrapper.setCode(not_allow_delete_wx);
                    msgWrapper.setMsg(Contants.Error.getErrorMsg(not_allow_delete_wx));
                }else {
                    wxConfigService.deleteById(wxId);
                }
            }
        }catch (Exception e){
            msgWrapper.setCode(wrong_next_do);
            msgWrapper.setMsg(Contants.Error.getErrorMsg(wrong_next_do));
        }
        return msgWrapper;
    }

    /**
     * 保存/更新微信公众号
     *
     * @param wxConfig
     * @param request
     * @return
     */
    @RequestMapping("/saveWx.do")
    @ResponseBody
    public MsgWrapper saveWx(WxConfig wxConfig,HttpServletRequest request){
        MsgWrapper msgWrapper = new MsgWrapper();
        Shopper shopper = (Shopper) request.getSession().getAttribute(Contants.curr_user);
        try {
            //更新
            if (wxConfig.getId() != null){
                wxConfigService.update(wxConfig);
            }else {//添加
                wxConfig.setShopper_nick_name(shopper.getNick_name());
                wxConfig.setAdd_time(new Date());
                wxConfigService.insert(wxConfig);
            }
        }catch (Exception e){
            logger.error("保存/更新微信公众号出现异常,公众号id:" + wxConfig.getId() + " 异常:" + LogUtil.getStackTrace(e));
            msgWrapper.setMsg(Contants.Error.getErrorMsg(save_update_wrong));
            msgWrapper.setCode(save_update_wrong);
        }
        return msgWrapper;
    }

}
