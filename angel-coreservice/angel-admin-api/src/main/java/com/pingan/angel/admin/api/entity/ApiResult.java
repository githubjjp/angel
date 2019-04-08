package com.pingan.angel.admin.api.entity;

import java.util.HashMap;
import java.util.Map;

public class ApiResult {

    public static Map<String,Object> success(Object data){
        Map<String,Object> map = new HashMap<String, Object>();
        if(data == null){
            data = new HashMap();
        }
        map.put("data",data);
        map.put("code", ResultCode.SUCCESS.getCode());
        map.put("msg",ResultCode.SUCCESS.getMsg());
        return map;
    }

    public static Map<String,Object> result(ResultCode code , Object data){
        Map<String,Object> map = new HashMap<String, Object>();
        if(data==null){
            data = new HashMap<>();
        }
        map.put("data",data);
        map.put("code", code.getCode());
        map.put("msg",code.getMsg());
        return map;
    }

    public static Map<String,Object> error(ResultCode code){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("data",new HashMap());
        map.put("code", code.getCode());
        map.put("msg",code.getMsg());
        return map;
    }

    public static Map<String,Object> error(String msg){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("data",new HashMap());
        map.put("code", ResultCode.ERROR.getCode());
        map.put("msg",msg);
        return map;
    }

    public static boolean isSuccess(Map<String,Object> map){
        return ResultCode.SUCCESS.getCode().equals(map.get("code"));
    }
}
