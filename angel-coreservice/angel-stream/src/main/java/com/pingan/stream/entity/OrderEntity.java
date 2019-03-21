package com.pingan.stream.entity;

import org.springframework.data.mongodb.core.mapping.Field;

public class OrderEntity {
    @Field("flag")
    private int flag;//起始标志
    @Field("version")
    private int version;//协议版本
    @Field("cmd")
    private int cmd;//命令字
    @Field("gprs")
    private int gprs;//GPRS状态信息   1-与服务器连接OK 0-失败  CMD=25的时候为gprs信号强度
    @Field("addr")
    private String addr;//经纬度信息
    @Field("barcodeid")
    private String barcodeid;//配件码
    @Field("len")
    private int len;//数据长度



    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getCmd() {
        return cmd;
    }

    public void setCmd(int cmd) {
        this.cmd = cmd;
    }

    public int getGprs() {
        return gprs;
    }

    public void setGprs(int gprs) {
        this.gprs = gprs;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getBarcodeid() {
        return barcodeid;
    }

    public void setBarcodeid(String barcodeid) {
        this.barcodeid = barcodeid;
    }

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }
}
