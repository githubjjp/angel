###产测配置表
DROP TABLE IF EXISTS `t_qc_device_config`;
CREATE TABLE `t_qc_device_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `product_code` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '产品编码',
  `product_name` varchar(60) CHARACTER SET utf8 DEFAULT NULL COMMENT '产品名称',
  `product_model` varchar(60) CHARACTER SET utf8 DEFAULT NULL COMMENT '产品型号',
  `product_conn_function` varchar(16) CHARACTER SET utf8 DEFAULT NULL COMMENT '产品连接方式',
  `max_water_temperature` int(11) DEFAULT NULL COMMENT '最大水温',
  `min_water_temperature` int(11) DEFAULT NULL COMMENT '最小水温',
  `max_water_amount` int(11) DEFAULT NULL COMMENT '最大水量',
  `min_water_amount` int(11) DEFAULT NULL COMMENT '最小水量',
  `max_inlet_tds` int(11) DEFAULT NULL COMMENT '最大进水TDS值',
  `min_inlet_tds` int(11) DEFAULT NULL COMMENT '最小进水TDS值',
  `max_out_tds` int(11) DEFAULT NULL COMMENT '最大出水TDS值',
  `min_out_tds` int(11) DEFAULT NULL COMMENT '最小出水TDS值',
  `max_desalination_rate` int(11) DEFAULT NULL COMMENT '最大脱盐率',
  `min_desalination_rate` int(11) DEFAULT NULL COMMENT '最小脱盐率',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建人',
  `updated_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `updated_by` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '最后修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
