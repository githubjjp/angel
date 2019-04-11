package com.pingan.angel.admin.api.mysql;

import lombok.Data;

/**
 * 产测配置表对象
 */
@Data
public class QcDeviceConfigEntity extends BaseEntity{

    private String productCode;//产品编码
    private String productName;//产品名称
    private String productModel;//产品型号
    private String productConnFunction;//产品连接方式
    private Integer minInletTds;//最小进水TDS值
    private Integer maxInletTds;//最大进水TDS值
    private Integer minOutTds;//最小出水TDS值
    private Integer maxOutTds;//最大出水TDS值
    private Integer minDesalinationRate = 0;//最小脱盐率
    private Integer maxDesalinationRate = 100;//最大脱盐率
    private Integer minWaterTemperature;//最小水温
    private Integer maxWaterTemperature;//最大水温
    private Integer minWaterAmount;//最小水量
    private Integer maxWaterAmount;//最大水量
}
