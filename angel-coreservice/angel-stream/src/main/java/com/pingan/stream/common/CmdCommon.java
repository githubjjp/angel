package com.pingan.stream.common;

import java.util.HashMap;
import java.util.Map;

public class CmdCommon {
    public static final Map<Integer,String> cmd_text_map=new HashMap<Integer,String>();
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
    }
}
