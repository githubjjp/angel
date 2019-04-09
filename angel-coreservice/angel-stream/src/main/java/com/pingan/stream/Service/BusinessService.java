package com.pingan.stream.Service;

import java.util.Map;

public interface BusinessService {
    /**
     * 具体业务处理
     * @param message
     */
    public void process(String message);

    /**
     * 获取连接字符串
     * @param deviceId
     * @return
     */
    public Map<String,Object> getConnectionStr(String deviceId);

    /**
     * 是否上下线验证
     * @param deviceId
     * @return
     */
    public Map<String,Object> isOnline(String deviceId);

    /**
     * 用户实时获取数据指令-16
     * @param deviceId
     * @param barcodeId
     */
    public String issueCmd16(String deviceId,String barcodeId);

    /**
     * 设备控制指令-17
     * @param deviceId
     * @param barcodeId
     * @param type    控制类型  0x01:冲洗  0x02:开机  0x04:关机  0x08:锁机 0x10:预留  0x20:预留
     * @return
     */
    public String  issueCmd17(String deviceId,String barcodeId,String type);

    /**
     * 校时指令-18
     * @param deviceId
     * @param barcodeId
     * @return
     */
    public String issueCmd18(String deviceId,String barcodeId);

    /**
     * 认证指令-20
     * @param deviceId
     * @param barcodeId
     * @param type
     * @return
     */
    public String issueCmd20(String deviceId,String barcodeId,String type);


}
