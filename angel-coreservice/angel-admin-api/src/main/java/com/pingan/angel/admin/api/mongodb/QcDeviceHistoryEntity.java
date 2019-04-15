package com.pingan.angel.admin.api.mongodb;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * 产测纪录表
 */
@Document(collection = "qcDeviceHistoryEntity")
@Data
public class QcDeviceHistoryEntity extends BaseMongoEntity implements Serializable {

    private String id;//产测记录id
    private String productCode;//产品编码
    private String activeId;//设备开机码
    private String barCodeId;//配件码
    private String snCode;//物流码
    private String mac;//mac地址
    private String iccid;//ICCID
    private String imei;//IMEI
    private String electricBoard;//电控板
    private Date activeTime;//产测时间
    private Integer inletTds;//进水TDS值
    private Integer outTds;//出水TDS值
    private Integer desalinationRate = 0;//脱盐率
    private Integer inletWaterTemperature;//进水温度
    private Integer outWaterTemperature;//出水温度
    private double pureWaterAmount=0;//纯水总量
    private double waterAmount =0;//总水量
    private boolean testSuccess = false;//产测结果，是否成功
    private String remark;//备注
    private String testUserName;//产测用户
    private String testAccount;//产测账号
    private Date completeTime;//产测结束时间
    private boolean startupTest = false;//开机测试是否成功
    private Date startupTestTime;//开机测试时间
    private boolean shutdownTest = false;//关机测试是否成功
    private Date shutdownTestTime;//开机测试时间
    private boolean washTest = false;//冲洗测试是否成功
    private Date washTestTime;//冲洗测试时间
    private String customerSuperCode;//水机大客户码
    private String customerSuperId;//大客户Id
//    private boolean isRealTime;//是否实时信息
//
//    public boolean isRealTime() {
//        if(this.getUpdateTime()==null) return false;
//        return (System.currentTimeMillis() - this.getUpdateTime().getTime())<60000;
//    }
}
