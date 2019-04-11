package com.pingan.angel.admin.api.mongodb;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 *  产测通过的设备整机码记录表
 */
@Document(collection = "QcTestSuccessDeviceTab")
@Data
public class QcTestSuccessDeviceEntity implements Serializable {
    @Id
    private String id;
    private String snCode;//物流码/整机码
    private String deviceId;//设备id
    private String barcodeId;//配件码
}
