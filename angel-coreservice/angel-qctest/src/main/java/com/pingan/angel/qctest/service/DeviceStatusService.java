package com.pingan.angel.qctest.service;

import com.pingan.angel.admin.api.mysql.DeviceStatusEntity;

/**
 * 设备状态服务，包括设备滤芯操作
 */
public interface DeviceStatusService {

    void deleteByDeviceId(String deviceId);

    DeviceStatusEntity findByDeviceId(String deviceId);
}
