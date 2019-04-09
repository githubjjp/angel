package com.pingan.angel.qctest.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pingan.angel.admin.api.mysql.BaseEntity;
import com.pingan.angel.admin.api.mysql.DeviceEntity;
import com.pingan.angel.common.core.mongodb.MongoBaseDao;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceDao extends BaseMapper<DeviceEntity> {

    DeviceEntity queryById(String deviceId);
}
