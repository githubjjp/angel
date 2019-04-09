package com.pingan.angel.qctest.service;

import com.pingan.angel.admin.api.entity.ApiResult;
import com.pingan.angel.admin.api.mongodb.QcDeviceEntity;
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
    private QcDeviceService qcDeviceService;
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
        if (unionEntity==null){
            return ApiResult.error("整机码错误！");
        }
        HashMap map = new HashMap<String, Object>();
        map.put("isTestSuccess", qcSuccessEntity != null ? true : false);
        map.put("barcodeId", unionEntity.getBarcodeId());
        return ApiResult.success(map);
    }

    /**
     * 扫码开始产测
     *
     * @param snCode
     * @param isWifi
     * @param mac
     * @return
     */
    public Map<String, Object> scanTest(String snCode, boolean isWifi, String mac) {
        QcDeviceEntity qcDevice = qcDeviceService.findBySnCode(snCode);
        if (qcDevice == null) {
            QcDeviceUnionInfoEntity deviceUnionInfo = deviceUnionInfoService.findBySnCode(snCode);
            String barCodeId = null;
            String proId = null;
            if (deviceUnionInfo != null) {
                barCodeId = deviceUnionInfo.getBarcodeId();
                proId = deviceUnionInfo.getProductCode();
                //若产品id为空则发送iot请求获取产品id
                if(StringUtils.isEmpty(proId)){

                }
            } else {

            }
        } else {

        }
        return null;
    }
}
