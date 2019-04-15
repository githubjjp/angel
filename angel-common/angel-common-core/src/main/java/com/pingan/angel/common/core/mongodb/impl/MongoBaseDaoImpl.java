package com.pingan.angel.common.core.mongodb.impl;


import com.pingan.angel.common.core.mongodb.MongoBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 *  mongoDB操作类
 * @param <T>
 */
public abstract  class MongoBaseDaoImpl<T extends Serializable> implements MongoBaseDao<T> {
    @Autowired
    @Qualifier("mongoTemplate")
    protected MongoTemplate mongoTemplate;

    /**
     * 保存一个对象到 mongodb
     * @param bean
     * @return
     */
    public T save(T bean) {
        mongoTemplate.save(bean);
        return bean;
    }

    /**
     * 根据 id 删除对象
     * @param t
     */
    public void deleteById(T t) {
        mongoTemplate.remove(t);
    }


    /**
     * 根据对象的属性删除
     * @param t
     */
    public void deleteByCondition(T t) {
        Query query = buildBaseQuery(t);
        mongoTemplate.remove(query, getEntityClass());
    }

    /**
     * 通过条件查询更新数据
     * @param query
     * @param update
     */
    public void updateMulti(Query query, Update update) {
        mongoTemplate.updateMulti(query, update, this.getEntityClass());
    }

    /**
     * 通过条件更新查询到的第一个数据
     * @param query
     * @param update
     */
    public void updateFirst(Query query, Update update){
        mongoTemplate.updateFirst(query,update,this.getEntityClass());
    }

    /**
     * 根据 id 进行更新
     * @param id
     * @param t
     */
    public void updateById(String id, T t) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        Update update = buildBaseUpdate(t);
        updateFirst(query, update);
    }

    /**
     * 通过条件查询实体 (集合)
     * @param query
     * @return
     */
    public List<T> find(Query query) {
        return mongoTemplate.find(query, this.getEntityClass());
    }

    public List<T> findByCondition(T t) {
        Query query = buildBaseQuery(t);
        return mongoTemplate.find(query, getEntityClass());
    }

    /**
     * 通过一定的条件查询一个实体
     * @param query
     * @return
     */
    public T findOne(Query query) {
        return mongoTemplate.findOne(query, this.getEntityClass());
    }


    /**
     * 通过 ID 获取记录
     * @param id
     * @return
     */
    public T get(String id) {
        return mongoTemplate.findById(id, this.getEntityClass());
    }

    /**
     * 通过 ID 获取记录, 并且指定了集合名 (表的意思)
     * @param id
     * @param collectionName
     * @return
     */
    public T get(String id, String collectionName) {
        return mongoTemplate.findById(id, this.getEntityClass(), collectionName);
    }

    /**
     * 根据 vo 构建查询条件 Query
     * @param t
     * @return
     */
    public Query buildBaseQuery(T t) {
        Query query = new Query();
        Field[] fields = t.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(t);
                if (value != null) {
                    org.springframework.data.mongodb.core.mapping.Field queryField = field.getAnnotation(org.springframework.data.mongodb.core.mapping.Field.class);
                    if (queryField != null) {
                        query.addCriteria(Criteria.where(queryField.value()).is(value));
                    }else{
                        query.addCriteria(Criteria.where(field.getName()).is(value));
                    }
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return query;
    }

    public Update buildBaseUpdate(T t) {
        Update update = new Update();

        Field[] fields = t.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(t);
                if (value != null) {
                    update.set(field.getName(), value);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return update;
    }

    /**
     * 获取需要操作的实体类 class
     * @return
     */
    @SuppressWarnings("unchecked")
    protected Class<T> getEntityClass() {
        return ((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }
}
