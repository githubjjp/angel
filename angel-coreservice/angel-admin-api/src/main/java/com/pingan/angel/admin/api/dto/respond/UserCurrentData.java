package com.pingan.angel.admin.api.dto.respond;

import lombok.Data;

/**
 * 
 * @author zhangquan
 * @text 用户实时获取数据指令
 */
@Data
public class UserCurrentData extends HeadNews {
	
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
	 * d5:脱盐率 (0-100)
	 */
	private int desalinationRate ;
	
	/**
	 * d6:滤芯1剩余寿命 
	 */
	private double filterOneResidualLife ;
	
	/**
	 * d7:滤芯1剩余流量 
	 */
	private double filterOneResidualFlow ;
	
	/**
	 * d8:滤芯2剩余寿命 
	 */
	private double filterTwoResidualLife ;
	
	/**
	 * d9:滤芯2剩余流量 
	 */
	private double filterTwoResidualFlow ;

	/**
	 * d10:滤芯3剩余寿命 
	 */
	private double filterThreeResidualLife ;
	
	/**
	 * d11:滤芯3剩余流量 
	 */
	private double filterThreeResidualFlow ;
	
	/**
	 * d12:滤芯4剩余寿命  
	 */
	private double filterFourResidualLife ;
	
	/**
	 * d13:滤芯4剩余流量
	 */
	private double filterFourResidualFlow ;
	
	/**
	 * d14:滤芯5剩余寿命 
	 */
	private double filterFiveResidualLife ;
	
	/**
	 * d15:滤芯5剩余流量 
	 */
	private double filterFiveResidualFlow ;
	
	/**
	 * d16:纯水总量
	 */
	private double pureWaterTotal ;
	
	/**
	 * d17:总水量
	 */
	private double waterTotal ;
}
