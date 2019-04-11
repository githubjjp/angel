package com.pingan.angel.qctest.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pingan.angel.admin.api.entity.PageResult;
import com.pingan.angel.admin.api.mysql.QcDeviceConfigEntity;


public interface QcDeviceConfigService {

    QcDeviceConfigEntity findByProductCode(String productCode);

    PageResult<QcDeviceConfigEntity> queryPage(IPage<QcDeviceConfigEntity> page, Wrapper<QcDeviceConfigEntity> queryWrapper);
}
