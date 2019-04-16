package com.pingan.angel.qctest.service.impl;

import com.pingan.angel.admin.api.mongodb.QcDeviceEntity;
import com.pingan.angel.qctest.dao.QcDeviceDao;
import com.pingan.angel.qctest.service.QcDeviceService;
import com.pingan.stream.utils.StringUtils;
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

    /**
     * 新增产测设备，返回产测设备表id
     * @param qcDevice
     * @return
     */
    @Override
    public String add(QcDeviceEntity qcDevice) {
        QcDeviceEntity qcDeviceResult = qcDeviceDao.save(qcDevice);
        if (qcDeviceResult!=null && StringUtils.isNotEmpty(qcDeviceResult.getId())){
            return qcDeviceResult.getId();
        }
        return null;
    }

    /**
     * 根据字段更新产测设备信息
     * @param qcDevice
     * @return
     */
    @Override
    public String updateById(QcDeviceEntity qcDevice) {
        qcDeviceDao.updateById(qcDevice.getId(),qcDevice);
        return null;
    }


}
