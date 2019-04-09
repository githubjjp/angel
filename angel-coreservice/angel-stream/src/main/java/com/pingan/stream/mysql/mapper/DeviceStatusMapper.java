package com.pingan.stream.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pingan.angel.admin.api.mysql.DeviceStatusEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceStatusMapper extends BaseMapper<DeviceStatusEntity> {

    /**
     * 根据条件查询设备状态
     * @param deviceId
     * @return
     */
    public DeviceStatusEntity findByDeviceId(String deviceId);
}
