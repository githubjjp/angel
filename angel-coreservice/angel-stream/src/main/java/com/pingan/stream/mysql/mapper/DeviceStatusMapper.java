package com.pingan.stream.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pingan.angel.admin.api.mysql.DeviceStatusEntity;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface DeviceStatusMapper extends BaseMapper<DeviceStatusEntity> {

    /**
     * 根据条件查询设备状态
     * @param deviceId
     * @return
     */
    public DeviceStatusEntity findByDeviceId(String deviceId);

    /**
     * 根据设备ID更新设备
     * @param param
     */
    public void updateByDeviceId(Map<String,Object> param);
}
