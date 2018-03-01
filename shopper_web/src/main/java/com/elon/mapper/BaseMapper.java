package com.elon.mapper;

/**
 * 2017/11/21 10:12.
 * <p>
 * Email: cheerUpPing@163.com
 */
public interface BaseMapper<T> {

    void insert(T t);

     void deleteById(Integer id);

    T selectById(Integer id);

    void update(T t);

}