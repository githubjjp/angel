package com.pingan.angel.qctest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pingan.angel.admin.api.dto.QCtestSuccess;

public interface QCTestSuccessMapper extends BaseMapper<QCtestSuccess> {

    /**
     * 根据整机码查询产测通过整机码记录
     * @param snCode
     * @return
     */
    QCtestSuccess findBySnCode(String snCode);
}
