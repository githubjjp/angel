package com.pingan.angel.qctest.service.impl;

import com.pingan.angel.admin.api.dto.QCtestSuccess;
import com.pingan.angel.qctest.mapper.QCTestSuccessMapper;
import com.pingan.angel.qctest.service.QCTestSuccessServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("qcTestSuccessService")
public class QCTestSuccessServiceImpl implements QCTestSuccessServcie {

    @Autowired
    private QCTestSuccessMapper qcTestSuccessMapper;

    /**
     * 通过整机码查询产测通过整机码记录
     * @param snCode 整机码
     * @return 整机码记录
     */
    @Override
    public QCtestSuccess findBySnCode(String snCode) {
        return qcTestSuccessMapper.findBySnCode(snCode);
    }
}
