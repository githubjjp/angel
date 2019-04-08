package com.pingan.angel.admin.api.entity;

public enum ResultCode {

    SUCCESS("000","成功"),
    ERROR("999","失败"),
    /**
     * 020 - 029 请求类错误
     */
    REQUEST_PARAM_ERR("020","请求参数错误"),
    REQUEST_PARAM_LOSET("021","缺少必传参数"),
    REQUEST_PARAM_TYPE_ERROR("022","参数类型错误"),
    REQUEST_PARAM_EMPTY("023","参数为空"),
    REQUEST_EXPIRE("024","请求过期");

    String code;
    String msg;

    ResultCode(String code,String msg){
        this.msg = msg;
        this.code = code ;
    }

    public String getCode(){
        return this.code;
    }

    public String getMsg() {
        return msg;
    }
}
