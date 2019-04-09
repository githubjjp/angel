package com.pingan.stream.mysql.mapper;

import com.pingan.angel.admin.api.mysql.DeviceStatusEntity;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface DeviceStatusMapper {
    /**
     * 保存设备状态信息
     * @param dto
     */
    public void insert(DeviceStatusEntity dto);

    /**
     * 更新设备状态信息
     * @param dto
     */
    public void update(DeviceStatusEntity dto);

    /**
     * 根据条件查询设备状态
     * @param param
     * @return
     */
    public DeviceStatusEntity findByCondition(Map<String,Object> param);
}
