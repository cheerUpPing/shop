package com.elon.service;

/**
 * 2017/11/21 10:46.
 * <p>
 * Email: cheerUpPing@163.com
 */
public abstract class BaseService<T> {

    public abstract void insert(T t);

    public abstract void deleteById(Integer id);

    public abstract void update(T t);

    public abstract T selectById(Integer id);


}
