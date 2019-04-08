package com.pingan.angel.admin.api.mongodb;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * 产测设备信息表
 */
@Document(collection = "QcDeviceEntity")
@Data
public class QcDeviceEntity implements Serializable {
    private String wifi		;//是否支持wifi
    private String snCode		;//整机码/物流码
    private Date lastActiveTime		;//最后活跃时间，每次心跳更新
    private boolean isAuthorization		;//是否认证
    private String updateUser	;
    private boolean isOnline		;//是否在线
    private Date updateTime		;//修改时间
    private String deviceId		;//设备id
    private String	iccid;//ICCID
    private boolean isOpen		;//是否开机
    private Date createTime		;//创建时间
    private String bulutooth		;//是否支持蓝牙
    private String proId		;//产品Id
    private String qcHistoryId		;//产测记录Id
    private String company		;//滤芯数量
    private String gprs		;//是否支持GPRS
    private String createUser	;
    private String	id;
    private	String	barcodeId;//配件码
    private	String	activeCode;//设备激活码
    private	boolean	testSuccess;//产测是否通过

}
