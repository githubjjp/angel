package com.pingan.angel.admin.api.dto;

import java.util.List;

import lombok.Data;

/**
 * 
 * @author zhangquan
 * @Date 2019-3-20 16:29:42
 * @Text 设备实时信息
 *
 */
@Data
public class Status {

	/**
	 * 设备的sn号
	 */
	private String sn ;
	
	/**
	 * 进水tds
	 */
	private int tdsIn ;
	
	/**
	 * 出水tds
	 */
	private int tdsOut ;
	
	/**
	 * 进水水温
	 */
	private int tempIn ;
	
	/**
	 * 出水水温
	 */
	private int tempOut ;
	
	/**
	 * 滤芯信息
	 */
	private List<FilterInfo> filterInfos ;
}
