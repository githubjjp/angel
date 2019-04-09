package com.pingan.angel.qctest.service;

import com.pingan.angel.admin.api.mysql.DeviceEntity;

public interface DeviceService {

    DeviceEntity findOne(String deviceId);

    void updateQCTestUndoById(String deviceId);
}
