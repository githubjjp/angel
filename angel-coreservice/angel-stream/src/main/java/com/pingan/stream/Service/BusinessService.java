package com.pingan.stream.Service;

import java.util.Map;

public interface BusinessService {
    /**
     * 具体业务处理
     * @param message
     */
    public void process(String message);

    /**
     * 获取连接字符串
     * @param deviceId
     * @return
     */
    public Map<String,Object> getConnectionStr(String deviceId);

    /**
     * 是否上下线验证
     * @param deviceId
     * @return
     */
    public Map<String,Object> isOnline(String deviceId);



}
