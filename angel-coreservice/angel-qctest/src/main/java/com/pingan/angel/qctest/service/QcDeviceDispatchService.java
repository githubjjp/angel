package com.pingan.angel.qctest.service;

import com.pingan.angel.admin.api.entity.ApiResult;
import com.pingan.angel.admin.api.entity.ResultCode;
import com.pingan.angel.admin.api.mongodb.QcDeviceUnionInfoEntity;
import com.pingan.angel.admin.api.mongodb.QcTestSuccessDeviceEntity;
import com.pingan.angel.admin.api.mysql.DeviceEntity;
import org.apache.commons.lang.StringUtils;
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
    @Autowired
    private DeviceStatusService deviceStatusService;
    @Autowired
    private DeviceCustomerCodeService deviceCustomerCodeService;


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

    /**
     * 扫码开始产测
     * @param snCode 整机码
     * @param isWifi 是否支持wifi
     * @param mac mac地址
     * @return
     */
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
            if(StringUtils.isEmpty(successDevice.getDeviceId())){
                return ApiResult.error("重新产测失败，该设备为通过产测");
            }
            //逻辑删除设备表中的设备产测状态
            deviceService.updateQCTestUndoById(successDevice.getDeviceId());
            //逻辑删除设备状态表和设备滤芯信息表中的记录
            deviceStatusService.deleteByDeviceId(successDevice.getDeviceId());
            //删除产测成功表中的记录
            qcTestSuccessServcie.deleteBySnCode(snCode);
            //通过整机码删除设备大客户批次码关联记录
            deviceCustomerCodeService.deleteBySnCode(snCode);
        }else{
            return ApiResult.error("重新产测失败，该设备未产测过");
        }
        return ApiResult.success("重新产测成功，请重启设备再次扫码产测");
    }
}
