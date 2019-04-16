package com.pingan.angel.qctest.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pingan.angel.admin.api.mysql.DeviceEntity;
import com.pingan.angel.admin.api.mysql.OrderProductEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao extends BaseMapper<OrderProductEntity> {
}
