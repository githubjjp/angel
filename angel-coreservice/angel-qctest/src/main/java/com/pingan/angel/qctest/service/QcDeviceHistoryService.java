package com.pingan.angel.qctest.service;

import com.pingan.angel.admin.api.mongodb.QcDeviceHistoryEntity;

public interface QcDeviceHistoryService {

    QcDeviceHistoryEntity findById(String historyId);
}
