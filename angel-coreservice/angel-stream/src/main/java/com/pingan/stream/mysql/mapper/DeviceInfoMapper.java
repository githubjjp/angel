package com.pingan.stream.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pingan.angel.admin.api.mysql.DeviceEntity;
import org.springframework.stereotype.Repository;

import java.util.Map;
@Repository
public interface DeviceInfoMapper extends BaseMapper<DeviceEntity> {

    /**
     * 根据设备ID查询设备
     * @param param
     * @return
     */
    public DeviceEntity findByDeviceId(Map<String,String> param);
}
