package com.pingan.stream.Service;

/**
 * 服务器下发控制指令
 */
public interface IssueCmdService {
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

    /**
     * 发送上报数据的时间间隔指令-23
     * @param deviceId
     * @param barcodeId
     * @param hour    间隔时间  1-24h
     * @return
     */
    public String issueCmd23(String deviceId,String barcodeId,int hour);

    /**
     * 清除认证状态指令-24
     * @param deviceId
     * @param barcodeId
     * @return
     */
    public String issueCmd24(String deviceId,String barcodeId);

    /**
     * 请求SIM卡的CCID指令-26
     * @param deviceId
     * @param barcodeId
     * @return
     */
    public String issueCmd26(String deviceId,String barcodeId);

    /**
     * 工作模式选择指令-27
     * @param deviceId
     * @param barcodeId
     * @param type  1-进入正常模式 2-进入工厂测试模式
     * @return
     */
    public String issueCmd27(String deviceId,String barcodeId,int type);

    /**
     * 测试模式读取数据指令-28
     * @param deviceId
     * @param barcodeId
     * @return
     */
    public String issueCmd28(String deviceId,String barcodeId);

    /**
     * 滤芯数据下发指令-30
     * @param deviceId
     * @param barcodeId
     * @return
     */
    public String issueCmd30(String deviceId,String barcodeId);

    /**
     * 在线升级指令-31
     * @param deviceId
     * @param barcodeId
     * @return
     */
    public String issueCmd31(String deviceId,String barcodeId);


}
