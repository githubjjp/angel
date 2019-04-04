package com.pingan.angel.admin.api.mongodb;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * 产测控制下发指令对象
 */
@Document(collection = "QcControlLogEntity")
public class QcControlLogEntity extends OrderCommonEntity implements Serializable {
}
