package com.pingan.angel.qctest.service.impl;

import com.pingan.angel.admin.api.mongodb.QcDeviceHistoryEntity;
import com.pingan.angel.qctest.dao.QcDeviceHistoryDao;
import com.pingan.angel.qctest.service.QcDeviceHistoryService;
import com.pingan.stream.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("qcDeviceHistoryService")
public class QcDeviceHistoryServiceImpl implements QcDeviceHistoryService {

    @Autowired
    private QcDeviceHistoryDao qcDeviceHistoryDao;

    @Override
    public QcDeviceHistoryEntity findById(String historyId) {
        return qcDeviceHistoryDao.findOne(Query.query(Criteria.where("id").is(historyId)));
    }

    @Override
    public boolean updateTestResult(boolean flag, String historyId) {
        qcDeviceHistoryDao.updateMutil(Query.query(Criteria.where("id").is(historyId)), Update.update("testSuccess", flag));
        QcDeviceHistoryEntity deviceHistory = findById(historyId);
        if (deviceHistory != null) {
            return flag == deviceHistory.isTestSuccess();
        }else{
            return false;
        }
    }

    /**
     * 添加产测记录表
     * @param qcDeviceHistoryEntity
     * @return
     */
    @Override
    public String add(QcDeviceHistoryEntity qcDeviceHistoryEntity) {
        QcDeviceHistoryEntity deviceHistory =  qcDeviceHistoryDao.save(qcDeviceHistoryEntity);
        if (deviceHistory != null && StringUtils.isNotEmpty(deviceHistory.getId())){
            return deviceHistory.getId();
        }
        return null;
    }

    /**
     * 使用实体对象来更新设备记录表
     * @param deviceHistoryEntity 需要更新设置的对象
     * @return
     */
    @Override
    public boolean updateActiveTimeById(QcDeviceHistoryEntity deviceHistoryEntity) {
        qcDeviceHistoryDao.updateFirst(Query.query(Criteria.where("id").is(deviceHistoryEntity.getId())),
                Update.update("activeTime",deviceHistoryEntity.getActiveTime())
                        .update("testAccount",deviceHistoryEntity.getTestAccount())
                        .update("testUserName",deviceHistoryEntity.getTestUserName()));

        QcDeviceHistoryEntity history = qcDeviceHistoryDao.findOne(
                Query.query(Criteria.where("id").is(deviceHistoryEntity.getId())));

        if (history.getActiveTime().equals(deviceHistoryEntity.getActiveTime())){
            return true;
        }
        return false;
    }

    /**
     * 更新map中的字段
     * @param paramMap
     * @param id
     * @return
     */
    @Override
    public boolean updateByMap(Map<String,Object> paramMap, String id) {
        Update update = new Update();
        for (String key: paramMap.keySet()) {
            update.set(key,paramMap.get(key));
        }
        qcDeviceHistoryDao.updateFirst(Query.query(Criteria.where("id").is(id)),update);
        return true;
    }

}
