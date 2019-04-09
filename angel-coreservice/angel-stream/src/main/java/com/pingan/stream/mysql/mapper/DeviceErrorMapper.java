package com.pingan.stream.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pingan.angel.admin.api.mysql.DeviceErrorEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceErrorMapper extends BaseMapper<DeviceErrorEntity> {

    /**
     * 查询设备故障信息
     * @param deviceId
     * @return
     */
    public DeviceErrorEntity findByDeviceId(String deviceId);
}
