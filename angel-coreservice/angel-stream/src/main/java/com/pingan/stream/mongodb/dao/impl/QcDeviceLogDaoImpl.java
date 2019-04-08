package com.pingan.stream.mongodb.dao.impl;

import com.pingan.angel.admin.api.mongodb.QcDeviceLogEntity;
import com.pingan.angel.common.core.mongodb.impl.MongoBaseDaoImpl;
import com.pingan.stream.mongodb.dao.QcDeviceLogDao;
import org.springframework.stereotype.Repository;

@Repository
public class QcDeviceLogDaoImpl extends MongoBaseDaoImpl<QcDeviceLogEntity> implements QcDeviceLogDao {
}
