package com.pingan.angel.admin.api.mongodb;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "QcDeviceUnionInfoTab")
@Data
public class QcDeviceUnionInfoEntity extends BaseMongoEntity implements Serializable {
    private String id;//主键
    private String productCode;//产品编码
    private String activeId;//设备开机码
    private String barcodeId;//配件码
    private String snCode;//整机码
    private String mac;//mac地址
    private String deviceSecret;//设备密钥   从IOT查询设备接口获取
}
