package com.pingan.angel.qctest.service;

import com.pingan.angel.admin.api.dto.QCtestSuccess;

/**
 * 产测通过服务
 */
public interface QCTestSuccessServcie {

    /**
     * 使用整机码查询产测通过整机码记录
     * @param snCode
     * @return
     */
    QCtestSuccess findBySnCode(String snCode);
}
