package com.pingan.angel.admin.api.dto.respond;

import lombok.Data;

import java.util.Date;

/**
 * 
 * @author zhangquan
 * @Text 设备主动上报数据指令
 */
@Data
public class EquipmentActive extends HeadNews{

	/**
	 * d1:原水水温
	 */
	private double primaryTemperature ;
	
	/**
	 * d2:出水水温
	 */
	private double outTemperature ;
	 
	/**
	 * d3:出水TDS值 
	 */
	private double outTDS ;
	
	/**
	 * d4:原水TDS值
	 */
	private double primaryTDS ;
	
	/**
	 * d5:纯水总量
	 */
	private double pureWaterTotal ;
	
	/**
	 * d6:总水量
	 */
	private double waterTotal ;

	/**
	 * 上报时间
	 */
	private Date time ;
	
	/**
	 * d13:滤芯1剩余寿命 
	 */
	private double filterOneResidualLife ;
	
	/**
	 * d14:滤芯1剩余流量 
	 */
	private double filterOneResidualFlow ;
	
	/**
	 * d15:滤芯2剩余寿命 
	 */
	private double filterTwoResidualLife ;
	
	/**
	 * d16:滤芯2剩余流量 
	 */
	private double filterTwoResidualFlow ;

	/**
	 * d17:滤芯3剩余寿命 
	 */
	private double filterThreeResidualLife ;
	
	/**
	 * d18:滤芯3剩余流量 
	 */
	private double filterThreeResidualFlow ;
	
	/**
	 * d19:滤芯4剩余寿命  
	 */
	private double filterFourResidualLife ;
	
	/**
	 * d20:滤芯4剩余流量
	 */
	private double filterFourResidualFlow ;
	
	/**
	 * d21:滤芯5剩余寿命 
	 */
	private double filterFiveResidualLife ;
	
	/**
	 * d22:滤芯5剩余流量 
	 */
	private double filterFiveResidualFlow ;
}
