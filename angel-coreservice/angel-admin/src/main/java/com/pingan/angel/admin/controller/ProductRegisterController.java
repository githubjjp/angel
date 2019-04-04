package com.pingan.angel.admin.controller;

import com.pingan.angel.common.core.constant.DataEncryption;
import com.pingan.angel.common.core.util.Result;
import com.pingan.stream.Service.IotHubService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 设备注册相关
 */
@RestController
@AllArgsConstructor
@RequestMapping("/registerInfo")
public class ProductRegisterController {

    private String productId = "";

    private String MD5Key = "";

    private final IotHubService iotHubService;

    /**
     * 设备信息注册
     *
     * @param id
     * @return
     */
    @GetMapping("/auth")
    public Result upload(@PathVariable Integer id, @PathVariable String barcodeId, @PathVariable String sign) {
        if (sign == null || !sign.equals(DataEncryption.md5(barcodeId, MD5Key))) {
            return new Result();
        }

        String registeResult = iotHubService.registDevice(barcodeId);
        if (registeResult != null) {
            //解析数据


            //存库Mysql


        } else {
            //暂时注册失败存库Mysql

        }
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
        // md5解密比对

        //MySql入库
        return new Result();
    }


}
