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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

#设备状态信息表
DROP TABLE IF EXISTS `t_device_status`;
CREATE TABLE `t_device_status` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `cmd` int(11) DEFAULT NULL COMMENT '最后上报的cmd值',
  `rate_of_desalination` double DEFAULT NULL COMMENT '脱盐率',
  `in_temperature` double DEFAULT NULL COMMENT '进水水温',
  `total_used_water` double DEFAULT NULL COMMENT '历史总用水量',
  `device_state` int(11) DEFAULT NULL COMMENT '设备状态：1制水/2满水',
  `report_time` datetime DEFAULT NULL COMMENT '最后上报时间',
  `report_hour_filter_count8` int(11) DEFAULT NULL COMMENT '第8个滤芯上报剩余时长',
  `total_used_water_month` double DEFAULT NULL COMMENT '当月总用水量',
  `out_tds` int(11) DEFAULT NULL COMMENT '出水TDS',
  `total_water` double DEFAULT NULL COMMENT '总水量',
  `is_start_up` varchar(8) DEFAULT NULL COMMENT '设备开机状态 Y-开机 N-关机',
  `in_tds` int(11) DEFAULT NULL COMMENT '进水TDS',
  `out_temperature` double DEFAULT NULL COMMENT '出水水温',
  `last_control_time` datetime DEFAULT NULL COMMENT '最后一次控制时间',
  `config_unit` varchar(2) DEFAULT NULL COMMENT '上报单位，默认h ，可用值h d 小时或者天,开发需要判断并换上成小时处理',
  `device_id` varchar(50) NOT NULL DEFAULT '设备id',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `updated_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `updated_by` varchar(32) DEFAULT NULL COMMENT '最后修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8


###设备滤芯状态信息表
DROP TABLE IF EXISTS `t_device_filter_element`;
CREATE TABLE `t_device_filter_element` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `device_id` varchar(50) NOT NULL DEFAULT '设备id',
  `config_total_flow_filter_count1` double DEFAULT NULL COMMENT '第1个滤芯的配置总流量值（固定）',
  `invalid_date_filter1` datetime DEFAULT NULL COMMENT '第1个滤芯的失效时间（固定）',
  `report_hour_filter_count1` int(11) DEFAULT NULL COMMENT '第1个滤芯上报剩余时长',
  `report_flow_filter_count1` double DEFAULT NULL COMMENT '第1个滤芯上报剩余流量',
  `config_total_flow_filter_count2` double DEFAULT NULL COMMENT '第2个滤芯的配置总流量值（固定）',
  `invalid_date_filter2` datetime DEFAULT NULL COMMENT '第2个滤芯的失效时间（固定）',
  `report_hour_filter_count2` int(11) DEFAULT NULL COMMENT '第2个滤芯上报剩余时长',
  `report_flow_filter_count2` double DEFAULT NULL COMMENT '第2个滤芯上报剩余流量',
  `config_total_flow_filter_count3` double DEFAULT NULL COMMENT '第3个滤芯的配置总流量值（固定）',
  `invalid_date_filter3` datetime DEFAULT NULL COMMENT '第3个滤芯的失效时间（固定）',
  `report_hour_filter_count3` int(11) DEFAULT NULL COMMENT '第3个滤芯上报剩余时长',
  `report_flow_filter_count3` double DEFAULT NULL COMMENT '第3个滤芯上报剩余流量',
  `config_total_flow_filter_count4` double DEFAULT NULL COMMENT '第4个滤芯的配置总流量值（固定）',
  `invalid_date_filter4` datetime DEFAULT NULL COMMENT '第4个滤芯的失效时间（固定）',
  `report_hour_filter_count4` int(11) DEFAULT NULL COMMENT '第4个滤芯上报剩余时长',
  `report_flow_filter_count4` double DEFAULT NULL COMMENT '第4个滤芯上报剩余流量',
  `config_total_flow_filter_count5` double DEFAULT NULL COMMENT '第5个滤芯的配置总流量值（固定）',
  `invalid_date_filter5` datetime DEFAULT NULL COMMENT '第5个滤芯的失效时间（固定）',
  `report_hour_filter_count5` int(11) DEFAULT NULL COMMENT '第5个滤芯上报剩余时长',
  `report_flow_filter_count5` double DEFAULT NULL COMMENT '第5个滤芯上报剩余流量',
  `config_total_flow_filter_count6` double DEFAULT NULL COMMENT '第6个滤芯的配置总流量值（固定）',
  `invalid_date_filter6` datetime DEFAULT NULL COMMENT '第6个滤芯的失效时间（固定）',
  `report_hour_filter_count6` int(11) DEFAULT NULL COMMENT '第6个滤芯上报剩余时长',
  `report_flow_filter_count6` double DEFAULT NULL COMMENT '第6个滤芯上报剩余流量',
  `config_total_flow_filter_count7` double DEFAULT NULL COMMENT '第7个滤芯的配置总流量值（固定）',
  `invalid_date_filter7` datetime DEFAULT NULL COMMENT '第7个滤芯的失效时间（固定）',
  `report_hour_filter_count7` int(11) DEFAULT NULL COMMENT '第7个滤芯上报剩余时长',
  `report_flow_filter_count7` double DEFAULT NULL COMMENT '第7个滤芯上报剩余流量',
  `config_total_flow_filter_count8` double DEFAULT NULL COMMENT '第8个滤芯的配置总流量值（固定）',
  `invalid_date_filter8` datetime DEFAULT NULL COMMENT '第8个滤芯的失效时间（固定）',
  `report_hour_filter_count8` int(11) DEFAULT NULL COMMENT '第8个滤芯上报剩余时长',
  `report_flow_filter_count8` double DEFAULT NULL COMMENT '第8个滤芯上报剩余流量',
  `config_total_flow_filter_count9` double DEFAULT NULL COMMENT '第9个滤芯的配置总流量值（固定）',
  `invalid_date_filter9` datetime DEFAULT NULL COMMENT '第9个滤芯的失效时间（固定）',
  `report_hour_filter_count9` int(11) DEFAULT NULL COMMENT '第9个滤芯上报剩余时长',
  `report_flow_filter_count9` double DEFAULT NULL COMMENT '第9个滤芯上报剩余流量',
  `config_total_flow_filter_count10` double DEFAULT NULL COMMENT '第10个滤芯的配置总流量值（固定）',
  `invalid_date_filter10` datetime DEFAULT NULL COMMENT '第10个滤芯的失效时间（固定）',
  `report_hour_filter_count10` int(11) DEFAULT NULL COMMENT '第10个滤芯上报剩余时长',
  `report_flow_filter_count10` double DEFAULT NULL COMMENT '第10个滤芯上报剩余流量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

###设备故障信息表
DROP TABLE IF EXISTS `t_device_error`;
CREATE TABLE `t_device_error` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sn_code` varchar(50) DEFAULT NULL COMMENT '整机码/物流码',
  `device_id` varchar(50) NOT NULL COMMENT '设备id',
  `last_post_time` datetime DEFAULT NULL COMMENT '上一次上报时间',
  `fault_code` int(10) DEFAULT NULL COMMENT '故障代码',
  `is_deal` char(1) DEFAULT NULL COMMENT '设备状态 Y-恢复 N-故障',
  `fault_content` varchar(500) DEFAULT NULL COMMENT '故障说明',
  `user_id` varchar(50) DEFAULT NULL COMMENT '用户id',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` varchar(50) DEFAULT 'system' COMMENT '创建人',
  `updated_time` datetime DEFAULT NULL COMMENT '修改时间',
  `updated_by` varchar(50) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8