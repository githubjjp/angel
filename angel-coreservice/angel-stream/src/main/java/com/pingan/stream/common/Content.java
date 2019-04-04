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
     *  MQ类型-kafka
     */
    public static final String MQ_TYPE="kafka";
    /**
     *  主题-监听设备指令
     */
    public static final String TOPIC_TYPE_1="shakeHands";
    /**
     * 订阅消息-上下线消息
     */
    public static final String MSTYPE_1="1";
    /**
     * 订阅消息-普通消息
     */
    public static final String MSTYPE_0="0";
    /**
     *  指令状态-未处理
     */
    public static final String DEAL_ORDER_0="0";
    /**
     * 指令状态-已处理
     */
    public static final String DEAL_ORDER_1="1";





}
