package com.pingan.angel.qctest.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pingan.angel.admin.api.mysql.CoreCustomerSuperEntity;
import com.pingan.angel.common.core.mongodb.MongoBaseDao;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerSuperDao extends BaseMapper<CoreCustomerSuperEntity> {
}
