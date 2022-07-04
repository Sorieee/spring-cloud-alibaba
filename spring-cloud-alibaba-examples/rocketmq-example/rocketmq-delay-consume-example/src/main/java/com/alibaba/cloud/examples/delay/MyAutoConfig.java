package com.alibaba.cloud.examples.delay;

import com.alibaba.cloud.stream.binder.rocketmq.convert.RocketMQMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.support.converter.ConfigurableCompositeMessageConverter;
import org.springframework.messaging.converter.MessageConverter;

@Configuration
public class MyAutoConfig {

    @Bean
    public MessageConverter rocketMqCustomMessageConverter() {
//        return new RocketMQMessageConverter().getMessageConverter();
        return new MyCustomMessageConverter(new RocketMQMessageConverter().getMessageConverter().getConverters());
    }
}
