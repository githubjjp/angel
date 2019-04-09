package com.pingan.angel.admin.api.mysql;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 *  设备滤芯状态表
 */
@Data
public class FilterElementEntity {
    @Id
    private Long id;//主键   关联设备状态表的filterElementId 字段
    private String deviceId;//设备id
    private double configTotalFlowFilterCount1;//第1个滤芯的配置总流量值（固定）
    private Date invalidDateFilter1;//第1个滤芯的失效时间（固定）
    private int reportHourFilterCount1;//第1个滤芯上报剩余时长
    private double reportFlowFilterCount1;//第1个滤芯上报剩余流量
    private double configTotalFlowFilterCount2;//第2个滤芯的配置总流量值（固定）
    private Date invalidDateFilter2;//第2个滤芯的失效时间（固定）
    private int reportHourFilterCount2;//第2个滤芯上报剩余时长
    private double reportFlowFilterCount2;//第2个滤芯上报剩余流量
    private double configTotalFlowFilterCount3;//第3个滤芯的配置总流量值（固定）
    private Date invalidDateFilter3;//第3个滤芯的失效时间（固定）
    private int reportHourFilterCount3;//第3个滤芯上报剩余时长
    private double reportFlowFilterCount3;//第3个滤芯上报剩余流量
    private double configTotalFlowFilterCount4;//第4个滤芯的配置总流量值（固定）
    private Date invalidDateFilter4;//第4个滤芯的失效时间（固定）
    private int reportHourFilterCount4;//第4个滤芯上报剩余时长
    private double reportFlowFilterCount4;//第4个滤芯上报剩余流量
    private double configTotalFlowFilterCount5;//第5个滤芯的配置总流量值（固定）
    private Date invalidDateFilter5;//第5个滤芯的失效时间（固定）
    private int reportHourFilterCount5;//第5个滤芯上报剩余时长
    private double reportFlowFilterCount5;//第5个滤芯上报剩余流量
    private double configTotalFlowFilterCount6;//第6个滤芯的配置总流量值（固定）
    private Date invalidDateFilter6;//第6个滤芯的失效时间（固定）
    private int reportHourFilterCount6;//第6个滤芯上报剩余时长
    private double reportFlowFilterCount6;//第6个滤芯上报剩余流量
    private double configTotalFlowFilterCount7;//第7个滤芯的配置总流量值（固定）
    private Date invalidDateFilter7;//第7个滤芯的失效时间（固定）
    private int reportHourFilterCount7;//第7个滤芯上报剩余时长
    private double reportFlowFilterCount7;//第7个滤芯上报剩余流量
    private double configTotalFlowFilterCount8;//第8个滤芯的配置总流量值（固定）
    private Date invalidDateFilter8;//第8个滤芯的失效时间（固定）
    private int reportHourFilterCount8;//第8个滤芯上报剩余时长
    private double reportFlowFilterCount8;//第8个滤芯上报剩余流量
    private double configTotalFlowFilterCount9;//第9个滤芯的配置总流量值（固定）
    private Date invalidDateFilter9;//第9个滤芯的失效时间（固定）
    private int reportHourFilterCount9;//第9个滤芯上报剩余时长
    private double reportFlowFilterCount9;//第9个滤芯上报剩余流量
    private double configTotalFlowFilterCount10;//第10个滤芯的配置总流量值（固定）
    private Date invalidDateFilter10;//第10个滤芯的失效时间（固定）
    private int reportHourFilterCount10;//第10个滤芯上报剩余时长
    private double reportFlowFilterCount10;//第10个滤芯上报剩余流量
}
