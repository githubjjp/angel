package com.pingan.angel.admin.controller.api;


import com.pingan.angel.common.core.util.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 设备激活相关
 */
@RestController
@RequestMapping("/product")
public class ProductActiveController {

    private String productId = "";

    /**
     * 设备激活
     *
     * @param id
     * @return
     */
    @GetMapping("/avtive")
    public Result update(@PathVariable Integer id, @PathVariable String customerSuperCode,
                         @PathVariable String barcodeId, @PathVariable String logisticsCode,
                         @PathVariable String sign) {

        // md5解密比对

        // 调用iot服务

        // MySql修改数据库设备状态
        return new Result();
    }

    /**
     * 设备基本信息查询
     *
     * @param id
     * @return
     */
    @GetMapping("/geInfo")
    public Result geInfo(@PathVariable Integer id, @PathVariable String snCode,
                         @PathVariable String sign) {

        // md5解密比对

        // 调用iot服务

        // MySql数据保存
        return new Result();
    }


    /**
     * 设备在离线查询
     *
     * @param id
     * @return
     */
    @GetMapping("/isOnline")
    public Result isOnline(@PathVariable Integer id, @PathVariable String customerSuperCode,
                           @PathVariable String barcodeId, @PathVariable String logisticsCode,
                           @PathVariable String sign) {

        // md5解密比对


        // 调用iot服务

        return new Result();
    }


    /**
     * 设备控制
     *
     * @param id
     * @return
     */
    @GetMapping("/cmd")
    public Result cmdDevice(@PathVariable Integer id, @PathVariable String customerSuperCode,
                            @PathVariable String barcodeId, @PathVariable String logisticsCode,
                            @PathVariable String sign) {

        // md5解密比对


        // 调用iot服务

        return new Result();
    }


    /**
     * 设备实时信息查询
     *
     * @param id
     * @return
     */
    @GetMapping("/getDeviceNow")
    public Result getDeviceNow(@PathVariable Integer id, @PathVariable String customerSuperCode,
                               @PathVariable String barcodeId, @PathVariable String logisticsCode,
                               @PathVariable String sign) {

        // md5解密比对


        // 调用iot服务

        return new Result();
    }


    /**
     * 滤芯重置api
     *
     * @param id
     * @return
     */
    @GetMapping("/resetFilter")
    public Result resetFilter(@PathVariable Integer id, @PathVariable String customerSuperCode,
                              @PathVariable String barcodeId, @PathVariable String logisticsCode,
                              @PathVariable String sign) {

        // md5解密比对


        // 调用iot服务

        return new Result();
    }


    /**
     * 用水量查询
     *
     * @param id
     * @return
     */
    @GetMapping("/useWaterInfo")
    public Result useWaterInfo(@PathVariable Integer id, @PathVariable String customerSuperCode,
                               @PathVariable String barcodeId, @PathVariable String logisticsCode,
                               @PathVariable String sign) {

        // md5解密比对


        // 调用iot服务

        return new Result();
    }

}
