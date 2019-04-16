package com.pingan.angel.admin.controller.api;

import com.pingan.angel.admin.api.mongodb.QcDeviceEntity;
import com.pingan.angel.admin.service.AngleApiService;
import com.pingan.angel.admin.service.mongodb.service.SysConfigService;
import com.pingan.angel.common.core.util.Result;
import com.pingan.stream.Service.IotHubService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 设备注册相关
 */
@RestController
@AllArgsConstructor
@RequestMapping("/openapi/angel")
public class AngelOpenapiController {


    @Autowired
    private AngleApiService angleApiService;

    @Autowired
    private IotHubService iotHubService;


    /***
     * @param snCode
     * @param barcodeId
     * @param activeId
     * @return
     */
    @RequestMapping("/snCodeUnion")
    public Result snCodeUnion(@PathVariable String barcodeId, @PathVariable String snCode, @PathVariable String activeId) {
        angleApiService.snCodeUnion(snCode, barcodeId, activeId);

        return new Result();
    }

    /**
     * 设备信息补录
     *
     * @param id
     * @return
     */
    @GetMapping("/upload")
    public Result update(@PathVariable Integer id, @PathVariable String customerSuperCode,
                         @PathVariable String barcodeId, @PathVariable String logisticsCode,
                         @PathVariable String sign) {

        return new Result();
    }


}
