package com.pingan.angel.admin.api.mongodb;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * 非产测控制下发设备日志对象
 */
@Document(collection = "DeviceControlLogEntity")
public class DeviceControlLogEntity extends OrderCommonEntity implements Serializable {
}
