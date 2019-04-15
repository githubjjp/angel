package com.pingan.stream.mongodb.dao.impl;

import com.pingan.angel.admin.api.mongodb.QcDeviceEntity;
import com.pingan.angel.common.core.mongodb.impl.MongoBaseDaoImpl;
import com.pingan.stream.mongodb.dao.QcDeviceInfoDao;
import org.springframework.stereotype.Repository;

@Repository
public class QcDeviceInfoDaoImpl extends MongoBaseDaoImpl<QcDeviceEntity> implements QcDeviceInfoDao {
}
