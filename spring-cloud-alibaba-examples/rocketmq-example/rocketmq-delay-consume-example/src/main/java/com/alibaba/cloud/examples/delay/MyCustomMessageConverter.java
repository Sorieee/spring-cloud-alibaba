package com.alibaba.cloud.examples.delay;

import com.alibaba.cloud.stream.binder.rocketmq.convert.RocketMQMessageConverter;
import org.springframework.messaging.Message;
import org.springframework.messaging.converter.AbstractMessageConverter;
import org.springframework.messaging.converter.MessageConverter;

import javax.activation.MimeType;
import java.util.List;

public class MyCustomMessageConverter extends AbstractMessageConverter {
    private List<MessageConverter> messageConverters;
    public MyCustomMessageConverter(List<MessageConverter> messageConverters) {
        super();
        this.messageConverters = messageConverters;
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    protected Object convertFromInternal(Message<?> message, Class<?> targetClass, Object conversionHint) {
        Object payload = null;
        for (MessageConverter converter : messageConverters) {
            try {
                payload = converter.fromMessage(message, targetClass);

            } catch (Exception ignore) {
            }
            if (payload != null) {
                return payload;
            }
        }
        return payload;
    }

}