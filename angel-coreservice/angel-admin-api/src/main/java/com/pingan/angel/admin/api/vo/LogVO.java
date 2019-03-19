package com.pingan.angel.admin.api.vo;

import com.pingan.angel.admin.api.entity.SysLog;
import lombok.Data;

import java.io.Serializable;

/**
 */
@Data
public class LogVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private SysLog sysLog;
	private String username;
}
