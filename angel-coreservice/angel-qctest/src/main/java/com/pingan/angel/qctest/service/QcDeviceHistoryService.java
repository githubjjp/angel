package com.pingan.angel.qctest.service;

import com.pingan.angel.admin.api.mongodb.QcDeviceHistoryEntity;

import java.util.Map;

public interface QcDeviceHistoryService {

    QcDeviceHistoryEntity findById(String historyId);

    boolean updateTestResult(boolean flag, String historyId);

    String add(QcDeviceHistoryEntity qcDeviceHistoryEntity);

    boolean updateActiveTimeById(QcDeviceHistoryEntity deviceHistoryEntity);

    boolean updateByMap(Map<String,Object> paramMap, String id);
}
