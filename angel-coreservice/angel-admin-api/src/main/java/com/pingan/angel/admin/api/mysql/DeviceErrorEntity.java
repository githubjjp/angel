package com.pingan.angel.admin.api.mysql;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * 设备故障记录表
 */
@Data
public class DeviceErrorEntity extends BaseEntity{
    @Id
    private Long id;//主键
    private String snCode;//整机码/物流码
    private String deviceId;//设备Id
    private Date lastPostTime;//最后一次上报时间
    private int faultCode;//故障代码
    private String isDeal;//设备状态，Y恢复，N故障
    private String faultContent;//故障说明
    private String userId;//用户id
}
