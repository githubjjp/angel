package com.pingan.angel.qctest.dao;

import com.pingan.angel.admin.api.mongodb.QcTestSuccessDeviceEntity;
import com.pingan.angel.common.core.mongodb.MongoBaseDao;

public interface QCTestSuccessDao extends MongoBaseDao<QcTestSuccessDeviceEntity> {

    /**
     * 根据整机码查询产测通过整机码记录
     * @param snCode
     * @return
     */
    QcTestSuccessDeviceEntity findBySnCode(String snCode);
}
