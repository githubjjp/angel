package com.pingan.stream.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
/**
 * 配置文件操作类
 * @author lenovo
 * @date   2019.03.25
 */
public class PropertiesUtils {
	private static Map<String,Properties> propMap=new HashMap<String,Properties>();

	/**
	 * 根据文件名在家配置文件
	 * @param fileName
	 * @return
	 */
	public static Properties getProperties(String fileName) {
		Properties prop=propMap.get(fileName);
		if(prop==null) {
			prop=loadProperties(fileName);
			propMap.put(fileName, prop);//存入缓存中
		}
		return prop;
	}

	/**
	 * 从本地磁盘加载prop
	 * @param fileName
	 * @return
	 */
	private static Properties loadProperties(String fileName) {
		InputStream is=null;
		Properties prop=null;
		try {
			Resource resource=new ClassPathResource(fileName);
		    is=resource.getInputStream();
		    prop=new Properties();
		    prop.load(is);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(is!=null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return prop;
	}
	
	/**
	 * 从配置文件中读取参数值
	 * @param fileName
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static String getValue(String fileName,String key,String defaultValue) {
	     Properties prop=getProperties(fileName);
	     if(prop!=null) {
	    	 if(StringUtils.isNotEmpty(defaultValue)) {
	    		 return prop.getProperty(key, defaultValue);
	    	 }
	    	 return prop.getProperty(key);
	     }
	     return null;
	}
    
    
}
