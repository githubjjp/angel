package com.pingan.angel.qctest.service;

import com.pingan.angel.admin.api.entity.ApiResult;
import com.pingan.angel.admin.api.mongodb.QcDeviceUnionInfoEntity;
import com.pingan.angel.admin.api.mongodb.QcTestSuccessDeviceEntity;
import com.pingan.angel.admin.api.mysql.DeviceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 产测api调度服务
 */
@Service
public class QcDeviceDispatchService {

    @Autowired
    private QcTestSuccessServcie qcTestSuccessServcie;
    @Autowired
    private DeviceUnionInfoService deviceUnionInfoService;
    @Autowired
    private DeviceService deviceService;


    /**
     * 判断产测是否通过服务
     *
     * @param snCode 整机码
     * @return
     */
    public Map<String, Object> isTestSuccess(String snCode) {
        QcTestSuccessDeviceEntity qcSuccessEntity = qcTestSuccessServcie.findBySnCode(snCode);
        //无论产测是否通过都要去查询到对应的配件码
        QcDeviceUnionInfoEntity unionEntity = deviceUnionInfoService.findBySnCode(snCode);
        if (unionEntity == null) {
            return ApiResult.error("整机码错误！");
        }
        HashMap map = new HashMap<String, Object>();
        map.put("isTestSuccess", qcSuccessEntity != null ? true : false);
        map.put("barcodeId", unionEntity.getBarcodeId());
        return ApiResult.success(map);
    }

    public Map<String, Object> scanTest(String snCode, boolean isWifi, String mac) {
        return null;
    }

    /**
     * 重新产测
     *
     * @param snCode 整机码
     * @return
     */
    public Map<String, Object> reQcDevice(String snCode) {
        QcTestSuccessDeviceEntity successDevice = qcTestSuccessServcie.findBySnCode(snCode);
        if (successDevice != null) {
            DeviceEntity device = deviceService.findOne(successDevice.getDeviceId());//设备信息

            //修改设备表中的是否产测为否

            //逻辑删除设备表中的设备产测状态
            //逻辑删除设备状态表中的
        }else{
            return ApiResult.error("重新产测失败，该设备未产测过");
        }
        return null;
    }
}
