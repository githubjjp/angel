package com.pingan.stream.Service.impl;

import com.pingan.stream.Service.BusinessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BusinessServiceImpl implements BusinessService {
    private static Logger logger= LoggerFactory.getLogger(BusinessServiceImpl.class);
    @Override
    public void process(String message) {
        try{

        }catch(Exception e){
            logger.error("业务处理异常",e);
        }
    }
}
