package com.pingan.angel.admin.api.mysql;

import lombok.Data;

/**
 * 大客户信息表
 */
@Data
public class CoreCustomerSuperEntity extends BaseEntity{

    private String id;//大客户二维码id
    private String userId;//关联用户Id
    private String customerCode;//大客户码
    private String isLockDevice;//是否需要锁机

    /**
     *公司/组织/个人
     */
    private String identityType;//客户身份类型
    private String name;//姓名/组织/公司名
    private String idNo;//身份证号/社会统一信用代码
    private String phone;//联系手机
    private String tel;//固定电话
    private String email;//Email
    private String birthDay;//出生年月yyyy-MM-dd
    private int selfCustomerCount;//下级终端客户总数
    private int deviceTotalCount;//设备总数
    private int deviceBusinessCount;//商用机总数
    private int deviceSelfCount;//家用机总数
    private String bindSalesId;//关联销售Id
    private String industryId;//行业id
    private String industryName;//行业名称
    private String linkmanName;//联系人名称
    private String qqNo;//qq号
    private String payCompanyId;//结算公司id
    private String payCompanyName;//结算公司名称
}
