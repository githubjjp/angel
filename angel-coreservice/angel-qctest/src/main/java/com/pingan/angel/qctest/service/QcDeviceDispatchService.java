package com.pingan.angel.qctest.service;

import cn.hutool.json.JSONString;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONReader;
import com.pingan.angel.admin.api.entity.ApiResult;
import com.pingan.angel.admin.api.entity.ResultCode;
import com.pingan.angel.admin.api.mongodb.QcDeviceEntity;
import com.pingan.angel.admin.api.mongodb.QcDeviceHistoryEntity;
import com.pingan.angel.admin.api.mongodb.QcDeviceUnionInfoEntity;
import com.pingan.angel.admin.api.mongodb.QcTestSuccessDeviceEntity;
import com.pingan.angel.admin.api.mysql.DeviceEntity;
import com.pingan.stream.Service.IssueCmdService;
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
    @Autowired
    private IssueCmdService issueCmdService;
    @Autowired
    private QcDeviceService qcDeviceService;
    @Autowired
    private QcDeviceHistoryService qcDeviceHistoryService;


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

    /**
     * 对设备设置工作模式，产测模式或者正常模式
     * @param deviceId 设备id
     * @param barcodeId 配件码
     * @return
     */
    public Map<String,Object> setMod(String deviceId, String barcodeId) {
        //通过配件码查询产测设备表，查看是否认证成功
        QcDeviceEntity qcDevice = qcDeviceService.findByBarCodeId(barcodeId);
        if (qcDevice==null || !qcDevice.isAuthorization()){
            return ApiResult.error("未认证，请先认证后再设置设备的工作模式");
        }
        //发送iot命令
        String jsonResult = issueCmdService.issueCmd27(deviceId,barcodeId,2);
        return resolveJsonResult(jsonResult);
    }

    //公共解析cmd命令返回的json字符串
    private Map<String, Object> resolveJsonResult(String jsonResult) {
        if(StringUtils.isEmpty(jsonResult)){
            return ApiResult.error("操作失败，请重新尝试");
        }
        Map resultMap = (Map<String,Object>)JSON.parse(jsonResult);
        String code = null;
        if(resultMap.get("code") != null){
            code = resultMap.get("code").toString();
        }
        if (!"000".equals(code)){
            return ApiResult.error("操作失败，请重新尝试");
        }else{
            return ApiResult.success("操作成功，请开始流程");
        }
    }

    /**
     * 设备基本操作，包括开机、关机、等
     * @param deviceId 设备id
     * @param barcodeId 配件码
     * @param type 1关机 2开机 3设备清洗 4锁机
     * @return
     */
    public Map<String, Object> handleDevice(String deviceId, String barcodeId,int type) {
        String handleType = null;
        switch (type){
            case 1:
                handleType = "0x04";//关机
                break;
            case 2:
                handleType = "0x02";//开机
                break;
            case 3:
                handleType = "0x01";//清洗设备
                break;
            case 4:
                handleType = "0x08";//锁机
                break;
            default:
                handleType = "0x01";//设备清洗
        }
        String resultJson = issueCmdService.issueCmd17(deviceId,barcodeId,handleType);
        return resolveJsonResult(resultJson);
    }

    /**
     * 查询产测实时记录信息
     * @param historyId
     * @return
     */
    public Map<String, Object> findTestHistory(String historyId) {
        QcDeviceHistoryEntity qcDeviceHistory = qcDeviceHistoryService.findById(historyId);
        if(!qcDeviceHistory.isRealTime()){
            //通过cmd命令向iot发送获取实时记录
            QcDeviceEntity qcDevice = qcDeviceService.findByBarCodeId(qcDeviceHistory.getBarCodeId());
            if (qcDevice!=null){
                //iot发送实时获取数据的指令
                String jsonResult = issueCmdService.issueCmd28(qcDevice.getDeviceId(),qcDevice.getBarcodeId());
                Map<String,Object> map = (Map<String,Object>)JSON.parse(jsonResult);
                if (map.get("code") != null){
                    String code = map.get("code").toString();
                    if ("000".equals(code)){
                        qcDeviceHistory = qcDeviceHistoryService.findById(historyId);
                    }else{
                        return ApiResult.error("查询失败，请稍后再试!");
                    }
                }else{
                    return ApiResult.error("查询失败，请稍后再试!");
                }
            }else{
                return  ApiResult.error("查询出错，请稍后再试！");
            }
        }
        return ApiResult.success(qcDeviceHistory);
    }
}
