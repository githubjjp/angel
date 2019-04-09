package com.pingan.angel.admin.api.mongodb;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "DeviceCustomerCodeEntity")
@Data
public class DeviceCustomerCodeEntity extends BaseMongoEntity {

    private String barcodeId;//配件码
    private String snCode;//整机码
    private String customerSn;//大客户批次码

}
