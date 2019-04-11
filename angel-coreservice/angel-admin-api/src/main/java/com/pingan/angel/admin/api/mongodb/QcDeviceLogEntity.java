package com.pingan.angel.admin.api.mongodb;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * 产测设备上报日志对象
 */
@Document(collection = "QcDeviceLogTab")
@Data
public class QcDeviceLogEntity extends OrderCommonEntity implements Serializable {
}
