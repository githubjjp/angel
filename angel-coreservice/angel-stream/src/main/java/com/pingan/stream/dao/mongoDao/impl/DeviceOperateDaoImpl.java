package com.pingan.stream.dao.mongoDao.impl;

import com.pingan.stream.dao.mongoDao.DeviceOperateDao;
import com.pingan.stream.entity.DeviceReportOrderEntity;
import org.springframework.stereotype.Component;

@Component("deviceOperateDao")
public class DeviceOperateDaoImpl  extends MongoBaseDaoImpl<DeviceReportOrderEntity> implements DeviceOperateDao {
}
