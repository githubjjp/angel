package com.pingan.angel.qctest.service;

import com.pingan.angel.admin.api.mongodb.DeviceCustomerCodeEntity;

import java.util.Map;

public interface DeviceCustomerCodeService {

    void deleteBySnCode(String snCode);

    DeviceCustomerCodeEntity findBySnCode(String snCode);

    void add(DeviceCustomerCodeEntity deviceCustomer);

    void updateByMap(Map<String, Object> map,String snCode);
}
