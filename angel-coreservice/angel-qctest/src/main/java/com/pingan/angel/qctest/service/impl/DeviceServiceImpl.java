package com.pingan.angel.qctest.service.impl;

import com.pingan.angel.admin.api.mysql.DeviceEntity;
import com.pingan.angel.qctest.dao.DeviceDao;
import com.pingan.angel.qctest.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service("deviceService")
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceDao deviceDao;

    @Override
    public DeviceEntity findOne(String deviceId) {
        return deviceDao.findOne(Query.query(Criteria.where("deviceId").is(deviceId)));
    }
}
