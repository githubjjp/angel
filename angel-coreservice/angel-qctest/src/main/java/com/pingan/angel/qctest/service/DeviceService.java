package com.pingan.angel.qctest.service;

import com.pingan.angel.admin.api.mysql.DeviceEntity;

public interface DeviceService {

    DeviceEntity findOneByDeviceId(String deviceId);

    void updateQCTestUndoById(String deviceId);

    DeviceEntity findOneBySnCode(String snCode);

    /**
     * 删除设备表中的数据
     */
    void deleteDeviceInfoBySnCode(String snCode);

    /**
     * 产测完成添加一条记录
     * @param deviceEntity
     */
    void add(DeviceEntity deviceEntity);
}
