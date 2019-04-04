package com.pingan.angel.admin.api.mongodb;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * 产测设备信息表
 */
@Document(collection = "QcDeviceEntity")
public class QcDeviceEntity implements Serializable {
    private String wifi		;//是否支持wifi
    private String snCode		;//整机码/物流码
    private Date lastActiveTime		;//最后活跃时间，每次心跳更新
    private boolean isAuthorization		;//是否认证
    private String updateUser	;
    private boolean isOnline		;//是否在线
    private Date updateTime		;//修改时间
    private String deviceId		;//设备id
    private String	iccid;//ICCID
    private boolean isOpen		;//是否开机
    private Date createTime		;//创建时间
    private String bulutooth		;//是否支持蓝牙
    private String proId		;//产品Id
    private String qcHistoryId		;//产测记录Id
    private String company		;//滤芯数量
    private String gprs		;//是否支持GPRS
    private String createUser	;
    private String	id;
    private	String	barcodeId;//配件码
    private	String	activeCode;//设备激活码
    private	boolean	testSuccess;//产测是否通过

    public String getWifi() {
        return wifi;
    }

    public void setWifi(String wifi) {
        this.wifi = wifi;
    }

    public String getSnCode() {
        return snCode;
    }

    public void setSnCode(String snCode) {
        this.snCode = snCode;
    }

    public Date getLastActiveTime() {
        return lastActiveTime;
    }

    public void setLastActiveTime(Date lastActiveTime) {
        this.lastActiveTime = lastActiveTime;
    }

    public boolean isAuthorization() {
        return isAuthorization;
    }

    public void setAuthorization(boolean authorization) {
        isAuthorization = authorization;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getIccid() {
        return iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getBulutooth() {
        return bulutooth;
    }

    public void setBulutooth(String bulutooth) {
        this.bulutooth = bulutooth;
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getQcHistoryId() {
        return qcHistoryId;
    }

    public void setQcHistoryId(String qcHistoryId) {
        this.qcHistoryId = qcHistoryId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getGprs() {
        return gprs;
    }

    public void setGprs(String gprs) {
        this.gprs = gprs;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBarcodeId() {
        return barcodeId;
    }

    public void setBarcodeId(String barcodeId) {
        this.barcodeId = barcodeId;
    }

    public String getActiveCode() {
        return activeCode;
    }

    public void setActiveCode(String activeCode) {
        this.activeCode = activeCode;
    }

    public boolean isTestSuccess() {
        return testSuccess;
    }

    public void setTestSuccess(boolean testSuccess) {
        this.testSuccess = testSuccess;
    }
}
