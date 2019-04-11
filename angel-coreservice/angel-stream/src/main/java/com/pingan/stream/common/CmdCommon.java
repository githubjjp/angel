package com.pingan.stream.common;

import java.util.HashMap;
import java.util.Map;

public class CmdCommon {
    /**
     * 指令说明
     */
    public static final Map<Integer,String> cmd_text_map=new HashMap<Integer,String>();
    /**
     * 故障代码说明
     */
    public static final  Map<Integer,String> CMD_FAULT_CONTENT=new HashMap<Integer,String>();
    /**
     * 保护代码说明
     */
    public static final  Map<Integer,String> CMD_PROTECT_CONTENT=new HashMap<Integer,String>();

    public static final Map<Integer,String> CMD35_D2_CONTENT=new HashMap<Integer,String>();
    static{
        cmd_text_map.put(16,"用户实时获取数据指令");
        cmd_text_map.put(17,"设备控制指令");
        cmd_text_map.put(18,"校时指令");
        cmd_text_map.put(20,"认证指令");
        cmd_text_map.put(21,"告警指令");
        cmd_text_map.put(22,"设备主动上报数据指令");
        cmd_text_map.put(23,"发送上报数据的时间间隔指令");
        cmd_text_map.put(24,"清除认证状态指令");
        cmd_text_map.put(25,"请求服务器数据指令");
        cmd_text_map.put(26,"请求SIM卡的CCID指令");
        cmd_text_map.put(27,"工作模式选择指令");
        cmd_text_map.put(28,"测试模式读取数据指令");
        cmd_text_map.put(29,"设备上传机器状态指令");
        cmd_text_map.put(30,"滤芯数据下发指令");
        cmd_text_map.put(31,"在线升级指令");
        cmd_text_map.put(32,"请求升级文件指令");
        cmd_text_map.put(33,"升级完成状态指令");
        cmd_text_map.put(34,"定时上报心跳指令");
        cmd_text_map.put(35,"滤芯复位上报指令");

        CMD_FAULT_CONTENT.put(0x0000,"无故障");
        CMD_FAULT_CONTENT.put(0x0001,"加热NTC故障");
        CMD_FAULT_CONTENT.put(0x0002,"预热NTC故障");
        CMD_FAULT_CONTENT.put(0x0004,"温水NTC故障");
        CMD_FAULT_CONTENT.put(0x0008,"冷水NTC故障");
        CMD_FAULT_CONTENT.put(0x0010,"溢水故障");
        CMD_FAULT_CONTENT.put(0x0020,"低水位故障");
        CMD_FAULT_CONTENT.put(0x0040,"漏水故障");
        CMD_FAULT_CONTENT.put(0x0080,"连续制水故障");
        CMD_FAULT_CONTENT.put(0x0100,"进水电磁阀故障");
        CMD_FAULT_CONTENT.put(0x0200,"出水电磁阀故障");
        CMD_FAULT_CONTENT.put(0x0400,"冲洗电磁阀故障");

        CMD_PROTECT_CONTENT.put(0x0000,"无保护");
        CMD_PROTECT_CONTENT.put(0x0001,"补水电磁阀保护");
        CMD_PROTECT_CONTENT.put(0x0002,"连续制水工作保护");
        CMD_PROTECT_CONTENT.put(0x0004,"干烧保护");
        CMD_PROTECT_CONTENT.put(0x0008,"滤芯寿命到期");
        CMD_PROTECT_CONTENT.put(0x0010,"制冷反馈信号线异常(Y1251)");
        CMD_PROTECT_CONTENT.put(0x0020,"保留");
        CMD_PROTECT_CONTENT.put(0x0040,"保留");
        CMD_PROTECT_CONTENT.put(0x0080,"保留");

        CMD35_D2_CONTENT.put(1,"设备版本比升级版本高");
        CMD35_D2_CONTENT.put(2,"数据接收错误，校验失败");
        CMD35_D2_CONTENT.put(3,"数据长度不符");
        CMD35_D2_CONTENT.put(4,"请求无应答数据");
    }
}
