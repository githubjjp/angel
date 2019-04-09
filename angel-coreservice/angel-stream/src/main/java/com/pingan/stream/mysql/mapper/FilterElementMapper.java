package com.pingan.stream.mysql.mapper;

import com.pingan.angel.admin.api.mysql.FilterElementEntity;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface FilterElementMapper {
    /**
     * 新增
     * @param dto
     */
    public void insert(FilterElementEntity dto);

    /**
     * 更新
     * @param dto
     */
    public void update(FilterElementEntity dto);

    /**
     * 根据条件查询
     * @param param
     * @return
     */
    public FilterElementEntity findByCondition(Map<String,Object> param);
}
