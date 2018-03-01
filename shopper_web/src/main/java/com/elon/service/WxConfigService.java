package com.elon.service;

import com.elon.entity.WxConfig;
import com.elon.mapper.WxConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 2017/11/21 10:48.
 * <p>
 * Email: cheerUpPing@163.com
 */
@Service
public class WxConfigService extends BaseService<WxConfig> {

    @Autowired
    private WxConfigMapper wxConfigMapper;

    @Override
    public void insert(WxConfig wxConfig) {
        wxConfigMapper.insert(wxConfig);
    }

    public List<WxConfig> selectAll() {
        return wxConfigMapper.selectAll();
    }

    /**
     * 通过nickName查询商户微信公众账户配置
     *
     * @param shopperNickName
     * @return
     */
    public List<WxConfig> selectByNickName(String shopperNickName) {
        return wxConfigMapper.selectByNickName(shopperNickName);
    }

    @Override
    public void deleteById(Integer id) {
        wxConfigMapper.deleteById(id);
    }

    @Override
    public void update(WxConfig wxConfig) {
        wxConfigMapper.update(wxConfig);
    }

    @Override
    public WxConfig selectById(Integer id) {
        return wxConfigMapper.selectById(id);
    }

}
