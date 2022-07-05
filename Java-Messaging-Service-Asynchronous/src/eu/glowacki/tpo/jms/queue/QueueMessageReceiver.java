package eu.glowacki.tpo.jms.queue;

import javax.jms.*;
import javax.naming.Context;

import eu.glowacki.tpo.jms.Common;
import eu.glowacki.tpo.jms.listeners.TprMessageListener;
import eu.glowacki.tpo.jms.messages.*;
import org.apache.activemq.command.ActiveMQObjectMessage;

import java.io.Serializable;
import java.util.Random;

public class QueueMessageReceiver implements Runnable {
    private static int counter = 0;
    private static Random rnd = new Random();
    String name;

    public QueueMessageReceiver(String s) {
        this.name = s;
    }

	//zaaplikowac listener ktory czeka na request
    @Override
    public void run() {
        Connection connection = null;
        try {
            Context context = Common.getContext();
            connection = Common.createAndStartQueueConnection(context);
            Session session = Common.createSession(connection);
            MessageConsumer messageConsumer = Common.createQueueMessageConsumer(context, session);
			TprMessageListener listener = new TprMessageListener();
			messageConsumer.setMessageListener(listener);
            System.out.println("(RECEIVER) MESSAGE CONSUMER HAS STARTED " + counter++);
            Message message = messageConsumer.receive(rnd.nextInt(3) + 3);
            if (message instanceof ObjectMessage) {
                ObjectMessage objectMessage = (ObjectMessage) message;
                IResponse response = process(objectMessage);
                System.out.println("(RECEIVER) PROCESSED objectMessage MESSAGE: \"" + objectMessage.getObject() + "\"");
				//TODO: SEND RESPONSE BACK TO THE SENDER'S QUEUE
				MessageProducer messageProducer = Common.createMessageProducer(context, session, true);
				Message messageBack = Common.createMessage(session, response);
				messageProducer.send(messageBack);
            } else if (message != null) {
                System.out.println("(RECEIVER) CONSUMED NON-OBJECT MESSAGE");
            }
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

    private static IResponse process(ObjectMessage message) {
        try {
            Serializable content = message.getObject();
            if (content instanceof RandomRequest) {
                RandomRequest randomRequest = (RandomRequest) content;
                return new RandomResponse(randomRequest);

            } else if (content instanceof ArithmeticRequest) {
                ArithmeticRequest arithmeticRequest = (ArithmeticRequest) content;
                return new ArithmeticResponse(arithmeticRequest);
            }
        } catch (JMSException exception) {
            exception.printStackTrace();
        }
        return null;
    }
}