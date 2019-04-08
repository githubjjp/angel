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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;