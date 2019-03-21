package com.pingan.stream.common;

/**
 * 定义一些常量
 */
public class Content {
    /**
     * 消息处理ok-00
     */
    public static final String MSG_OK="00";
    /**
     * 消息处理error-01
     */
    public static final String MSG_ERROR="01";
    /**
     *  消息处理反馈说明-消息反馈成功
     */
    public static final String MSG_OK_CONTENT="消息反馈成功";
    /**
     *  消息处理反馈说明-消息反馈失败
     */
    public static final String MSG_ERROR_CONTENT="消息反馈失败";
    /**
     *  请求结果说明-请求成功
     */
    public static final String REQ_OK_CONTENT="请求成功";
    /**
     *  请求结果说明-请求失败
     */
    public static final String REQ_ERROR_CONTENT="请求失败";
    /**
     * 请求成功-200
     */
    public static final String REQ_OK="200";
    /**
     *  请求失败-300
     */
    public static final String REQ_ERROR="300";
    /**
     *  请求参数为空-01
     */
    public static final String PARAM_NULL="01";
    /**
     *  MQ类型-rabbitMQ
     */
    public static final String MQ_TYPE_1="rabbitMQ";
    /**
     *  MQ类型-kafka
     */
    public static final String MQ_TYPE_2="kafka";
    /**
     *  主题-监听设备指令
     */
    public static final String TOPIC_TYPE_1="shakeHands";
    /**
     * 告警指令
     */
    public static final String CMD_21="21";
    /**
     * 设备主动上报数据指令
     */
    public static final String CMD_22="22";
    /**
     * 请求服务器数据指令
     */
    public static final String CMD_25="25";
    /**
     * 设备上传机器状态指令
     */
    public static final String CMD_29="29";
    /**
     * 请求升级文件指令
     */
    public static final String CMD_32="32";
    /**
     * 升级完成状态指令
     */
    public static final String CMD_33="33";
    /**
     * 定时上报心跳指令
     */
    public static final String CMD_34="34";
    /**
     * 滤芯复位上报指令
     */
    public static final String CMD_35="35";
    /**
     * 指令类型-设备主动上报指令
     */
    public static final String CMD_STYLE_0="设备上报指令";
    /**
     * 指令类型-服务器主动下发指令
     */
    public static final String CMD_STYLE_1="服务器下发指令";




}
