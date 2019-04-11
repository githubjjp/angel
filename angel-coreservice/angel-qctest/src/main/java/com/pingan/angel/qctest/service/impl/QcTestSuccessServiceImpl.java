package com.pingan.angel.qctest.service.impl;

import com.pingan.angel.admin.api.mongodb.QcTestSuccessDeviceEntity;
import com.pingan.angel.qctest.dao.QcTestSuccessDao;
import com.pingan.angel.qctest.service.QcTestSuccessServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service("qcTestSuccessService")
public class QcTestSuccessServiceImpl implements QcTestSuccessServcie {

    @Autowired
    private QcTestSuccessDao qcTestSuccessDao;

    /**
     * 通过整机码查询产测通过整机码记录
     * @param snCode 整机码
     * @return 整机码记录
     */
    @Override
    public QcTestSuccessDeviceEntity findBySnCode(String snCode) {
        return qcTestSuccessDao.findOne(Query.query(Criteria.where("snCode").is(snCode)));
    }

    @Override
    public void deleteBySnCode(String snCode) {
        QcTestSuccessDeviceEntity successDeviceEntity = new QcTestSuccessDeviceEntity();
        successDeviceEntity.setSnCode(snCode);
        qcTestSuccessDao.deleteByCondition(successDeviceEntity);
    }
}
