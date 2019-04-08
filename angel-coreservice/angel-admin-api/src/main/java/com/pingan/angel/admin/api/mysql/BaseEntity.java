package com.pingan.angel.admin.api.mysql;

import lombok.Data;

import java.util.Date;
@Data
public class BaseEntity {
    private Date createdTime;//创建时间
    private String createdBy;//创建人
    private Date updatedTime;//更新时间
    private String updatedBy;//更新人

}
