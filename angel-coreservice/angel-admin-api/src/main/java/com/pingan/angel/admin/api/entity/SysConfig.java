package com.pingan.angel.admin.api.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("sys_config")
public class SysConfig {
	
	@TableId(value = "variable", type = IdType.UUID)
	private String variable;

	private String value;
	
	private LocalDateTime setTime;
	
	
	private String setBy;

}

