package com.pingan.stream.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.jboss.logging.Logger;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

/**
 * json操作类
 * 
 * @author 陈浩
 *
 */
public class JSONUtils {
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(JSONUtils.class);
    
	private static ObjectMapper mapper=new ObjectMapper()
			.setSerializationInclusion(Include.NON_NULL)
			.enable(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS)
			.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
			.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
			.enable(MapperFeature.USE_ANNOTATIONS);
	
	
	/**
	 * 从json字符串中获取key对应的value
	 * 
	 * @param result
	 *            json字符串
	 * @param key
	 * @return
	 */
	public static String getJSONStr(String result, String key) {
		JSONObject json = JSONObject.parseObject(result);
		return json.getString(key);
	}

	/**
	 * 从json字符串中根据key获取对应的集合
	 * 
	 * @param result
	 *            json字符串
	 * @param key
	 * @return
	 */
	public static List<JSONObject> getJSONList(String result, String key) {
		JSONObject json = JSONObject.parseObject(result);
		JSONArray array = json.getJSONArray(key);
		if (array == null) {
			return null;
		}
		Iterator<?> it = array.iterator();
		List<JSONObject> list = new ArrayList<JSONObject>();
		while (it.hasNext()) {
			JSONObject one = (JSONObject) it.next();
			list.add(one);
		}
		return list;
	}

	/**
	 * 从json字符串中获取json对象
	 * 
	 * @param result
	 * @param key
	 * @return
	 */
	public static JSONObject getJSONObject(String result, String key) {
		JSONObject json = JSONObject.parseObject(result);
		boolean constain = json.containsKey(key);
		if (!constain) {
			return null;
		}
		return json.getJSONObject(key);
	}

	/**
	 * 将json字符串转换为map
	 * 
	 * @param result
	 * @return
	 */
	public static Map<String, Object> jsonToMap(String result) {
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject data = JSONObject.parseObject(result);
		for (Entry<String, Object> entry : data.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			if (value instanceof String || value instanceof Double || value instanceof Integer || value instanceof Long || value instanceof Boolean || value instanceof Short || value instanceof Byte) {
				map.put(key, value);
			} else if (value instanceof JSONObject) {
				Map<String, Object> map2 = jsonToMap(value.toString());
				map.put(key, map2);
			} else if (value instanceof JSONArray) {
				Iterator<?> it = ((JSONArray) value).iterator();
				if (!it.hasNext()) {
					List<Object> list = new ArrayList<Object>();
					map.put(key, list);
				} else {
					while (it.hasNext()) {
						Map<String, Object> map3 = jsonToMap((((JSONObject) it.next()).toJSONString()));
						map.put(key, map3);
					}
				}
			} else {
				map.put(key, value);
			}
		}

		return map;
	}

	/**
	 * 将map转换为json数据
	 * 
	 * @param map
	 * @return
	 */
	public static JSONObject mapToJson(Map<String, Object> map) {
		String jsonStr = JSONObject.toJSONString(map, SerializerFeature.WriteMapNullValue);
		return JSONObject.parseObject(jsonStr);
	}
	
	
	/**
	 * 转换json字符串
	 * @param bean
	 * @return
	 */
	public static <T> String toJSON(T bean){
		try{
			return mapper.writeValueAsString(bean);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 泛型转换实体类
	 * @param content
	 * @param tr
	 * @return
	 */
	public static <T> T toObject(String content,TypeReference<T> tr){
		try{
			return mapper.readValue(content, tr);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 非泛型转换实体类
	 * @param content
	 * @param clazz
	 * @return
	 */
	public static <T> T toObejct(String content,Class<T> clazz){
		try{
			return mapper.readValue(content, clazz);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取对象属性之外的动态字段
	 * @param origialMap
	 * @return
	 */
	public static Map<String,Object> getDynamicMap(Map<String,Object> origialMap){
		Set<String> keys=origialMap.keySet();
		logger.info("keys"+ JSON.toJSONString(keys));
		LinkedHashMap map=new LinkedHashMap();
		for(String key:keys){
			if(key.indexOf("d")==0 && key.length() <=3){  //只需要d1-dn这些字段信息
				map.put(key,origialMap.get(key));
			}
		}
		return map;
	}
	



}
