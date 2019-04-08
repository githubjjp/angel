package com.pingan.angel.qctest.service;

import com.pingan.angel.admin.api.mongodb.QcDeviceUnionInfoEntity;
import com.pingan.angel.admin.api.mongodb.QcTestSuccessDeviceEntity;

/**
 * 产测设备关联信息
 */
public interface DeviceUnionInfoService {

    QcDeviceUnionInfoEntity findBySnCode(String snCode);
}
