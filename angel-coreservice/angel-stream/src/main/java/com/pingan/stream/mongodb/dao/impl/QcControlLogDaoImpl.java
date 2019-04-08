package com.pingan.stream.mongodb.dao.impl;

import com.pingan.angel.admin.api.mongodb.QcControlLogEntity;
import com.pingan.angel.common.core.mongodb.impl.MongoBaseDaoImpl;
import com.pingan.stream.mongodb.dao.QcControlLogDao;
import org.springframework.stereotype.Repository;

@Repository
public class QcControlLogDaoImpl extends MongoBaseDaoImpl<QcControlLogEntity> implements QcControlLogDao {
}
