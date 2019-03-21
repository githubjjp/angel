package com.pingan.stream.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * 设备上报故障指令信息
 */
@Document(collection="serviceDeliveryOrder")
public class ServiceDeliveryOrderEntity extends OrderEntity implements Serializable {
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
    @Field("status")
    private String status;//指令状态 0-正常 1-异常
    @Field("description")
    private String description;//指令具体情况说明
    @Field("deviceReportId")
    private String deviceReportId;//关联设备上报指令ID，表明设备先上报后触发了服务下发操作
    @Field("cmdStyle")
    private String cmdStyle;//服务器下发操作指令

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

    public String getDeviceReportId() {
        return deviceReportId;
    }

    public void setDeviceReportId(String deviceReportId) {
        this.deviceReportId = deviceReportId;
    }

    public String getCmdStyle() {
        return cmdStyle;
    }

    public void setCmdStyle(String cmdStyle) {
        this.cmdStyle = cmdStyle;
    }

}
