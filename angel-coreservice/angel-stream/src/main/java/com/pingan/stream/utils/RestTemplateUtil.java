package com.pingan.stream.utils;

import org.jboss.logging.Logger;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.*;

/**
 * 请求外系统
 * 
 * @author 陈浩
 * @date 2019.03.25
 *
 */
public class RestTemplateUtil {
	private static Logger logger = Logger.getLogger(RestTemplateUtil.class);

	public static Properties prop;

	static {
		prop = PropertiesUtils.getProperties("classpath:application.yml");
	}

	/**
	 * 请求
	 * @param paramMap
	 * @param openPath
	 * @param path
	 * @param method
	 * @param flag
	 * @return
	 */
	private static String invoke(Map<String,Object> paramMap, String openPath, String path, String method, boolean flag) {
		String params=changeToMap(paramMap);
		RestTemplate template = new RestTemplate();
		HttpHeaders header = new HttpHeaders();
		header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		header.setAccept(Arrays.asList(new MediaType[] { new MediaType("application", "json", Charset.forName("UTF-8")) }));
		HttpEntity<String> requestEntity = new HttpEntity<String>(params, header);
		StringBuffer url = new StringBuffer();
		url.append(openPath);
		url.append(path);

		ResponseEntity<String> responseEntity = null;
		String result = null;

		if (method.equals("POST")) { // post请求
			logger.info("url===" + url.toString() + "   param===" + params);
			responseEntity = template.exchange(url.toString(), HttpMethod.POST, requestEntity, String.class);
			result = responseEntity.getBody();
		} else if (method.equals("GET")) { // get请求
			if (flag) {
				logger.info("url===" + url.toString() + "   param===" + params);
				result = template.getForObject(url.toString(), String.class, params);
			} else {
				url.append("?").append(params);
				logger.info("url===" + url.toString() + "   param===" + params);
				responseEntity = template.exchange(url.toString(), HttpMethod.GET, requestEntity, String.class);
				result = responseEntity.getBody();
			}
		} else if (method.equals("PUT")) {
			logger.info("url===" + url.toString() + "   param===" + params);
			responseEntity = template.exchange(url.toString(), HttpMethod.PUT, requestEntity, String.class);
			result = responseEntity.getBody();
		} else if (method.equals("DELETE")) {
			url.append("?").append(params);
			logger.info("url===" + url.toString() + "   param===" + params);
			responseEntity = template.exchange(url.toString(), HttpMethod.GET, requestEntity, String.class);
			result = responseEntity.getBody();
		}
		logger.info("调用外系统返回数据结果:::" + result);
		return result;
	}

	/**
	 * 入参转字符串
	 * @param params
	 * @return
	 */
	public static String changeToMap(Map<String,Object> params){
		StringBuffer sb=new StringBuffer();
		Set<String> keys=params.keySet();
		for(String key:keys){
			sb.append(key).append("=").append(params.get(key)).append("&");
		}
		String paramResult=sb.toString();
		paramResult.substring(0,paramResult.lastIndexOf("&"));
		return paramResult;
	}





}
