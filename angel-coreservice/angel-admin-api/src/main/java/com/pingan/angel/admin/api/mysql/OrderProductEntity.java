package com.pingan.angel.admin.api.mysql;

import lombok.Data;

@Data
public class OrderProductEntity extends BaseEntity {

    private String code ;//产品编码
    private String imgStr;//产品图片
    private String name;//产品名称
    private String model;//产品型号
    private String spec;//产品规格
    private String color;//产品颜色
    private int tdsShow =0;//产品TDS显示,0不显示,1进水，2出水，3进水和出水
    private String washFunction  = "N";//冲洗功能
    private String startUpFunction  = "N";//开关机功能
    private String setTimeFunction  = "N";//定时开关机功能
    private int temperatureShow =0;//水温显示,0不显示,1进水，2出水，3进水和出水
    private String connFunction;//连接方法
    private String communicationCode;//通讯编码
    private String productType;//产品类型
    private String productTypeId;//产品类型ID
    private String erpId;//ERP产品Id
    private String filterLifeUnit;//滤芯寿命单位（天/时）
    private String runStatusShow;//运行状态显示（以逗号分隔开）
    private String enableFilterFlow = "Y";//启用滤芯剩余水量
    private String enableFilterLife = "Y";//启用滤芯剩余寿命
    private String isOnlyBusiness  = "N";//是否是商用设备
}
