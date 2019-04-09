package com.pingan.angel.qctest.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.pingan.angel.admin.api.mongodb.DeviceCustomerCodeEntity;
import com.pingan.angel.qctest.dao.DeviceCustomerCodeDao;
import com.pingan.angel.qctest.service.DeviceCustomerCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("deviceCustomerCodeService")
public class DeviceCustomerCodeServiceImpl implements DeviceCustomerCodeService {

    @Autowired
    private DeviceCustomerCodeDao deviceCustomerCodeDao;

    @Override
    public void deleteBySnCode(String snCode) {
        deviceCustomerCodeDao.delete(Wrappers.<DeviceCustomerCodeEntity>query().lambda().eq(DeviceCustomerCodeEntity::getSnCode,snCode));
    }
}
