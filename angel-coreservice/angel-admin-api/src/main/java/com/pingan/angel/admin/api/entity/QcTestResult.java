package com.pingan.angel.admin.api.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class QcTestResult implements Serializable {

    private String name;//检测名称
    private int state;//检测状态0待检测1通过2不通过
    private String result;

}
