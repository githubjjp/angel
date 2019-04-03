package com.pingan.stream.Service;

import java.util.List;

/**
 * IOT请求接口
 */
public interface IotHubService {

    /**
     * 发布消息到topic
     * @param deviceName
     * @param content
     * @param qos 0-最多送达一次   1-至少送达一次   默认为0
     * @return
     */
     public String publish(String deviceName,String content,String qos);

    /**
     * 发布广播消息
     * @param content
     * @param qos  0-最多送达一次   1-至少送达一次   默认为0
     * @return
     */
     public  String publishBroadcast(String content,String qos);

    /**
     * 创建产品
     * @param productName
     * @param productDesc
     * @param authId    1.	证书 2.密钥    默认1
     * @param region     地区ID
     * @return
     */
     public  String createProduct(String productName,String productDesc,String authId,String region);

    /**
     *  查询产品详情
     * @param productId
     * @return
     */
     public  String queryProduct(String productId);

    /**
     * 动态注册设备
     * @param deviceName
     * @return
     */
     public String registDevice(String deviceName);

    /**
     * 批量创建设备
     * @param deviceNames
     * @return
     */
     public String batchCreateDeviceList(List<String> deviceNames);

    /**
     * 批量查询设备
     * @param applyId
     * @param pageIndex
     * @param pageSize
     * @return
     */
     public String queryDeviceListByApplyId(String applyId,int pageIndex,int pageSize);

    /**
     * 查询设备详情
     * @param deviceId
     * @return
     */
     public String queryDevice(String deviceId);

    /**
     * 解禁设备
     * @param deviceName
     * @param type  0-解禁  1-禁用
     * @return
     */
     public String isEnableDevice(String deviceName,String type);

}
