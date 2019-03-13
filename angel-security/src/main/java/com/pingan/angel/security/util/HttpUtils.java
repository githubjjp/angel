//package com.pingan.angel.security.util;
//
//import java.io.IOException;
//
//import javax.servlet.http.HttpServletResponse;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.pingan.angel.admin.util.R;
//
///**
// * @author yuit
// * @date 2018/11/5 16:34
// *
// */
//public class HttpUtils {
//
//    public static void writerError(R<Object> bs, HttpServletResponse response) throws IOException {
//        response.setContentType("application/json,charset=utf-8");
//        response.setStatus(bs.getCode());
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.writeValue(response.getOutputStream(),bs);
//    }
//
//}
