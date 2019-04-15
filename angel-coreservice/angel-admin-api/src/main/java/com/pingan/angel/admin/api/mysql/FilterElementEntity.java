package com.pingan.angel.admin.api.mysql;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 *  设备滤芯状态表
 */
@Data
public class FilterElementEntity {
    @Id
    private Long id;//主键   关联设备状态表的filterElementId 字段
    private String deviceId;//设备id
    private double configTotalFlowFilterCount1;//第1个滤芯的配置总流量值（固定）
    private double invalidDateFilter1;//第1个滤芯的失效时间（固定）
    private double reportHourFilterCount1;//第1个滤芯上报剩余时长
    private double reportFlowFilterCount1;//第1个滤芯上报剩余流量
    private double configTotalFlowFilterCount2;//第2个滤芯的配置总流量值（固定）
    private double invalidDateFilter2;//第2个滤芯的失效时间（固定）
    private double reportHourFilterCount2;//第2个滤芯上报剩余时长
    private double reportFlowFilterCount2;//第2个滤芯上报剩余流量
    private double configTotalFlowFilterCount3;//第3个滤芯的配置总流量值（固定）
    private double invalidDateFilter3;//第3个滤芯的失效时间（固定）
    private double reportHourFilterCount3;//第3个滤芯上报剩余时长
    private double reportFlowFilterCount3;//第3个滤芯上报剩余流量
    private double configTotalFlowFilterCount4;//第4个滤芯的配置总流量值（固定）
    private double invalidDateFilter4;//第4个滤芯的失效时间（固定）
    private double reportHourFilterCount4;//第4个滤芯上报剩余时长
    private double reportFlowFilterCount4;//第4个滤芯上报剩余流量
    private double configTotalFlowFilterCount5;//第5个滤芯的配置总流量值（固定）
    private double invalidDateFilter5;//第5个滤芯的失效时间（固定）
    private double reportHourFilterCount5;//第5个滤芯上报剩余时长
    private double reportFlowFilterCount5;//第5个滤芯上报剩余流量

}
