package com.pingan.angel.admin.api.mongodb;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Map;

public class OrderCommonEntity extends BaseMongoEntity{
    @Id
    private String id;//主键
    @Field
    private Map<String,Object> data;
    @Field
    private String deviceId; //设备id
    @Field
    private String barcodeId;//配件码
    @Field
    private int flage;//起始标志  默认242
    @Field
    private String version;// 版本
    @Field
    private String versionName;//协议名称
    @Field
    private String versionNo;//协议编号
    @Field
    private int cmd;//命令
    @Field
    private String gprs;//gprs状态信息。CMD=25的时候为gprs信号强度",description = "1：服务器已连接，0：服务器未连接
    @Field
    private String gprsLv;//gprs信号强度
    @Field
    private String addr;//经纬度信息   //192.168.0.1
    @Field
    private int len;//长度
    @Field
    private String IMEI;//默认为经纬度，CMD=28时候，为IMEI号
    @Field
    private String cmdText;//指令说明
    @Field
    private String json;//原始指令


    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getBarcodeId() {
        return barcodeId;
    }

    public void setBarcodeId(String barcodeId) {
        this.barcodeId = barcodeId;
    }

    public int getFlage() {
        return flage;
    }

    public void setFlage(int flage) {
        this.flage = flage;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(String versionNo) {
        this.versionNo = versionNo;
    }

    public int getCmd() {
        return cmd;
    }

    public void setCmd(int cmd) {
        this.cmd = cmd;
    }

    public String getGprs() {
        return gprs;
    }

    public void setGprs(String gprs) {
        this.gprs = gprs;
    }

    public String getGprsLv() {
        return gprsLv;
    }

    public void setGprsLv(String gprsLv) {
        this.gprsLv = gprsLv;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public String getIMEI() {
        return IMEI;
    }

    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public String getCmdText() {
        return cmdText;
    }

    public void setCmdText(String cmdText) {
        this.cmdText = cmdText;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }
}
