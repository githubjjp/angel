package com.pingan.stream.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pingan.angel.admin.api.mysql.FilterElementEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface FilterElementMapper extends BaseMapper<FilterElementEntity> {

    /**
     * 根据条件查询
     * @param deviceId
     * @return
     */
    public FilterElementEntity findByDeviceId(String deviceId);
}
