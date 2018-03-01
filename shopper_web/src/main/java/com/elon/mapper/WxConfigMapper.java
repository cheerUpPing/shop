package com.elon.mapper;

import com.elon.entity.WxConfig;

import java.util.List;

/**
 * 2017/11/21 10:11.
 * <p>
 * Email: cheerUpPing@163.com
 */
public interface WxConfigMapper extends BaseMapper<WxConfig> {

    List<WxConfig> selectAll();

    List<WxConfig> selectByNickName(String shopperNickName);
}
