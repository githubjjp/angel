package com.pingan.angel.admin.api.constants;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

import com.pingan.angel.admin.constants.CodeConstants;

/**
 * 
 * 	@author zhangquan
 *	@Date 2019-3-18 16:51:28
 *  @Text 加密解密工具
 */
public class DataEncryption {
	
	/**
	 * MD5方法
	 * @param text 明文
	 * @param key 密钥
	 * @return 密文
	 * @throws Exception
	 */
	public static String md5(String text, String key){
		// 加密后的字符串
		String encodeStr = DigestUtils.md5Hex(text + key);
		
		return encodeStr;
	}

	/**
	 * MD5验证方法
	 * @param text 明文
	 * @param key 密钥
	 * @param md5  密文
	 * @return true/false
	 * @throws Exception
	 */
	public static boolean verify(String text, String key, String md5)
			throws Exception {
		// 根据传入的密钥进行验证
		String md5Text = md5(text, key);
		if (md5Text.equalsIgnoreCase(md5)) {
			System.out.println("MD5验证通过");
			return true;
		}

		return false;
	}
	
	/**
	 * 拼接参数
	 * @param splitKey
	 * @param map
	 * @param parameterKey
	 * @return
	 */
	public static String splitParameter( String splitKey, Map<String,Object> map, String[] parameterKey){
		
		StringBuffer strB = new StringBuffer() ;
		String key = null ;
		boolean isBlank = false ;
		for (int i = 0; i < parameterKey.length; i++) {
			key = parameterKey[i] ;
			if( StringUtils.isBlank(key) || null==map.get(key) || StringUtils.isBlank(map.get(key).toString()) ){
				continue ;
			}
			if( isBlank ){
				strB.append( splitKey ) ;
			}
			strB.append(key) ;
			strB.append("=") ;
			strB.append( map.get(key) ) ;
			isBlank = true ;
		}
		
		return strB.toString() ;
	}
	
	
	public static void main(String[] args) {
		Map<String,Object> map = new HashMap<String ,Object>() ;
		map.put("sn", "123456789") ;
		String splitStr = splitParameter( CodeConstants.LOT_SPLIT_KEY , map, APIConstant.ACTIVE) ;
		System.out.println(splitStr);
		
		System.out.println( md5( splitStr , APIConstant.MD5_KEY ) );
	}
	
	
	
}
