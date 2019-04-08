package com.pingan.stream.mongodb.dao.impl;

import com.pingan.angel.admin.api.mongodb.ErrorLogEntity;
import com.pingan.angel.common.core.mongodb.impl.MongoBaseDaoImpl;
import com.pingan.stream.mongodb.dao.ErrorLogDao;
import org.springframework.stereotype.Repository;

@Repository
public class ErrorLogDaoImpl extends MongoBaseDaoImpl<ErrorLogEntity> implements ErrorLogDao {
}
