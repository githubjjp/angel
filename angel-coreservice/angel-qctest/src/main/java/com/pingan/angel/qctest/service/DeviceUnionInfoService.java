package com.pingan.angel.qctest.service;

import com.pingan.angel.admin.api.mongodb.QcDeviceUnionInfoEntity;
import com.pingan.angel.admin.api.mongodb.QcTestSuccessDeviceEntity;

public interface DeviceUnionInfoService {

    QcDeviceUnionInfoEntity findBySnCode(String snCode);
}
