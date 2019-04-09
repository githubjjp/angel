###设备故障信息表
DROP TABLE IF EXISTS `t_device_error`;
CREATE TABLE `t_device_error` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sn_code` varchar(50) DEFAULT NULL COMMENT '整机码/物流码',
  `device_id` varchar(50) NOT NULL COMMENT '设备id',
  `last_post_time` datetime DEFAULT NULL COMMENT '上一次上报时间',
  `fault_code` int(10) DEFAULT NULL COMMENT '故障代码',
  `protect_code` int(10) DEFAULT NULL COMMENT '保护代码',
  `is_deal` char(1) DEFAULT NULL COMMENT '设备状态 Y-恢复 N-故障',
  `fault_content` varchar(500) DEFAULT NULL COMMENT '故障说明',
  `protect_content` varchar(500) DEFAULT NULL COMMENT '保护代码说明',
  `user_id` varchar(50) DEFAULT NULL COMMENT '用户id',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` varchar(50) DEFAULT 'system' COMMENT '创建人',
  `updated_time` datetime DEFAULT NULL COMMENT '修改时间',
  `updated_by` varchar(50) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;