package com.pingan.stream.mysql.mapper;

import com.pingan.angel.admin.api.mysql.DeviceErrorEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceErrorMapper {
    /**
     * 新增
     * @param dto
     */
    public void insert(DeviceErrorEntity dto);

    /**
     * 更新
     * @param dto
     */
    public void update(DeviceErrorEntity dto);
}
