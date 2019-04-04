package com.pingan.angel.admin.api.mongodb;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * 故障上报日志
 */
@Document(collection = "ErrorLogEntity")
public class ErrorLogEntity  extends OrderCommonEntity implements Serializable {
}
