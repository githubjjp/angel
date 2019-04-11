package com.pingan.angel.admin.api.mongodb;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * 故障上报日志
 */
@Document(collection = "ErrorLogTab")
@Data
public class ErrorLogEntity  extends OrderCommonEntity implements Serializable {
}
