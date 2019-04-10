###设备信息表
DROP TABLE IF EXISTS `t_device_info`;
CREATE TABLE `t_device_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `sn_code` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '整机码，物流码',
  `device_id` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '设备id',
  `customer_super_code` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '大客户码',
  `barcode_id` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '配件码',
  `active_id` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '开机码',
  `customer_sn` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '大客户批次码',
  `is_activation` varchar(1) CHARACTER SET utf8 DEFAULT 'N' COMMENT 'Y已激活 N未激活',
  `is_product_test` varchar(1) CHARACTER SET utf8 DEFAULT NULL COMMENT 'Y 已产测  N未产测 ',
  `is_online` varchar(1) CHARACTER SET utf8 DEFAULT 'N' COMMENT 'Y在线 N不在线',
  `is_online_date` datetime DEFAULT NULL COMMENT '上线或下线时间',
  `wifi_or_gprs` varchar(1) CHARACTER SET utf8 DEFAULT NULL COMMENT '联网方式 1支持wifi 2支持gprs',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建人',
  `updated_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `updated_by` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '最后修改人',
  `device_secret` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '设备密匙',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
