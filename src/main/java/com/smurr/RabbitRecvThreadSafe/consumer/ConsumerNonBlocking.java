package com.smurr.RabbitRecvThreadSafe.consumer;

import java.io.IOException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class ConsumerNonBlocking extends DefaultConsumer {
	
	Channel channel;

	public ConsumerNonBlocking(Channel channel) {
		super(channel);
		this.channel = channel;
	}

	@Override
	public void handleDelivery(String consumerTag, Envelope envelope,
			AMQP.BasicProperties properties, byte[] body) throws IOException {
		
		System.out.println(new String(body));
		
		channel.basicAck(envelope.getDeliveryTag(), false);
		
	}

}
