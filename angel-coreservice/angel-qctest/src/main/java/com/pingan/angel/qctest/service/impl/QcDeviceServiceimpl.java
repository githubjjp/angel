package com.pingan.angel.qctest.service.impl;

import com.pingan.angel.admin.api.mongodb.QcDeviceEntity;
import com.pingan.angel.qctest.dao.QcDeviceDao;
import com.pingan.angel.qctest.service.QcDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service("qcDeviceService")
public class QcDeviceServiceimpl implements QcDeviceService {

    @Autowired
    private QcDeviceDao qcDeviceDao;

    @Override
    public QcDeviceEntity findBySnCode(String snCode) {
        return qcDeviceDao.findOne(Query.query(Criteria.where("snCode").is(snCode)));
    }

    @Override
    public QcDeviceEntity findByBarCodeId(String barcodeId) {
        return qcDeviceDao.findOne(Query.query(Criteria.where("barcodeId").is(barcodeId)));
    }


}
