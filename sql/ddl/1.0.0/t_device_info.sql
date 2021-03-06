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
  `device_secret` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '设备密匙',
  `program_main_supplier` int(11)  DEFAULT NULL COMMENT '主板固件供应商   1.拓邦 2.精诚 3.兴通 4.英唐 5.海和 6.胤桥 7.安吉尔',
  `program_main_version` int(11)  DEFAULT NULL COMMENT '主板固件版本号   版本100 表示V1.00版本，101 表示V1.01版本。',
  `program_main_version_name` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '版本100 表示V1.00版本，101 表示V1.01版本',
  `mac` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT 'MAC地址(WIFI)/IMEI(GPRS)',
  `ccid` int(11)  DEFAULT NULL COMMENT 'CCID(gprs信号)',
  `program_small_supplier` int(11)  DEFAULT NULL COMMENT '小板供应商   1.拓邦 2.精诚 3.兴通 4.英唐 5.海和 6.胤桥 7.安吉尔',
  `program_small_version` int(11)  DEFAULT NULL COMMENT '小板固件版本号   版本100 表示V1.00版本，101 表示V1.01版本。',
  `program_small_version_name` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '版本100 表示V1.00版本，101 表示V1.01版本',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建人',
  `updated_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `filter_author1` char(1) CHARACTER SET utf8 DEFAULT 'N' COMMENT '滤芯认证1   Y-认证  N-未认证',
  `filter_author2` char(1) CHARACTER SET utf8 DEFAULT 'N' COMMENT '滤芯认证2   Y-认证  N-未认证',
  `filter_author3` char(1) CHARACTER SET utf8 DEFAULT 'N' COMMENT '滤芯认证3   Y-认证  N-未认证',
  `filter_author4` char(1) CHARACTER SET utf8 DEFAULT 'N' COMMENT '滤芯认证4   Y-认证  N-未认证',
  `filter_author5` char(1) CHARACTER SET utf8 DEFAULT 'N' COMMENT '滤芯认证5   Y-认证  N-未认证',
  `is_authorization` char(1) CHARACTER SET utf8 DEFAULT 'N' COMMENT '设备认证   Y-认证  N-未认证',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
