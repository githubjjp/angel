package com.pingan.angel.qctest.service;

import com.pingan.angel.admin.api.mongodb.QcTestSuccessDeviceEntity;

/**
 * 产测通过服务
 */
public interface QcTestSuccessServcie {

    /**
     * 使用整机码查询产测通过整机码记录
     * @param snCode
     * @return
     */
    QcTestSuccessDeviceEntity findBySnCode(String snCode);

    void deleteBySnCode(String snCode);

    /**
     * 添加一条产测成功记录
     * @param qcsuccessDevice
     */
    void add(QcTestSuccessDeviceEntity qcsuccessDevice);
}
