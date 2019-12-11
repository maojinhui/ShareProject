package com.mao.mysql.sqlie;

public interface IBaseDao<T> {

    /**
     * 插入
     * @param entity
     * @return
     */
    Long insert(T entity);


}
