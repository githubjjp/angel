package com.pingan.angel.qctest.service.impl;

import com.pingan.angel.admin.api.mongodb.QcDeviceHistoryEntity;
import com.pingan.angel.qctest.dao.QcDeviceHistoryDao;
import com.pingan.angel.qctest.service.QcDeviceHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public class QcDeviceHistoryServiceImpl implements QcDeviceHistoryService {

    @Autowired
    private QcDeviceHistoryDao qcDeviceHistoryDao;

    @Override
    public QcDeviceHistoryEntity findById(String historyId) {
        return qcDeviceHistoryDao.findOne(Query.query(Criteria.where("id").is(historyId)));
    }

    @Override
    public boolean updateTestResult(boolean flag, String historyId) {
        qcDeviceHistoryDao.update(Query.query(Criteria.where("id").is(historyId)), Update.update("testSuccess", flag));
        QcDeviceHistoryEntity deviceHistory = findById(historyId);
        if (deviceHistory != null) {
            return flag == deviceHistory.isTestSuccess();
        }else{
            return false;
        }
    }

}
