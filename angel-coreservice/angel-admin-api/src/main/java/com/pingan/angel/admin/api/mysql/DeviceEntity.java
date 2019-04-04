package com.pingan.angel.admin.api.mysql;

import java.util.Date;

/**
 * 设备对象
 */
public class DeviceEntity {
    private String id;
    private String snCode;//物流码/整机码
    private String deviceId;//设备ID
    private String  customerSuperCode;//大客户码
    private String barcodeId;//配件码
    private String activeId;//开机码
    private String customerSn;//大客户批次码
    private String isActivation;//Y已激活 N未激活
    private String isProductTest;//Y 已产测  N未产测
    private String isOnline;//Y在线 N不在线
    private Date isOnlineDate;//上线心跳更新时间
    private String wifiOrGprs;//联网方式 1支持wifi 2支持gprs
    private Date createdTime;//创建时间
    private String createdBy;//创建人
    private Date updatedTime;//修改时间
    private String updatedBy;//修改人
    private String deviceSecret;//设备secret

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSnCode() {
        return snCode;
    }

    public void setSnCode(String snCode) {
        this.snCode = snCode;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getCustomerSuperCode() {
        return customerSuperCode;
    }

    public void setCustomerSuperCode(String customerSuperCode) {
        this.customerSuperCode = customerSuperCode;
    }

    public String getBarcodeId() {
        return barcodeId;
    }

    public void setBarcodeId(String barcodeId) {
        this.barcodeId = barcodeId;
    }

    public String getActiveId() {
        return activeId;
    }

    public void setActiveId(String activeId) {
        this.activeId = activeId;
    }

    public String getCustomerSn() {
        return customerSn;
    }

    public void setCustomerSn(String customerSn) {
        this.customerSn = customerSn;
    }

    public String getIsActivation() {
        return isActivation;
    }

    public void setIsActivation(String isActivation) {
        this.isActivation = isActivation;
    }

    public String getIsProductTest() {
        return isProductTest;
    }

    public void setIsProductTest(String isProductTest) {
        this.isProductTest = isProductTest;
    }

    public String getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(String isOnline) {
        this.isOnline = isOnline;
    }

    public Date getIsOnlineDate() {
        return isOnlineDate;
    }

    public void setIsOnlineDate(Date isOnlineDate) {
        this.isOnlineDate = isOnlineDate;
    }

    public String getWifiOrGprs() {
        return wifiOrGprs;
    }

    public void setWifiOrGprs(String wifiOrGprs) {
        this.wifiOrGprs = wifiOrGprs;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getDeviceSecret() {
        return deviceSecret;
    }

    public void setDeviceSecret(String deviceSecret) {
        this.deviceSecret = deviceSecret;
    }
}
