package com.pingan.angel.qctest.service;

import com.pingan.angel.admin.api.mongodb.QcTestSuccessDeviceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 产测api调度服务
 */
@Service
public class QCDeviceDispatchService {

    @Autowired
    private QCTestSuccessServcie qcTestSuccessServcie;


    /**
     * 判断产测是否通过服务
     * @param snCode 整机码
     * @return
     */
    public Map<String, Object> isTestSuccess(String snCode) {
        QcTestSuccessDeviceEntity qcSuccessEntity = qcTestSuccessServcie.findBySnCode(snCode);
        //无论产测是否通过都要去查询到对应的配件码

        return null;
    }
}
