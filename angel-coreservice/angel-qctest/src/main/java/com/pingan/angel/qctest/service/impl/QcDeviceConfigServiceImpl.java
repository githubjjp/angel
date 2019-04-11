package com.pingan.angel.qctest.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.pingan.angel.admin.api.entity.PageResult;
import com.pingan.angel.admin.api.mysql.QcDeviceConfigEntity;
import com.pingan.angel.qctest.dao.QcDeviceConfigDao;
import com.pingan.angel.qctest.service.QcDeviceConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("qcDeviceConfigService")
public class QcDeviceConfigServiceImpl implements QcDeviceConfigService {

    @Autowired
    private QcDeviceConfigDao qcDeviceConfigDao;

    /**
     * 根据产品code查询产测设备的配置信息
     * @param productCode
     */
    @Override
    public QcDeviceConfigEntity findByProductCode(String productCode) {
        return qcDeviceConfigDao.selectOne(Wrappers.<QcDeviceConfigEntity>query().lambda().eq(QcDeviceConfigEntity::getProductCode,productCode));
    }

    @Override
    public PageResult<QcDeviceConfigEntity> queryPage(IPage<QcDeviceConfigEntity> page, Wrapper<QcDeviceConfigEntity> queryWrapper) {
        IPage<QcDeviceConfigEntity> resultPage = qcDeviceConfigDao.selectPage(page,queryWrapper);
        if(resultPage==null){
            return new PageResult<QcDeviceConfigEntity>(1,0,new ArrayList<QcDeviceConfigEntity>(),0);
        }
        PageResult<QcDeviceConfigEntity> result = new PageResult<QcDeviceConfigEntity>((int)resultPage.getCurrent(),(int)resultPage.getPages(),resultPage.getRecords(),(int)resultPage.getSize());
        return result;
    }
}
