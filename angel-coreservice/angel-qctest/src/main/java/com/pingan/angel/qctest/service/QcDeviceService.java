package com.pingan.angel.qctest.service;

import com.pingan.angel.admin.api.mongodb.QcDeviceEntity;

public interface QcDeviceService {

    QcDeviceEntity findBySnCode(String snCode);
}