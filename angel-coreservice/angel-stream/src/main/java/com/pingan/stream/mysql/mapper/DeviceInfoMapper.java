package com.pingan.stream.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pingan.angel.admin.api.mysql.DeviceEntity;
import org.springframework.stereotype.Repository;

import java.util.Map;
@Repository
public interface DeviceInfoMapper extends BaseMapper<DeviceEntity> {
    /**
     * 更新设备上下线状态
     * @param param
     */
    public void updateOnlineStatus(Map<String,Object> param);
    /**
     * 查询设备在离线状态
     * @param deviceId
     */
    public Map<String,Object> queryDeviceOnline(String deviceId);

    /**
     * 根据设备ID查询设备
     * @param param
     * @return
     */
    public DeviceEntity findByDeviceId(Map<String,String> param);
}
