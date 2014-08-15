package com.test.rabbitRecv;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;
import com.smurr.RabbitRecvThreadSafe.consumer.ConsumerNonBlocking;

/**
 * Hello world!
 * 
 */
public class RabbitClient {
	public static String	EXCHANGE	= "teste";

	public static void main(String[] args) throws IOException, ShutdownSignalException, ConsumerCancelledException, InterruptedException {
		System.out.println("Hello World!");

		// vars
		String messageToSend = "hello me!";

		// create connection to rabbit

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		// create exchagne
		
		
		channel.exchangeDeclare(EXCHANGE, "fanout");

		// create/bind queue
		String queueName = channel.queueDeclare().getQueue();
		channel.queueBind(queueName, EXCHANGE, "");		

		ConsumerNonBlocking consumer = new ConsumerNonBlocking(channel);
		String message = channel.basicConsume(queueName, true, consumer);
		
		System.out.println("I finished");
		

	}
}
