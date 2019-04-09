package com.pingan.angel.qctest.controller;

import com.pingan.angel.qctest.service.QcDeviceDispatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 产测api接口调度
 */
@RestController
@RequestMapping("/webapi/qc/")
public class QcapiController {

    @Autowired
    private QcDeviceDispatchService service;


    /**
     * 查询是否产测成功
     * @param snCode
     * @return
     */
    @RequestMapping(value = "/isTestSuccess",method = RequestMethod.POST)
    public Map<String,Object> isTestSuccess(String snCode){
        return service.isTestSuccess(snCode);
    }

    /**
     * 扫描开始测试
     * @param snCode 物流码
     * @param mac   物理mac地址
     * @param isWifi    是否wifi网络
     * @return
     */
    @RequestMapping(value = "/scanTest",method = RequestMethod.POST)
    public Map<String,Object> scanTest(String snCode,String mac,boolean isWifi){
        return service.scanTest(snCode,isWifi,mac);
    }

    /**
     * 重新产测
     * @param snCode
     * @return
     */
    @RequestMapping(value = "/reQcDevice",method = RequestMethod.GET)
    public Map<String,Object> reQcDevice(String snCode){
        return service.reQcDevice(snCode);
    }

    /**
     * 获取设备实时数据
     * @param id
     * @return
     */
//    @RequestMapping(value = "/getData",method = RequestMethod.GET)
//    public Map<String,Object> getData(String id){
//        QcDeviceHistoryEntity testHistory = service.findTestHistory(id);
//        return WebApiResult.success(JClassUtil.copyTo(testHistory,new QcHistoryVo()));
//    }

    /**
     * 设备关机
     * @param deviceId
     * @param barcodeId
     * @return
     */
//    @RequestMapping(value = "/shutdownDevice",method = RequestMethod.GET)
//    public Map<String,Object> shutdownDevice(String deviceId,String barcodeId){
//        boolean b = service.shutdownDevice(deviceId, barcodeId);
//        return b?WebApiResult.success(null):WebApiResult.error("关机失败，请稍后再试");
//    }

    /**
     * 设备开机
     * @param deviceId
     * @param barcodeId
     * @return
     */
//    @RequestMapping(value = "/startupDevice",method = RequestMethod.GET)
//    public Map<String,Object> startupDevice(String deviceId,String barcodeId){
//        boolean b = service.startupDevice(deviceId, barcodeId);
//        return b?WebApiResult.success(null):WebApiResult.error("开机失败，请稍后再试");
//    }

    /**
     * 查询设备当前状态
     * @param snCode
     * @return
     */
//    @RequestMapping(value = "/deviceStatus",method = RequestMethod.GET)
//    public Map<String,Object> deviceStatus(String snCode){
//        return service.deviceStatus(snCode);
//    }

    /**
     * 冲洗设备
     * @param deviceId
     * @param barcodeId
     * @return
     */
//    @RequestMapping(value = "/washDevice",method = RequestMethod.GET)
//    public Map<String,Object> washDevice(String deviceId,String barcodeId){
//        boolean b = service.washDevice(deviceId, barcodeId);
//        return b?WebApiResult.success(null):WebApiResult.error("清洗失败，请稍后再试");
//    }

    /**
     * 设备设置工作模式
     * @param deviceId
     * @param barcodeId
     * @return
     */
//    @RequestMapping(value = "/setMod",method = RequestMethod.GET)
//    public Map<String,Object> setMod(String deviceId,String barcodeId){
//        boolean b = service.setMod(deviceId, barcodeId);
//        return b?WebApiResult.success(null):WebApiResult.error("测试模式设置失败，请稍后再试");
//    }

    /**
     * 结束产测，提交产测
     * @param historyId
     * @return
     */
//    @RequestMapping(value = "/endTest",method = RequestMethod.GET)
//    public Map<String,Object> endTest(String historyId){
//        return service.checkTestResult(historyId);
//    }

    /**
     * 恢复出厂设置
     * @param historyId
     * @param isLock
     * @return
     */
//    @RequestMapping(value = "/reset",method = RequestMethod.GET)
//    public Map<String,Object> reset(String historyId,@RequestParam(required = false,defaultValue = "true") boolean isLock){
//        return service.reset(historyId,isLock);
//    }

    /**
     * 扫描大客户码
     * @param historyId
     * @param customerCode
     * @param isLock
     * @return
     */
//    @RequestMapping(value = "/scanCustomerSuper",method = RequestMethod.GET)
//    public Map<String,Object> scanCustomerSuper(String historyId,String customerCode,@RequestParam(required = false,defaultValue = "false") boolean isLock
//    ){
//        return service.scanCustomerSuper(historyId,customerCode,isLock);
//    }

}
