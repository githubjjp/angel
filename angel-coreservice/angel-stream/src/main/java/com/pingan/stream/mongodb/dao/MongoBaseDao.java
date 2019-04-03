package com.pingan.stream.mongodb.dao;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import java.util.List;

/**
 * MongoBaseDao基本操作
 * @param <T>
 */
public interface MongoBaseDao<T> {
    /**
     * 保存一个对象到 mongodb
     * @param bean
     * @return
     */
    public T save(T bean);

    /**
     * 根据 id 删除对象
     * @param t
     */
    public void deleteById(T t);

    /**
     * 根据对象的属性删除
     * @param t
     */
    public void deleteByCondition(T t);

    /**
     * 通过条件查询更新数据
     * @param query
     * @param update
     */
    public void update(Query query, Update update);

    /**
     * 根据 id 进行更新
     * @param id
     * @param t
     */
    public void updateById(String id, T t);

    /**
     * 通过条件查询实体 (集合)
     * @param query
     * @return
     */
    public List<T> find(Query query);

    /**
     * 根据条件查询集合
     * @param t
     * @return
     */
    public List<T> findByCondition(T t);

    /**
     * 通过一定的条件查询一个实体
     * @param query
     * @return
     */
    public T findOne(Query query);

    /**
     * 通过 ID 获取记录
     * @param id
     * @return
     */
    public T get(String id);

    /**
     * 通过 ID 获取记录, 并且指定了集合名 (表的意思)
     * @param id
     * @param collectionName
     * @return
     */
    public T get(String id, String collectionName);

    /**
     * 获取MongoTemplate
     * @return
     */
    public MongoTemplate getMongoTemplate();
}
