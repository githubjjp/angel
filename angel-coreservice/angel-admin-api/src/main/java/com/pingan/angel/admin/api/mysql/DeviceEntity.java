package com.pingan.angel.admin.api.mysql;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * 设备对象(设备表)
 */
@Data
public class DeviceEntity extends BaseEntity implements Serializable {
    @Id
    private Long id;
    private String snCode;//物流码/整机码
    private String deviceId;//设备ID
    private String customerSuperCode;//大客户码
    private String customerSuperId;//大客户的userId
    private String barcodeId;//配件码
    private String activeId;//开机码
    private String customerSn;//大客户批次码
    private String isActivation;//Y已激活 N未激活
    private String isProductTest;//Y 已产测  N未产测
    private String isOnline;//Y在线 N不在线
    private Date isOnlineDate;//上线心跳更新时间
    private String wifiOrGprs;//联网方式 1支持wifi 2支持gprs
    private String deviceSecret;//设备secret
    private int programMainSupplier;//主板固件供应商   1.拓邦 2.精诚 3.兴通 4.英唐 5.海和 6.胤桥 7.安吉尔
    private int programMainVersion;//主板固件版本号   版本100 表示V1.00版本，101 表示V1.01版本。
    private String programMainVersionName;//版本100 表示V1.00版本，101 表示V1.01版本
    private String mac;   //MAC地址(WIFI)/IMEI(GPRS)
    private int programSmallSupplier;//小板固件供应商
    private int programSmallVersion;//小板固件版本号   版本100 表示V1.00版本，101 表示V1.01版本。
    private String programSmallVersionName;//版本100 表示V1.00版本，101 表示V1.01版本
    private String filterAuthor1;//滤芯认证1   Y-认证  N-未认证
    private String filterAuthor2;//滤芯认证2   Y-认证  N-未认证
    private String filterAuthor3;//滤芯认证3   Y-认证  N-未认证
    private String filterAuthor4;//滤芯认证4   Y-认证  N-未认证
    private String filterAuthor5;//滤芯认证5   Y-认证  N-未认证

    private String isAuthorization;//设备认证  Y-认证  N-未认证
    private String isLock;//是否锁机

    private String company;//从条码库移过来的公司id
    private String proId;//产品id
    private String iccid;//iccid
    private String imei;//imei
    private String proName = "";//产品名称
    private String proMode = "";//产品型号
    private String proImg = "";//产品图片

}
