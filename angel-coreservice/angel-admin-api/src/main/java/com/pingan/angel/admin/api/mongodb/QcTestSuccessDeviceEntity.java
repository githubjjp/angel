package com.pingan.angel.admin.api.mongodb;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 *  产测通过的设备整机码记录表
 */
@Document(collection = "QcTestSuccessDeviceEntity")
public class QcTestSuccessDeviceEntity implements Serializable {
    @Id
    private String id;
    @Field
    private String deviceId;//设备id
    @Field
    private String barcodeId;//配件码

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
}
