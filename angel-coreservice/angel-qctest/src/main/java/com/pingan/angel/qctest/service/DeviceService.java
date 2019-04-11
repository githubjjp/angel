package com.pingan.angel.qctest.service;

import com.pingan.angel.admin.api.mysql.DeviceEntity;

public interface DeviceService {

    DeviceEntity findOneByDeviceId(String deviceId);

    void updateQCTestUndoById(String deviceId);

    DeviceEntity findOneBySnCode(String snCode);
}
