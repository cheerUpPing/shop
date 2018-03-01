package com.elon.contants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2017/11/21 14:27.
 * <p>
 * Email: cheerUpPing@163.com
 */
public class Contants {

    public static String curr_user = "shopper_nick_name";

    //大菜单的css类属性
    public static List<String> classStyles = new ArrayList<String>();

    static {
        classStyles.add("am-icon-flag");
        classStyles.add("am-icon-lock");
        classStyles.add("am-icon-diamond");
        classStyles.add("am-icon-cart-plus");
        classStyles.add("am-icon-ship");
        classStyles.add("am-icon-motorcycle");
        classStyles.add("am-icon-heartbeat");
        classStyles.add("am-icon-venus");
        classStyles.add("am-icon-mars");
        classStyles.add("am-icon-mercury");
        classStyles.add("am-icon-venus-double");
        classStyles.add("am-icon-mars-double");
        classStyles.add("am-icon-neuter");
    }

    public static class Error {

        private static Map<String, String> errorMap = new HashMap<String, String>();

        public static String user_not_exist = "0001";
        private static String user_not_exist_msg = "用户名或密码错误";

        public static String unknow_exception = "0002";
        private static String unknow_exception_msg = "服务器错误";

        public static String session_invalid = "0003";
        private static String session_invalid_msg = "session失效,请重新登录";

        public static String param_not_empty = "0004";
        private static String param_not_empty_msg = "参数不能为空";

        public static String image_format_wrong = "0005";
        private static String image_format_wrong_msg = "文件格式错误或格式不支持";

        public static String save_update_wrong = "0006";
        private static String save_update_wrong_msg = "更新或保存失败";

        public static String wx_not_exist = "0007";
        private static String wx_not_exist_msg = "对应的微信公众号不存在";

        public static String not_allow_delete_wx = "0008";
        private static String not_allow_delete_wx_msg = "对不起,你没有权力删除他人微信公众号";

        public static String wrong_next_do = "0009";
        private static String wrong_next_do_msg = "操作异常,请稍后重试";

        public static String all_order_detail_complete = "0010";
        private static String all_order_detail_complete_msg = "所有的订单都完成了";

        static {
            errorMap.put(user_not_exist, user_not_exist_msg);
            errorMap.put(unknow_exception, unknow_exception_msg);
            errorMap.put(session_invalid, session_invalid_msg);
            errorMap.put(param_not_empty, param_not_empty_msg);
            errorMap.put(image_format_wrong, image_format_wrong_msg);
            errorMap.put(save_update_wrong, save_update_wrong_msg);
            errorMap.put(wx_not_exist, wx_not_exist_msg);
            errorMap.put(not_allow_delete_wx, not_allow_delete_wx_msg);
            errorMap.put(wrong_next_do, wrong_next_do_msg);
            errorMap.put(all_order_detail_complete, all_order_detail_complete_msg);

        }

        public static String getErrorMsg(String errorKey) {
            return errorMap.get(errorKey);
        }

    }
}
