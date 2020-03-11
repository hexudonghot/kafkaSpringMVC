package com.unionpay.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.MessageListener;

/**
 * 
 * @Title: KafkaConsumerListener
 * @Description:kafka消息中间件，消费端监听，发现、拉取 消息
 * @Author: zhaotf
 * @Since:2017年2月23日 下午4:54:13
 * @Version:1.0
 */
public class KafkaConsumer1Listener implements MessageListener<String, String> {

	/**
	 * 监听器自动执行该方法 消费消息 自动提交offset 执行业务代码 （high level api
	 * 不提供offset管理，不能指定offset进行消费）
	 */
	@Override
	public void onMessage(ConsumerRecord<String, String> record) {
		System.out.println("kafkaConsumer 1 开始消费，键:" + record.key());
		System.out.println("kafkaConsumer 1 开始消费，内容:" + record.value());
		System.out.println("kafkaConsumer 1 开始消费，topic:" + record.topic());
		System.out.println("kafkaConsumer 1 开始消费，offset:" + record.offset());
		System.out.println("kafkaConsumer 1 开始消费，partition:" + record.partition());
	}

}
