package com.pingan.angel.admin.api.entity;

import lombok.Data;

public enum QcTestStatusEnum {
    WAIT(0, "待检测"),
    SUCCESS(1, "通过"),
    FAIL(2, "不通过");
    int status;
    String value;


    public int getStatus() {
        return status;
    }

    public String getValue() {
        return value;
    }


    QcTestStatusEnum(int status,String value){
        this.status = status;
        this.value = value;
    }

    public QcTestStatusEnum get(Integer status){
        if(status==null) return null;
        for(QcTestStatusEnum t : QcTestStatusEnum.values()){
            if(status==t.status) return t;
        }
        return null;
    }
}
