package com.pingan.angel.qctest.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pingan.angel.admin.api.mysql.FilterElementEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceFilterDao extends BaseMapper<FilterElementEntity> {
}
