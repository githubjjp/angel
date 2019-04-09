package com.pingan.angel.qctest.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.pingan.angel.admin.api.mysql.DeviceStatusEntity;
import com.pingan.angel.admin.api.mysql.FilterElementEntity;
import com.pingan.angel.qctest.dao.DeviceFilterDao;
import com.pingan.angel.qctest.dao.DeviceStatusDao;
import com.pingan.angel.qctest.service.DeviceStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("deviceStatusService")
public class DeviceStatusServiceImpl implements DeviceStatusService {

    @Autowired
    private DeviceStatusDao deviceStatusDao;
    @Autowired
    private DeviceFilterDao deviceFilterDao;

    /**
     * 删除设备状态信息和设备滤芯信息，产测部分的
     * @param deviceId
     */
    @Override
    public void deleteByDeviceId(String deviceId) {
        //删除设备状态表数据
        deviceStatusDao.delete(Wrappers.<DeviceStatusEntity>query().lambda().eq(DeviceStatusEntity::getDeviceId,deviceId));
        //删除设备滤芯数据
        deviceFilterDao.delete(Wrappers.<FilterElementEntity>query().lambda().eq(FilterElementEntity::getDeviceId,deviceId));
    }
}
