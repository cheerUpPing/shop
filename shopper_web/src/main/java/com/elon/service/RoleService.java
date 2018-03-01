package com.elon.service;

import com.elon.entity.Role;
import com.elon.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 2017/11/21 10:48.
 * <p>
 * Email: cheerUpPing@163.com
 */
@Service
public class RoleService extends BaseService<Role> {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public void insert(Role role) {
        roleMapper.insert(role);
    }



    public List<Role> selectAll() {
        return roleMapper.selectAll();
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void update(Role role) {

    }

    @Override
    public Role selectById(Integer id) {
        return null;
    }

}
