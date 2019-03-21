package com.pingan.stream.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 *  设备上报指令信息
 */
@Document(collection="deviceReportOrder")
public class DeviceReportOrderEntity extends OrderEntity implements Serializable {
    @Id
    private String id;//id
    @Field("data")
    private Map data;//数据
    @Field("createdTime")
    private Date createdTime;//系统创建时间
    @Field("cmdText")
    private String cmdText;//指令含义
    @Field("deviceId")
    private String deviceId;//设备ID
    @Field("cmdStyle")
    private String cmdStyle;//设备上报指令
    @Field("serviceDeliveryId")
    private String serviceDeliveryId;//关联服务反馈设备iD
    @Field("status")
    private String status;//指令状态
    @Field("description")
    private String description;//指令具体情况说明

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getCmdText() {
        return cmdText;
    }

    public void setCmdText(String cmdText) {
        this.cmdText = cmdText;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getCmdStyle() {
        return cmdStyle;
    }

    public void setCmdStyle(String cmdStyle) {
        this.cmdStyle = cmdStyle;
    }

    public String getServiceDeliveryId() {
        return serviceDeliveryId;
    }

    public void setServiceDeliveryId(String serviceDeliveryId) {
        this.serviceDeliveryId = serviceDeliveryId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map getData() {
        return data;
    }

    public void setData(Map data) {
        this.data = data;
    }
}
