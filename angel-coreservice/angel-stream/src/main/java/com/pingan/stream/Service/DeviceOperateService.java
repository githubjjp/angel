package com.pingan.stream.Service;

import com.pingan.stream.entity.DeviceReportOrderEntity;

import java.util.List;
import java.util.Map;

/**
 * 设备操作相关消息处理
 */
public interface DeviceOperateService {
    /**
     * 设备主动上报指令处理
     * @param data
     */
    public void receiveDeviceOrder(String data);

    /**
     * 根据条件查询集合
     * @param param
     * @return
     */
    public List<DeviceReportOrderEntity> findAll(Map<String, Object> param);

    /**
     *  查询指令详情
     * @param id
     * @return
     */
    public DeviceReportOrderEntity findById(String id);



}
