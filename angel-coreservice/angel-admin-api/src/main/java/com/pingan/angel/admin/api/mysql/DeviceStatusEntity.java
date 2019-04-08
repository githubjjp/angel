package com.pingan.angel.admin.api.mysql;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 *  设备状态表
 */
@Data
public class DeviceStatusEntity extends BaseEntity{
    @Id
    private Long id;//主键
    private String deviceId;//设备id
    private int cmd;//cmd值
    private double rateOfDesalination;//脱盐率
    private double inTemperature;//进水水温
    private double outTemperature;//出水水温
    private double totalWater;//总水量
    private double totalCleanWater;//总纯水量
    private double totalUsedWater;//历史总用水量
    private Date reportTime;//最后上报时间
    private int outTds;//出水TDS
    private int inTds;//进水TDS
    private String isStartUp;//设备开机状态
    private Date lastControlTime;//最后一次控制时间
    private String configUnit;//上报单位，默认h ，可用值h d 小时或者天,开发需要判断并换上成小时处理
    private int deviceState;//设备状态：1制水/2满水
}
