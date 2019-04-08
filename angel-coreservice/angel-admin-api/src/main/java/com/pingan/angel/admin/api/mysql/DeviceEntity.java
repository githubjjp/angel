package com.pingan.angel.admin.api.mysql;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * 设备对象(设备表)
 */
@Data
public class DeviceEntity extends BaseEntity implements Serializable {
    @Id
    private Long id;
    private String snCode;//物流码/整机码
    private String deviceId;//设备ID
    private String  customerSuperCode;//大客户码
    private String barcodeId;//配件码
    private String activeId;//开机码
    private String customerSn;//大客户批次码
    private String isActivation;//Y已激活 N未激活
    private String isProductTest;//Y 已产测  N未产测
    private String isOnline;//Y在线 N不在线
    private Date isOnlineDate;//上线心跳更新时间
    private String wifiOrGprs;//联网方式 1支持wifi 2支持gprs
    private String deviceSecret;//设备secret

}
