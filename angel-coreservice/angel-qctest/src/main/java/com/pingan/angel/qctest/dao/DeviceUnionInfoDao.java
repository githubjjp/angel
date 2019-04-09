package com.pingan.angel.qctest.dao;

import com.pingan.angel.admin.api.mongodb.QcDeviceUnionInfoEntity;
import com.pingan.angel.common.core.mongodb.MongoBaseDao;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceUnionInfoDao extends MongoBaseDao<QcDeviceUnionInfoEntity> {

    QcDeviceUnionInfoEntity findBySnCode(String snCode);
}
