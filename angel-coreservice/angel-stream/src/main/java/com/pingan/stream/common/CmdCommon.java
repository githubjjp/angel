package com.pingan.stream.common;

import java.util.HashMap;
import java.util.Map;

/**
 *  指令CMD中的一些常量
 */
public class CmdCommon {
    /**
     *  告警指令-21   d2-保护代码说明
     */
    public static Map<String,String> protectionCodeMap21=new HashMap<String,String>();
    /**
     * 告警指令-21   d1-故障代码说明
     */
    public static Map<String,String> errorCodeMap21=new HashMap<String,String>();
    /**
     *  指令说明
     */
    public static Map<String,String> cmdMap=new HashMap<String,String>();

    /**
     * 指令25  d1-说明
     */
    public static Map<String,String> cmdMap25=new HashMap<String,String>();
    /**
     * 指令29 d1说明
     */
    public static Map<String,String> cmdMap29=new HashMap<>();
    /**
     * 指令33 当d1=0 d2说明
     */
    public static Map<String,String> cmdMap33=new HashMap<>();

    public static Map<String,String> cmdMap35_Bit0=new HashMap<String,String>();
    public static Map<String,String> cmdMap35_Bit1=new HashMap<String,String>();
    public static Map<String,String> cmdMap35_Bit2=new HashMap<String,String>();
    public static Map<String,String> cmdMap35_Bit3=new HashMap<String,String>();
    public static Map<String,String> cmdMap35_Bit4=new HashMap<String,String>();

    static{
        protectionCodeMap21.put("0x0000","无保护");
        protectionCodeMap21.put("0x0001","补水电磁阀保护");
        protectionCodeMap21.put("0x0002","连续制水工作保护");
        protectionCodeMap21.put("0x0004","干烧保护");
        protectionCodeMap21.put("0x0008","滤芯寿命到期");
        protectionCodeMap21.put("0x0010","制冷反馈信号线异常(Y1251)");
        protectionCodeMap21.put("0x0020","保留");
        protectionCodeMap21.put("0x0040","保留");
        protectionCodeMap21.put("0x0080","保留");

        errorCodeMap21.put("0x0000","无故障");
        errorCodeMap21.put("0x0001","加热NTC故障");
        errorCodeMap21.put("0x0002","预热NTC故障");
        errorCodeMap21.put("0x0004","温水NTC故障");
        errorCodeMap21.put("0x0008","冷水NTC故障");
        errorCodeMap21.put("0x0010","溢水故障");
        errorCodeMap21.put("0x0020","低水位故障");
        errorCodeMap21.put("0x0040","漏水故障");
        errorCodeMap21.put("0x0080","连续制水故障(取消)");
        errorCodeMap21.put("0x0100","进水电磁阀故障");
        errorCodeMap21.put("0x0200","出水电磁阀故障");
        errorCodeMap21.put("0x0400","冲洗电磁阀故障");

        cmdMap.put("21","告警指令");
        cmdMap.put("22","设备主动上报数据指令");
        cmdMap.put("25","请求服务器数据指令");
        cmdMap.put("29","设备上传机器状态指令");
        cmdMap.put("32","请求升级文件指令");
        cmdMap.put("33","升级完成状态指令");
        cmdMap.put("34","定时上报心跳指令");
        cmdMap.put("35","滤芯复位上报指令");

        cmdMap25.put("1","表示请求服务器更新时间数据，服务器接收之后，发送校时指令，参见第3条的校时指令格式。(设备上电之后会主动请求一次校时指令)");
        cmdMap25.put("2","请求服务器滤芯最大寿命，滤芯剩余寿命，服务器接收之后，发送第14条指令，参见第14条滤芯数据下发指令格式");

        cmdMap29.put("0x01","制水");
        cmdMap29.put("0x02","满水");
        cmdMap29.put("其他","保留");

        cmdMap33.put("1","设备版本比升级版本高");
        cmdMap33.put("2","数据接收错误，校验失败");
        cmdMap33.put("3","数据长度不符");
        cmdMap33.put("4","请求无应答数据");

        cmdMap35_Bit0.put("0","滤芯1不复位");
        cmdMap35_Bit0.put("1","滤芯1复位");

        cmdMap35_Bit1.put("0","滤芯2不复位");
        cmdMap35_Bit1.put("1","滤芯2复位");

        cmdMap35_Bit2.put("0","滤芯3不复位");
        cmdMap35_Bit2.put("1","滤芯3复位");

        cmdMap35_Bit3.put("0","滤芯4不复位");
        cmdMap35_Bit3.put("1","滤芯4复位");

        cmdMap35_Bit4.put("0","滤芯5不复位");
        cmdMap35_Bit4.put("1","滤芯5复位");
    }
}
