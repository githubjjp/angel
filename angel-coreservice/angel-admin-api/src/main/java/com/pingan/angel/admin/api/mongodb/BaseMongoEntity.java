package com.pingan.angel.admin.api.mongodb;

import lombok.Data;

import java.util.Date;

@Data
public class BaseMongoEntity {
    private Date createTime;//创建日期
    private String createUser;//创建人
    private Date updateTime;//修改时间
    private String updateUser;//修改人
}
