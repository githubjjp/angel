package com.pingan.angel.qctest.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pingan.angel.admin.api.mysql.DeviceStatusEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceStatusDao extends BaseMapper<DeviceStatusEntity> {
}
