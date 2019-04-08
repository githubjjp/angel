package com.pingan.angel.admin.api.mongodb;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "QcDeviceUnionInfoEntity")
@Data
public class QcDeviceUnionInfoEntity extends BaseMongoEntity {

    private String productCode;//产品编码
    private String activeId;//设备开机码
    private String barcodeId;//配件码
    private String snCode;//整机码
    private String mac;//mac地址
}
