package com.pingan.angel.qctest.service.impl;

import com.pingan.angel.admin.api.mongodb.QcDeviceUnionInfoEntity;
import com.pingan.angel.qctest.dao.DeviceUnionInfoDao;
import com.pingan.angel.qctest.service.DeviceUnionInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("deviceUnionInfoService")
public class DeviceUnionInfoServiceImpl implements DeviceUnionInfoService {

    @Autowired
    private DeviceUnionInfoDao deviceUnionInfoDao;

    @Override
    public QcDeviceUnionInfoEntity findBySnCode(String snCode) {
        return deviceUnionInfoDao.findBySnCode(snCode);
    }
}

