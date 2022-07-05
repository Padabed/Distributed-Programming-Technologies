package eu.glowacki.tpo.jms.queue;

import javax.jms.*;
import javax.naming.Context;

import eu.glowacki.tpo.jms.Common;
import eu.glowacki.tpo.jms.messages.*;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class QueueMessageSender implements Runnable{
	private static int counter=0;
	private static Random rnd = new Random();
	private String name;
	private int chooseRequestType;
	public QueueMessageSender(String x)
	{
		name=x;
	}

	//blockingqueue costam that only one receiver can get that message
	@Override
	public void run() {
		Connection connection = null;
		try {
			Context context = Common.getContext();
			connection = Common.createAndStartQueueConnection(context);
			Session session = Common.createSession(connection);
			MessageProducer messageProducer = Common.createMessageProducer(context, session, true);
			chooseRequestType = rnd.nextInt();
			System.out.println(Thread.currentThread().getName() + "(START) MESSAGE SENDER HAS STARTED "+counter++);
			//1. Requestor prepares the body of request message.
			if (chooseRequestType%2==0)
			{
				ArithmeticRequest arithmeticRequest = new ArithmeticRequest(BigInteger.valueOf(rnd.nextInt()),BigInteger.valueOf(rnd.nextInt()),rnd.nextInt(5));
				Message message = Common.createMessage(session, arithmeticRequest);
				messageProducer.send(message);
				System.out.println("(SENDER) MESSAGE SENDER HAS SENT THE MESSAGE: Arithmetical request "+arithmeticRequest.COMPONENT1+" "+arithmeticRequest.COMPONENT2+" type "+arithmeticRequest.type);
			}
			/*else{
				RandomRequest randomRequest = new RandomRequest();
				Message message = Common.createMessage(session, randomRequest);
				messageProducer.send(message);
				System.out.println("(SENDER) MESSAGE PRODUCER HAS SENT THE MESSAGE: Radnom request");
			}*/
			//AWAIT
				System.out.println("(SENDER) MESSAGE CONSUMER INITIALIZED");
				//	processResponse((ObjectMessage) response);


		} catch (Exception ex) {
			ex.printStackTrace();
			System.exit(1);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (JMSException ex) {
					System.err.println(ex);
				}
			}
		}
	}
	private String processResponse(ObjectMessage message)
	{
		try {
			Serializable content = message.getObject();
			if (content instanceof RandomResponse) {
				RandomResponse randomResponse = (RandomResponse) content;
				return "(SENDER) Response is "+randomResponse.result;

			} else if (content instanceof ArithmeticResponse) {
				ArithmeticResponse arithmeticResponse = (ArithmeticResponse) content;
				return "(SENDER) Reponse is "+arithmeticResponse.RESULT;
			}
		} catch (JMSException exception) {
			exception.printStackTrace();
		}
		return null;
	}
}