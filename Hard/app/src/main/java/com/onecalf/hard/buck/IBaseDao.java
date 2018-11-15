package com.onecalf.hard.buck;

public interface IBaseDao<T> {

    /**
     * 插入数据库
     * @param entity
     * @return
     */
    int insert(T entity);

    /**
     * 更新
     * @param entity
     * @param where
     * @return
     */
    int update(T entity,T where);


}
