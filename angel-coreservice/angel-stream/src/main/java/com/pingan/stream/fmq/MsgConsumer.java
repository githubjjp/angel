package com.pingan.stream.fmq;

import com.paic.fincloud.client.common.FCMessageListener;
import com.paic.fincloud.client.dto.FCMessage;
import com.paic.fincloud.client.util.FCConsumeStatus;
import com.pingan.stream.Service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 *  消费者监听消息实现
 */
@Component
public class MsgConsumer implements FCMessageListener {
    private static Logger logger= LoggerFactory.getLogger(MsgConsumer.class);

    @Autowired
    private MessageService messageService;

    @Override
    public FCConsumeStatus pushMessage(List<FCMessage> list)  {
        if(list==null||list.isEmpty()){
            logger.info("【consumer】接受到的消息为空，不处理，直接返回成功");
            return FCConsumeStatus.CONSUME_OK;
        }
        logger.info("消息数::"+list.size());
        for(FCMessage fcm:list){
            try {
                String msgBody = new String(fcm.getConent(),"UTF-8");
                String msgId=fcm.getMsgId();
                logger.info("接受到的消息::"+msgId+"   消息内容::"+msgBody);
                messageService.receive(msgBody);
            } catch (UnsupportedEncodingException e) {
                logger.error("监听消息异常",e);
            }
        }
        return FCConsumeStatus.CONSUME_OK;
    }
}
