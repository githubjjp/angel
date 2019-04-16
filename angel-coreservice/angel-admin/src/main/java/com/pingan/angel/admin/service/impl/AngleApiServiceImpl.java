package com.pingan.angel.admin.service.impl;

import com.pingan.angel.admin.api.mongodb.QcDeviceEntity;
import com.pingan.angel.admin.service.AngleApiService;
import com.pingan.angel.common.core.util.Result;
import com.pingan.angel.qctest.service.DeviceService;
import com.pingan.angel.qctest.service.QcDeviceHistoryService;
import com.pingan.angel.qctest.service.QcDeviceService;
import com.pingan.stream.Service.IotHubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("angleApiService")
public class AngleApiServiceImpl implements AngleApiService {

    @Autowired
    public DeviceService deviceService;

    @Autowired
    public QcDeviceService qcDeviceService;


    @Autowired
    public QcDeviceHistoryService qcDeviceHistoryService;


    @Autowired
    public IotHubService iotHubService;

    @Override
    public Result snCodeUnion(String snCode, String barcodeId, String activeId) {
        QcDeviceEntity qdeByBar = qcDeviceService.findByBarCodeId(barcodeId);

        // 无设备信息 ，调用iot注册，
        if (qdeByBar == null) {
            String result = iotHubService.registDevice(snCode);
            qdeByBar = new QcDeviceEntity();
            qdeByBar.setSnCode(snCode);
            qdeByBar.setBarcodeId(barcodeId);
            qdeByBar.setActiveCode(activeId);
            qcDeviceService.add(qdeByBar);
            return new Result(0, "success", qdeByBar);
        }

        if (snCode.equals(qdeByBar.getSnCode())) {
            return new Result(1, "配件码已跟其他整机码关联");
        }

        QcDeviceEntity qdeBySnCode = qcDeviceService.findBySnCode(snCode);
        String dbActiveCode = qdeBySnCode.getActiveCode();

        if (barcodeId.equals(qdeBySnCode.getBarcodeId()) && activeId.equals(dbActiveCode)) {
            return new Result(0, "success", qdeBySnCode);
        }

        if (qdeBySnCode.isTestSuccess()) {
            deviceService.updateQCTestUndoById(null);
        }
        qcDeviceService.updateById(qdeByBar);

        if (!barcodeId.equals(qdeBySnCode.getBarcodeId())) {

        }

        return null;
    }
}
