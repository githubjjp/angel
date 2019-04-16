package com.pingan.angel.qctest.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pingan.angel.admin.api.entity.ApiResult;
import com.pingan.angel.admin.api.entity.PageResult;
import com.pingan.angel.admin.api.entity.QcTestResult;
import com.pingan.angel.admin.api.entity.QcTestStatusEnum;
import com.pingan.angel.admin.api.mongodb.*;
import com.pingan.angel.admin.api.mysql.DeviceEntity;
import com.pingan.angel.admin.api.mysql.DeviceStatusEntity;
import com.pingan.angel.admin.api.mysql.OrderProductEntity;
import com.pingan.angel.admin.api.mysql.QcDeviceConfigEntity;
import com.pingan.stream.Service.IssueCmdService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 产测api调度服务
 */
@Service("QcDeviceDispatchService")
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
    @Autowired
    private QcDeviceConfigService qcDeviceConfigService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CustomerSuperService customerSuperService;


    /**
     * 判断产测是否通过服务
     *
     * @param snCode 整机码
     * @return
     */
    public Map<String, Object> isTestSuccess(String snCode) {
        if (StringUtils.isEmpty(snCode)) {
            return ApiResult.error("参数为空，请重试");
        }
        QcTestSuccessDeviceEntity qcSuccessEntity = qcTestSuccessServcie.findBySnCode(snCode);
        //无论产测是否通过都要去查询到对应的配件码
        QcDeviceEntity qcDevice = qcDeviceService.findBySnCode(snCode);
        if (qcDevice == null) {
            return ApiResult.error("整机码未注册，请稍后再试");
        }
        HashMap map = new HashMap<String, Object>();
        map.put("isTestSuccess", qcSuccessEntity != null ? true : false);
        map.put("barcodeId", qcDevice.getBarcodeId());
        return ApiResult.success(map);
    }

    /**
     * 扫码开始产测
     *
     * @param snCode 整机码
     * @param isWifi 是否支持wifi
     * @param mac    mac地址
     * @return
     */
    public Map<String, Object> scanTest(String snCode, boolean isWifi, String mac) {
        QcDeviceEntity qcDevice = qcDeviceService.findBySnCode(snCode);
        if (qcDevice == null) {//未注册
            return ApiResult.error("设备未注册，请稍后再试");
        } else {//注册了
            QcTestSuccessDeviceEntity testSuccessDevice = qcTestSuccessServcie.findBySnCode(snCode);
            if (testSuccessDevice != null) {//产测成功
                try {
                    //删除设备表中的信息,直接删除一条记录
                    deviceService.deleteDeviceInfoBySnCode(snCode);
                    //删除设备状态表中的数据
                    deviceStatusService.deleteByDeviceId(testSuccessDevice.getDeviceId());
                    //删除产测成功表中的数据
                    qcTestSuccessServcie.deleteBySnCode(snCode);
                } catch (Exception e) {
                    e.printStackTrace();
                    return ApiResult.error("设备信息异常，无法重新进行产测");
                }

                String deviceId = qcDevice.getDeviceId();
                String activeId = qcDevice.getActiveCode();
                String proId = qcDevice.getProId();
                String company = qcDevice.getCompany();
                String barcodeId = qcDevice.getBarcodeId();

                //添加产测记录表
                QcDeviceHistoryEntity qcDeviceHistoryEntity = new QcDeviceHistoryEntity();//产测记录
                qcDeviceHistoryEntity.setProductCode(proId);
                qcDeviceHistoryEntity.setBarCodeId(barcodeId);
                qcDeviceHistoryEntity.setActiveId(activeId);
                qcDeviceHistoryEntity.setMac(mac);
                qcDeviceHistoryEntity.setCreateTime(new Date());
                qcDeviceHistoryEntity.setTestAccount("userid");//fixme 用户信息怎么获取
                qcDeviceHistoryEntity.setTestUserName("sys");//fixme 从app用户部分获取
                qcDeviceHistoryEntity.setActiveTime(new Date());
                qcDeviceHistoryEntity.setSnCode(snCode);
                String historyId = qcDeviceHistoryService.add(qcDeviceHistoryEntity);
                qcDeviceHistoryEntity.setId(historyId);

                //更新产测设备表
                qcDevice.setOnline(true);
                qcDevice.setLastActiveTime(new Date());
                qcDevice.setQcHistoryId(historyId);
                qcDevice.setCreateTime(new Date());
                if (isWifi) {
                    qcDevice.setWifi("Y");
                    qcDevice.setGprs("N");
                } else {
                    qcDevice.setWifi("N");
                    qcDevice.setGprs("Y");
                }
                qcDeviceService.updateById(qcDevice);

                OrderProductEntity product = productService.findByProductId(proId);

                Map<String, Object> result = new HashMap<>();
                result.put("qcHistory", qcDeviceHistoryEntity);
                result.put("qcDeviceInfo", qcDevice);
                result.put("product", product);
                //认证
                issueCmdService.issueCmd20(deviceId, barcodeId, "6");//激活码认证
                return ApiResult.success(result);
            } else {//产测不成功
                String proId = qcDevice.getProId();
                String historyId = qcDevice.getQcHistoryId();

                QcDeviceHistoryEntity deviceHistoryEntity = new QcDeviceHistoryEntity();
                deviceHistoryEntity.setTestAccount("userid");//fixme 获取修改账户的userid
                deviceHistoryEntity.setTestUserName("sys");//fixme 获取修改账户的用户名
                deviceHistoryEntity.setActiveTime(new Date());
                boolean flag = qcDeviceHistoryService.updateActiveTimeById(deviceHistoryEntity);
                if (!flag) {
                    return ApiResult.error("产测信息异常");
                }
                QcDeviceHistoryEntity deviceHistory = qcDeviceHistoryService.findById(historyId);
                OrderProductEntity product = productService.findByProductId(proId);

                Map resultMap = new HashMap<String, Object>();
                resultMap.put("qcHistory", deviceHistory);
                resultMap.put("qcDeviceInfo", qcDevice);
                resultMap.put("product", product);

                //服务器下发iccid命令
                if (StringUtils.isEmpty(qcDevice.getIccid())) {
                    issueCmdService.issueCmd26(qcDevice.getDeviceId(), qcDevice.getBarcodeId());//iccid命令
                }
                //设备认证
                if (!qcDevice.isAuthorization()) {
                    issueCmdService.issueCmd20(qcDevice.getDeviceId(), qcDevice.getBarcodeId(), "6");
                }
                return ApiResult.success(resultMap);
            }
        }
    }

    /**
     * 重新产测
     *
     * @param snCode 整机码
     * @return
     */
    public Map<String, Object> reQcDevice(String snCode) {
        if (StringUtils.isEmpty(snCode)) {
            return ApiResult.error("参数为空，请重试");
        }
        QcTestSuccessDeviceEntity successDevice = qcTestSuccessServcie.findBySnCode(snCode);
        if (successDevice != null) {
            if (StringUtils.isEmpty(successDevice.getDeviceId())) {
                return ApiResult.error("重新产测失败，该设备未通过产测");
            }
            //逻辑删除设备表中的设备产测状态
            deviceService.updateQCTestUndoById(successDevice.getDeviceId());
            //删除产测成功表中的记录
            qcTestSuccessServcie.deleteBySnCode(snCode);
            //通过整机码删除设备大客户批次码关联记录
            deviceCustomerCodeService.deleteBySnCode(snCode);
            return ApiResult.success("重新产测成功，请重启设备再次扫码产测");
        } else {
            return ApiResult.error("重新产测失败，该设备未产测过");
        }
    }

    /**
     * 对设备设置工作模式，产测模式或者正常模式
     *
     * @param deviceId  设备id
     * @param barcodeId 配件码
     * @return
     */
    public Map<String, Object> setMod(String deviceId, String barcodeId) {
        if (StringUtils.isEmpty(barcodeId)) {
            return ApiResult.error("参数为空，请重试");
        }
        if (StringUtils.isEmpty(deviceId)) {
            return ApiResult.error("参数为空，请重试");
        }
        //发送认证命令
        issueCmdService.issueCmd20(deviceId, barcodeId, "6");//激活码认证
        //设备表查询认证是否成功
        DeviceEntity device = deviceService.findOneByDeviceId(deviceId);
        if (device == null || "N".equalsIgnoreCase(device.getIsAuthorization())) {
            return ApiResult.error("未认证，请重试后再设置设备的工作模式");
        }
        //发送iot命令
        String jsonResult = issueCmdService.issueCmd27(deviceId, barcodeId, 2);
        return resolveJsonResult(jsonResult);
    }

    //公共解析cmd命令返回的json字符串，仅判断是否操作成功，具体数据需要额外自行解析
    private Map<String, Object> resolveJsonResult(String jsonResult) {
        if (StringUtils.isEmpty(jsonResult)) {
            return ApiResult.error("操作失败，请重新尝试");
        }
        Map resultMap = (Map<String, Object>) JSON.parse(jsonResult);
        String code = null;
        if (!CollectionUtils.isEmpty(resultMap)) {
            Object dataMap = resultMap.get("data");
            if (dataMap != null && ((Map<String, Object>) dataMap).get("code") != null) {
                code = ((Map<String, Object>) dataMap).get("code").toString();
            }
        }
        if (!"00".equals(code)) {//不为00操作失败
            return ApiResult.error("操作失败，请重新尝试");
        } else {
            return ApiResult.success("操作成功，请开始流程");
        }
    }

    /**
     * 设备基本操作，包括开机、关机、等
     *
     * @param deviceId  设备id
     * @param barcodeId 配件码
     * @param type      1关机 2开机 3设备清洗 4锁机
     * @return
     */
    public Map<String, Object> handleDevice(String deviceId, String barcodeId, int type) {
        if (StringUtils.isEmpty(deviceId)) {
            return ApiResult.error("操作失败，请重新尝试");
        }
        if (StringUtils.isEmpty(barcodeId)) {
            return ApiResult.error("操作失败，请重新尝试");
        }
        String handleType = null;
        switch (type) {
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
        String resultJson = issueCmdService.issueCmd17(deviceId, barcodeId, handleType);
        return resolveJsonResult(resultJson);
    }

    /**
     * 查询产测实时记录信息
     *
     * @param historyId
     * @return
     */
    public Map<String, Object> findRealTimeData(String deviceId) {
        if (StringUtils.isEmpty(deviceId)) {
            return ApiResult.error("操作失败,参数错误，请重新尝试");
        }
        QcDeviceHistoryEntity qcDeviceHistory = null;
        //根据deviceId获取配件码信息
        DeviceEntity device = deviceService.findOneByDeviceId(deviceId);
        if (device == null) {
            return ApiResult.error("设备码错误，请重新尝试");
        }
        //通过cmd命令向iot发送获取实时记录
        String jsonResult = issueCmdService.issueCmd28(deviceId, device.getBarcodeId());

        Map<String, Object> map = resolveJsonResult(jsonResult);
        if (map.get("code") != null) {
            String code = map.get("code").toString();
            if ("00".equals(code)) {
                qcDeviceHistory = qcDeviceHistoryService.findById(deviceId);
            } else {
                return ApiResult.error("查询失败，请稍后再试!");
            }
        } else {
            return ApiResult.error("查询失败，请稍后再试!");
        }
        return ApiResult.success(qcDeviceHistory);
    }

    /**
     * 提交产测数据，完成产测流程
     * 实时数据是否正确，开关机是否成功，
     *
     * @param deviceId 设备id
     * @return
     */
    public Map<String, Object> checkTestResult(String deviceId) {
        if (StringUtils.isEmpty(deviceId)) {
            return ApiResult.error("产测记录id为空");
        }
        boolean flag = true;//产测结果flag
        List<QcTestResult> qcTestResults = new ArrayList<>();//存放所有产测记录
        //根据产测历史纪录id查询产测记录信息
        DeviceStatusEntity deviceStatusInfo = deviceStatusService.findByDeviceId(deviceId);//存储的产测记录信息
        QcDeviceHistoryEntity qcDeviceHistory = qcDeviceHistoryService.findById(deviceId);
        if (deviceStatusInfo == null) {
            return ApiResult.error("未获取产测数据，请产测完成后再提交");
        }
        //实时数据检测结果
        QcTestResult realTimeData = new QcTestResult();
        realTimeData.setName("实时数据");
        if (deviceStatusInfo.getInTds() == 0) {
            realTimeData.setState(QcTestStatusEnum.WAIT.getStatus());
            realTimeData.setResult(QcTestStatusEnum.WAIT.getValue());
            flag = false;
        } else {
            String productCode = qcDeviceHistory.getProductCode();
            QcDeviceConfigEntity productConfig = qcDeviceConfigService.findByProductCode(productCode);
            if (productConfig == null) {
                //若为空随意取一个产测设备的配置信息来使用
                Page page = new Page(0, 1L);
                page.setDesc("created_time");//取出最新的数据
                PageResult<QcDeviceConfigEntity> result = qcDeviceConfigService.queryPage(page, Wrappers.emptyWrapper());
                if (result.getRecordList().isEmpty()) {
                    return ApiResult.error("产测配置信息异常");
                }
                productConfig = result.getRecordList().get(0);//获取任意一条配置信息配置该设备
            }

            //进出水是否正常
            String resultStr = checkData(deviceStatusInfo, productConfig);
            if (StringUtils.isNotEmpty(resultStr)) {
                realTimeData.setState(QcTestStatusEnum.FAIL.getStatus());
                realTimeData.setResult(resultStr);
                flag = false;
            } else {
                realTimeData.setState(QcTestStatusEnum.SUCCESS.getStatus());
                realTimeData.setResult(QcTestStatusEnum.SUCCESS.getValue());
            }
        }

        //开关机是否测试过
        QcTestResult shutDown = new QcTestResult();
        shutDown.setName("关机测试");
        if (qcDeviceHistory.getShutdownTestTime() == null) {
            shutDown.setState(QcTestStatusEnum.WAIT.getStatus());
            shutDown.setResult(QcTestStatusEnum.WAIT.getValue());
            flag = false;
        } else if (qcDeviceHistory.isShutdownTest()) {
            shutDown.setState(QcTestStatusEnum.SUCCESS.getStatus());
            shutDown.setResult(QcTestStatusEnum.SUCCESS.getValue());
        } else {
            shutDown.setState(QcTestStatusEnum.FAIL.getStatus());
            shutDown.setResult(QcTestStatusEnum.FAIL.getValue());
            flag = false;
        }

        QcTestResult startUp = new QcTestResult();
        startUp.setName("开机测试");
        if (qcDeviceHistory.getStartupTestTime() == null) {
            startUp.setState(QcTestStatusEnum.WAIT.getStatus());
            startUp.setResult(QcTestStatusEnum.WAIT.getValue());
            flag = false;
        } else if (qcDeviceHistory.isStartupTest()) {
            startUp.setState(QcTestStatusEnum.SUCCESS.getStatus());
            startUp.setResult(QcTestStatusEnum.SUCCESS.getValue());
        } else {
            startUp.setState(QcTestStatusEnum.FAIL.getStatus());
            startUp.setResult(QcTestStatusEnum.FAIL.getValue());
            flag = false;
        }
        //返回结果
        qcTestResults.add(realTimeData);
        qcTestResults.add(shutDown);
        qcTestResults.add(startUp);
        Map<String, Object> update = new HashMap<>();
        if (flag) {
            boolean updateResult = qcDeviceHistoryService.updateTestResult(flag, deviceId);
            if (updateResult) {
                return ApiResult.success(qcTestResults);
            }
        }
        return ApiResult.success(qcTestResults);
    }

    /**
     * 检查产测数据是否有问题
     *
     * @param deviceStatusEntity 产测数据
     * @param productConfig      配置数据
     * @return
     */
    private String checkData(DeviceStatusEntity deviceStatusEntity, QcDeviceConfigEntity productConfig) {
        StringBuffer sb = new StringBuffer();
        Integer minInletTds = productConfig.getMinInletTds();
        Integer maxInletTds = productConfig.getMaxInletTds();
        if (deviceStatusEntity.getInTds() < minInletTds || deviceStatusEntity.getInTds() > maxInletTds) {
            sb.append("进水TDS值异常，");
        }

        Integer minOutTds = productConfig.getMinOutTds();
        Integer maxOutTds = productConfig.getMaxOutTds();
        if (deviceStatusEntity.getOutTds() < minOutTds || deviceStatusEntity.getOutTds() > maxOutTds) {
            sb.append("出水TDS值异常，");
        }

        Integer minWaterTemperature = productConfig.getMinWaterTemperature();
        Integer maxWaterTemperature = productConfig.getMaxWaterTemperature();
        if (deviceStatusEntity.getOutTemperature() < minWaterTemperature || deviceStatusEntity.getOutTemperature() > maxWaterTemperature) {
            sb.append("水温值异常，");
        }

        Integer minWaterAmount = productConfig.getMinWaterAmount();
        Integer maxWaterAmount = productConfig.getMaxWaterAmount();
        if (deviceStatusEntity.getTotalWater() < minWaterAmount || deviceStatusEntity.getTotalWater() > maxWaterAmount) {
            sb.append("水量异常，");
        }
        return sb.toString();
    }

    /**
     * 恢复出厂设置
     *
     * @param historyId 产测记录id
     * @param isLock    是否锁机（清除认证）
     * @return
     */
    public Map<String, Object> reset(String historyId, boolean isLock) {
        QcDeviceHistoryEntity qcHistory = qcDeviceHistoryService.findById(historyId);
        QcDeviceEntity qcDevice = qcDeviceService.findByBarCodeId(qcHistory.getBarCodeId());
        if (qcDevice == null) {
            return ApiResult.error("设备信息不存在");
        }
        //恢复出厂设置或者扫码完成产测都调用这个业务逻辑
        Map<String, Object> resultMap = qcTestEnd(qcHistory, qcDevice, isLock);
        if(resultMap ==null || resultMap.get("code") == null || !"00".equals(resultMap.get("code"))){
            return resultMap;
        }
        return ApiResult.success("");
    }

    //恢复出厂设置或者扫码完成产测都调用这个业务逻辑
    //将产测信息记录到设备表，完成产测
    private Map<String, Object> qcTestEnd(QcDeviceHistoryEntity qcHistory, QcDeviceEntity qcDevice, boolean isLock) {
        if (qcHistory == null) {
            return null;
        }
        if (qcDevice == null) {
            return null;
        }
        //更新产测记录表为当前时间并产测完成
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("completeTime", new Date());
        paramMap.put("testSuccess", true);
        boolean flag = qcDeviceHistoryService.updateByMap(paramMap, qcHistory.getId());
        if (!flag) {
            return ApiResult.error("重置设备失败");
        }

        //查询设备大客户关联表
        String customerSuperCode = qcHistory.getCustomerSuperCode();
        String customerSuperId = qcHistory.getCustomerSuperId();
        DeviceCustomerCodeEntity customerCode = deviceCustomerCodeService.findBySnCode(qcDevice.getSnCode());
        if (StringUtils.isEmpty(customerSuperId)) {
            if (customerCode != null) {
                customerSuperId = customerSuperService.findIdByCustomerCode(customerCode.getCustomerSn());
                if (StringUtils.isNotEmpty(customerSuperId)) {
                    customerSuperCode = customerCode.getCustomerSn();
                }
            }
        } else if (StringUtils.isNotEmpty(customerSuperCode)) {
            if (customerCode == null) {//大客户码关联表无记录
                DeviceCustomerCodeEntity deviceCustomer = new DeviceCustomerCodeEntity();
                deviceCustomer.setSnCode(qcDevice.getSnCode());
                deviceCustomer.setCustomerSn(qcHistory.getCustomerSuperCode());
                deviceCustomer.setBarcodeId(qcDevice.getBarcodeId());
                deviceCustomerCodeService.add(deviceCustomer);
            } else {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("snCode", qcDevice.getSnCode());
                map.put("customerSn", qcHistory.getCustomerSuperCode());
                map.put("barCodeId", qcDevice.getBarcodeId());
                deviceCustomerCodeService.updateByMap(map, qcDevice.getSnCode());
            }
        }

        //锁机
        if (isLock) {
            String result = issueCmdService.issueCmd24(qcDevice.getDeviceId(), qcDevice.getBarcodeId());
            if (StringUtils.isNotEmpty(result)) {
                Map<String, Object> map = resolveJsonResult(result);
                if (CollectionUtils.isEmpty(map) ||
                        map.get("code") == null ||
                        !"00".equals(map.get("code").toString())) {
                    return ApiResult.error("重置设备失败");
                }
            } else {
                return ApiResult.error("重置设备失败");
            }
        }

        //添加到device设备表
        DeviceEntity deviceEntity = new DeviceEntity();
        deviceEntity.setBarcodeId(qcDevice.getBarcodeId());
        deviceEntity.setActiveId(qcDevice.getActiveCode());
        deviceEntity.setSnCode(qcDevice.getSnCode());
        deviceEntity.setProId(qcDevice.getProId());
        deviceEntity.setIccid(qcHistory.getIccid());
        if (StringUtils.isNotEmpty(qcHistory.getIccid())) {
            deviceEntity.setWifiOrGprs("2");
        } else {
            deviceEntity.setWifiOrGprs("1");
        }
        if (StringUtils.isNotEmpty(qcHistory.getMac())) {
            deviceEntity.setMac(qcHistory.getMac());
        }
        if (StringUtils.isNotEmpty(qcHistory.getImei())) {
            deviceEntity.setImei(qcHistory.getImei());
        }
        if (StringUtils.isNotEmpty(customerSuperId)) {
            deviceEntity.setCustomerSuperId(customerSuperId);
        }
        deviceEntity.setCustomerSuperCode(customerSuperCode);

        //产品信息
        OrderProductEntity product = productService.findbyCode(qcDevice.getProId());
        if (product != null) {
            deviceEntity.setProName(product.getName());
            deviceEntity.setProMode(product.getModel());
            deviceEntity.setProImg(product.getImgStr());
        }

        if(isLock){
            deviceEntity.setIsLock("Y");
        }else{
            deviceEntity.setIsAuthorization("Y");
        }

        //添加设备信息
        deviceService.add(deviceEntity);

        QcTestSuccessDeviceEntity qcsuccessDevice = new QcTestSuccessDeviceEntity();
        qcsuccessDevice.setSnCode(qcDevice.getSnCode());
        qcsuccessDevice.setBarcodeId(qcDevice.getBarcodeId());
        qcsuccessDevice.setDeviceId(qcDevice.getDeviceId());
        qcTestSuccessServcie.add(qcsuccessDevice);
        //fixme 可能需要添加sim信息
        return ApiResult.success("");
    }

    /**
     * 扫描大客户码完成产测
     *
     * @param historyId
     * @param customerCode 大客户二维码
     * @param isLock       是否锁机
     * @return
     */
    public Map<String, Object> scanCustomerSuper(String historyId, String customerCode, boolean isLock) {
        return null;
    }

}
