package com.pingan.stream.Service;

import com.pingan.stream.entity.DeviceReportOrderEntity;

import java.util.List;

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
     * 根据命令字查询指令集合
     * @param cmd
     * @return
     */
    public List<DeviceReportOrderEntity> findByCmd(int cmd);

    /**
     * 查询全部指令数据
     * @return
     */
    public List<DeviceReportOrderEntity> findAll();

    /**
     *  查询指令详情
     * @param id
     * @return
     */
    public DeviceReportOrderEntity findById(String id);



}
