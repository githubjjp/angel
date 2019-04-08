package com.pingan.stream.mongodb.dao.impl;

import com.pingan.angel.admin.api.mongodb.DeviceLogEntity;
import com.pingan.angel.common.core.mongodb.impl.MongoBaseDaoImpl;
import com.pingan.stream.mongodb.dao.DeviceLogDao;
import org.springframework.stereotype.Repository;

@Repository
public class DeviceLogDaoImpl extends MongoBaseDaoImpl<DeviceLogEntity> implements DeviceLogDao {
}
