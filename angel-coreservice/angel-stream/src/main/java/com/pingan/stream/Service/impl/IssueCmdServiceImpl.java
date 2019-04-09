package com.pingan.stream.Service.impl;

import com.pingan.stream.Service.IssueCmdService;
import com.pingan.stream.mongodb.dao.DeviceControlLogDao;
import com.pingan.stream.mongodb.dao.QcControlLogDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@PropertySource("classpath:iot.properties")
public class IssueCmdServiceImpl implements IssueCmdService {
    private static Logger logger= LoggerFactory.getLogger(IssueCmdServiceImpl.class);
    @Value("${iot.product.key}")
    private String productKey;   //产品key
    @Value("${iot.test.url}")
    private String iotUrl;       //iot url

    @Autowired
    private QcControlLogDao qcControlLogDao;//产测控制下发日志操作
    @Autowired
    private DeviceControlLogDao deviceControlLogDao;//非产测控制下发日志操作

    @Override
    public String issueCmd16(String deviceId, String barcodeId) {
        return null;
    }

    @Override
    public String issueCmd17(String deviceId, String barcodeId, String type) {
        return null;
    }

    @Override
    public String issueCmd18(String deviceId, String barcodeId) {
        logger.info("校准时间指令::");
        Date date=new Date();//当前系统时间

        return null;
    }

    @Override
    public String issueCmd20(String deviceId, String barcodeId, String type) {
        return null;
    }

    @Override
    public String issueCmd23(String deviceId, String barcodeId, int hour) {
        return null;
    }

    @Override
    public String issueCmd24(String deviceId, String barcodeId) {
        return null;
    }

    @Override
    public String issueCmd26(String deviceId, String barcodeId) {
        return null;
    }

    @Override
    public String issueCmd27(String deviceId, String barcodeId, int type) {
        return null;
    }

    @Override
    public String issueCmd28(String deviceId, String barcodeId) {
        return null;
    }

    @Override
    public String issueCmd30(String deviceId, String barcodeId) {
        logger.info("滤芯数据下发指令---deviceId::"+deviceId+"   barcodeId::"+barcodeId);
        return null;
    }

    @Override
    public String issueCmd31(String deviceId, String barcodeId) {
        return null;
    }
}
