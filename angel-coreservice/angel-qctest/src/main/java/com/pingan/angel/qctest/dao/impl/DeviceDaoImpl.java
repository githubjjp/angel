package com.pingan.angel.qctest.dao.impl;

import com.pingan.angel.admin.api.mysql.DeviceEntity;
import com.pingan.angel.common.core.mongodb.impl.MongoBaseDaoImpl;
import com.pingan.angel.qctest.dao.DeviceDao;
import org.springframework.stereotype.Component;

@Component
public class DeviceDaoImpl extends MongoBaseDaoImpl<DeviceEntity> implements DeviceDao {
}
