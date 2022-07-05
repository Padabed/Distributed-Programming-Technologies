package eu.glowacki.tpo.jms;

import eu.glowacki.tpo.jms.messages.ArithmeticRequest;
import eu.glowacki.tpo.jms.messages.IRequest;
import eu.glowacki.tpo.jms.messages.IResponse;
import eu.glowacki.tpo.jms.messages.RandomRequest;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.math.BigInteger;
import java.util.Random;

public class Common {

	private static final String QUEUE_NAME = "Queue";
	private static final String TOPIC_NAME = "Topic";
	private static final String TOPIC_SUBSCRIBER_ID = "TopicSubscriber";
	private static final String CONNECTION_FACTORY = "ConnectionFactory";

	public static Context getContext() throws NamingException {
		Context context = new InitialContext();
		return context;
	}

	public static Connection createAndStartQueueConnection(Context context) throws NamingException, JMSException {
		Connection connection = createConnection(context);
		connection.start();
		return connection;
	}

	public static Connection createAndStartTopicConnection(Context context) throws NamingException, JMSException {
		Connection connection = createConnection(context);
		connection.setClientID(TOPIC_SUBSCRIBER_ID);
		connection.start();
		return connection;
	}

	public static Session createSession(Connection connection) throws JMSException {
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		return session;
	}
	public static MessageProducer createMessageProducer(Context context, Session session, boolean queue)
			throws NamingException, JMSException {
		Destination destination = getDestination(context, queue);
		MessageProducer messageProducer = session.createProducer(destination);
		return messageProducer;
	}

	public static MessageConsumer createQueueMessageConsumer(Context context, Session session)
			throws NamingException, JMSException {
		Destination destination = getDestination(context, true);
		return session.createConsumer(destination);
	}

	//creating request
	public static Message createMessage(Session session, RandomRequest request) throws JMSException {
		ObjectMessage objectMessage = session.createObjectMessage();
		objectMessage.setObject(request);
		return objectMessage;
	}
	public static Message createMessage(Session session, ArithmeticRequest request) throws JMSException {
		ObjectMessage objectMessage = session.createObjectMessage();
		objectMessage.setObject(request);
		return objectMessage;
	}

	public static Message createMessage(Session session, IResponse response) throws JMSException {
		ObjectMessage objectMessage = session.createObjectMessage();
		objectMessage.setObject(response);
		return objectMessage;
	}
	private static Connection createConnection(Context context) throws NamingException, JMSException {
		//ConnectionFactory factory = connectionFactory(context);
		//I needed to change that due to security problems setTrustAllPackages works for this
		ActiveMQConnectionFactory factory1 = new ActiveMQConnectionFactory("tcp://localhost:61616");
		factory1.setTrustAllPackages(true);
		Connection connection = factory1.createConnection();
		return connection;
	}
	
	private static ConnectionFactory connectionFactory(Context context) throws NamingException {
		return  (ConnectionFactory) context.lookup(Common.CONNECTION_FACTORY);
	}

	private static Destination getDestination(Context context, boolean queue) throws NamingException {
		Destination destination = queue //
				? Common.getQueue(context) //
						: Common.getTopic(context);
		return destination;
	}

	/**
	 * get destination (queue)
	 */
	private static Queue getQueue(Context context) throws NamingException {
		Queue queue = (Queue) context.lookup(Common.QUEUE_NAME);
		return queue;
	}

	/**
	 * get destination (topic)
	 */
	private static Topic getTopic(Context context) throws NamingException {
		Topic topic = (Topic) context.lookup(Common.TOPIC_NAME);
		return topic;
	}
}