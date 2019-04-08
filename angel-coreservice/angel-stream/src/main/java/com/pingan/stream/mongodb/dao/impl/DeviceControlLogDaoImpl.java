package com.pingan.stream.mongodb.dao.impl;

import com.pingan.angel.admin.api.mongodb.DeviceControlLogEntity;
import com.pingan.angel.common.core.mongodb.impl.MongoBaseDaoImpl;
import com.pingan.stream.mongodb.dao.DeviceControlLogDao;
import org.springframework.stereotype.Repository;

@Repository
public class DeviceControlLogDaoImpl extends MongoBaseDaoImpl<DeviceControlLogEntity> implements DeviceControlLogDao {
}
