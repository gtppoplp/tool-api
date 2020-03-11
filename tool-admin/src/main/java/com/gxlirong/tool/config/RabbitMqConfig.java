package com.gxlirong.tool.config;

import com.gxlirong.tool.enums.QueueEnum;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 消息队列配置
 *
 * @author lirong
 */
@Configuration
public class RabbitMqConfig {

    /**
     * 我的世界[汉化消息]消费队列所绑定的交换机
     *
     * @author lirong
     */
    @Bean
    public DirectExchange minecraftChineseDirect() {
        return (DirectExchange) ExchangeBuilder
                .directExchange(QueueEnum.QUEUE_MINECRAFT_CHINESE.getExchange())
                .durable(true)
                .build();
    }

    /**
     * 我的世界[汉化消息]消费队列
     *
     * @author lirong
     */
    @Bean
    public Queue minecraftChineseQueue() {
        return new Queue(QueueEnum.QUEUE_MINECRAFT_CHINESE.getName());
    }

    /**
     * 将我的世界[汉化消息]队列绑定到交换机
     *
     * @author lirong
     */
    @Bean
    public Binding minecraftChineseBinding(DirectExchange minecraftChineseDirect, Queue minecraftChineseQueue) {
        return BindingBuilder
                .bind(minecraftChineseQueue)
                .to(minecraftChineseDirect)
                .with(QueueEnum.QUEUE_MINECRAFT_CHINESE.getRouteKey());
    }


    /**
     * 我的世界[保存字段信息消息]消费队列所绑定的交换机
     *
     * @author lirong
     */
    @Bean
    public DirectExchange minecraftLangCreateDirect() {
        return (DirectExchange) ExchangeBuilder
                .directExchange(QueueEnum.QUEUE_MINECRAFT_LANG_CREATE.getExchange())
                .durable(true)
                .build();
    }

    /**
     * 我的世界[保存字段信息消息]消费队列
     *
     * @author lirong
     */
    @Bean
    public Queue minecraftLangCreateQueue() {
        return new Queue(QueueEnum.QUEUE_MINECRAFT_LANG_CREATE.getName());
    }

    /**
     * 将我的世界[保存字段信息消息]队列绑定到交换机
     *
     * @author lirong
     */
    @Bean
    public Binding minecraftLangCreateBinding(DirectExchange minecraftLangCreateDirect, Queue minecraftLangCreateQueue) {
        return BindingBuilder
                .bind(minecraftLangCreateQueue)
                .to(minecraftLangCreateDirect)
                .with(QueueEnum.QUEUE_MINECRAFT_LANG_CREATE.getRouteKey());
    }
}
