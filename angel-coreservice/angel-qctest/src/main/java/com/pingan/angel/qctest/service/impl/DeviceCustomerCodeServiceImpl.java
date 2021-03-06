package com.pingan.angel.qctest.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.pingan.angel.admin.api.mongodb.DeviceCustomerCodeEntity;
import com.pingan.angel.qctest.dao.DeviceCustomerCodeDao;
import com.pingan.angel.qctest.service.DeviceCustomerCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("deviceCustomerCodeService")
public class DeviceCustomerCodeServiceImpl implements DeviceCustomerCodeService {

    @Autowired
    private DeviceCustomerCodeDao deviceCustomerCodeDao;

    @Override
    public void deleteBySnCode(String snCode) {
        DeviceCustomerCodeEntity customerCode = new DeviceCustomerCodeEntity();
        customerCode.setSnCode(snCode);
        deviceCustomerCodeDao.deleteByCondition(customerCode);
    }

    @Override
    public DeviceCustomerCodeEntity findBySnCode(String snCode) {
        return deviceCustomerCodeDao.findOne(Query.query(Criteria.where("snCode").is(snCode)));
    }

    @Override
    public void add(DeviceCustomerCodeEntity deviceCustomer) {
        deviceCustomerCodeDao.save(deviceCustomer);
    }

    @Override
    public void updateByMap(Map<String, Object> map,String snCode) {
        if (CollectionUtils.isEmpty(map)){
            return;
        }
        Update update = new Update();
        for (String key:map.keySet()){
            update.set(key,map.get(key));
        }
        deviceCustomerCodeDao.updateFirst(Query.query(Criteria.where("snCode").is(snCode)),update);
    }
}
