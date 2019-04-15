package com.pingan.angel.qctest.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.pingan.angel.admin.api.mysql.DeviceEntity;
import com.pingan.angel.qctest.dao.DeviceDao;
import com.pingan.angel.qctest.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("deviceService")
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceDao deviceDao;

    @Override
    public DeviceEntity findOneByDeviceId(String deviceId) {
        return deviceDao.selectOne(Wrappers.<DeviceEntity>query()
                .lambda().eq(DeviceEntity::getDeviceId, deviceId));
    }

    /**
     * 更新设备信息中产测完成标记为未产测完成
     * @param deviceId
     */
    @Override
    public void updateQCTestUndoById(String deviceId) {
        DeviceEntity device = new DeviceEntity();
        device.setIsProductTest("N");
        deviceDao.update(device,Wrappers.<DeviceEntity>query().lambda().eq(DeviceEntity::getDeviceId, deviceId));
    }

    @Override
    public DeviceEntity findOneBySnCode(String snCode) {
        return deviceDao.selectOne(Wrappers.<DeviceEntity>query().lambda().eq(DeviceEntity::getSnCode,snCode));
    }

    /**
     * 删除设备表中的数据
     */
    @Override
    public void deleteDeviceInfoBySnCode(String snCode) {
        deviceDao.delete(Wrappers.<DeviceEntity>query().lambda().eq(DeviceEntity::getSnCode,snCode));
    }
}
