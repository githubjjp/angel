package com.pingan.angel.qctest.dao.impl;

import com.pingan.angel.admin.api.mongodb.QcDeviceHistoryEntity;
import com.pingan.angel.common.core.mongodb.impl.MongoBaseDaoImpl;
import com.pingan.angel.qctest.dao.QcDeviceHistoryDao;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class QcDeviceHistoryDaoImpl extends MongoBaseDaoImpl<QcDeviceHistoryEntity> implements QcDeviceHistoryDao {
    @Override
    public void updateMutil(Query query, Update update) {

    }
}
