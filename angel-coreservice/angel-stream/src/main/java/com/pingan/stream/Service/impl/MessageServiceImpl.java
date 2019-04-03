package com.pingan.stream.Service.impl;

import com.pingan.stream.Service.BusinessService;
import com.pingan.stream.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private BusinessService businessService;

    @Override
    public void receive(String mqStr) {
        businessService.process(mqStr);
    }
}
