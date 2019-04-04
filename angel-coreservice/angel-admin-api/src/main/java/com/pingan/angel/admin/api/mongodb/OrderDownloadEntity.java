package com.pingan.angel.admin.api.mongodb;

import lombok.Data;

/**
 *  下发指令对象
 */
@Data
public class OrderDownloadEntity{
    private int operateType;//cmd=17    d10x01:冲洗   0x02:开机 0x04:关机 0x08:锁机 0x10:预留 0x20:预留 0x40:预留 0x80:预留
    private String systemTime;//cmd=18  yyyy-MM-dd HH:mm:ss  对应 d1-d6
    private int certificateType;//cmd=20  d1: 0x01 第1个滤芯认证 0x02 第2个滤芯认证 0x03 第3个滤芯认证 0x04 第4个滤芯认证 0x05 第5个滤芯认证 0x06 发送开机码(大写的字符串形式)
    private String filterHour;//cmd=20 d2: 表示第1-5个滤芯的小时数   开机码(字符串，20位)
    private String filterFlow;//cmd=20 d3: 表示第1-5个滤芯的流量数   00



}
