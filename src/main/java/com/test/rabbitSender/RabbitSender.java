package com.test.rabbitSender;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Hello world!
 *
 */
public class RabbitSender 
{
	public static String EXCHANGE = "teste";
	
    public static void main( String[] args ) throws IOException
    {
        System.out.println( "Hello World!" );
        
        //vars
        String messageToSend = "hello";
        
        
        //create connection to rabbit
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        
        //create exchange
        channel.exchangeDeclare(EXCHANGE, "fanout");
        
        //create queue       
        channel.basicPublish(EXCHANGE, "", null, messageToSend.getBytes());
        System.out.println("[X-out] sent message");
        
        
        //close connection
        channel.close();
        connection.close();        
        
        System.out.println( "Goodbye World!" );
    }
}
