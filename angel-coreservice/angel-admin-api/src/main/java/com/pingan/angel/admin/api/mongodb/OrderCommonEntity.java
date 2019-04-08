package com.pingan.angel.admin.api.mongodb;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Map;
@Data
public class OrderCommonEntity extends BaseMongoEntity{
    @Id
    private String id;//主键
    private Map<String,Object> data;
    private String deviceId; //设备id
    private String barcodeId;//配件码
    private int flage;//起始标志  默认242
    private String version;// 版本
    private String versionName;//协议名称
    private String versionNo;//协议编号
    private int cmd;//命令
    private String gprs;//gprs状态信息。CMD=25的时候为gprs信号强度",description = "1：服务器已连接，0：服务器未连接
    private String gprsLv;//gprs信号强度
    private String addr;//经纬度信息   //192.168.0.1
    private int len;//长度
    private String IMEI;//默认为经纬度，CMD=28时候，为IMEI号
    private String cmdText;//指令说明
    private String json;//原始指令


}
