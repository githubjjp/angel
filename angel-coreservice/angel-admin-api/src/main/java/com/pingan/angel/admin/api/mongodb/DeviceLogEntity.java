package com.pingan.angel.admin.api.mongodb;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * 非产测设备上报日志对象
 */
@Document(collection = "DeviceLogEntity")
@Data
public class DeviceLogEntity extends OrderCommonEntity implements Serializable {
}
