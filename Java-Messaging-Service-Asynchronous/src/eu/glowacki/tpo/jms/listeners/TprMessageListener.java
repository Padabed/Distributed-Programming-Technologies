package eu.glowacki.tpo.jms.listeners;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serializable;

import javax.jms.*;
import javax.naming.Context;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import eu.glowacki.tpo.jms.Common;
import eu.glowacki.tpo.jms.messages.ArithmeticRequest;
import eu.glowacki.tpo.jms.messages.ArithmeticResponse;
import eu.glowacki.tpo.jms.messages.RandomRequest;
import eu.glowacki.tpo.jms.messages.RandomResponse;
import org.apache.activemq.command.ActiveMQObjectMessage;

public class TprMessageListener extends JFrame implements MessageListener {

	private static final long serialVersionUID = 5203912244025667139L;
	private Connection connection;
	private JTextArea _text = new JTextArea(10, 20);

	public TprMessageListener() {

		try {
			Context context = Common.getContext();
			connection = Common.createAndStartTopicConnection(context);
			Session session = Common.createSession(connection);
			MessageConsumer messageConsumer = Common.createQueueMessageConsumer(context, session);
			//MessageConsumer messageConsumer = Common.createTopicSubscriber(context, session);
			//MessageConsumer messageConsumer = Common.createDurableTopicSubscriber(context, session);
			messageConsumer.setMessageListener(this);
			connection.start();
		} catch (Exception exc) {
			exc.printStackTrace();
			System.exit(1);
		}

		add(new JScrollPane(_text));

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				try {
					connection.close();
				} catch (Exception exc) {
				}
				dispose();
				System.exit(0);
			}
		});
		setTitle("Awaiting");
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	int i = 0;

	@Override
	public void onMessage(Message request) {
		ObjectMessage objectMessage = (ObjectMessage) request;
		setTitle("Received msg " + ++i);
		try {
				Serializable content = objectMessage.getObject();
				if (content instanceof RandomRequest) {
					RandomRequest randomRequest = (RandomRequest) content;
					_text.append(String.valueOf(new RandomResponse(randomRequest)));

				} else if (content instanceof ArithmeticRequest) {
					ArithmeticRequest arithmeticRequest = (ArithmeticRequest) content;
					_text.append(String.valueOf(new ArithmeticResponse(arithmeticRequest)));
				}
			} catch (JMSException exception) {
				exception.printStackTrace();
			}
	}

	public static void main(String[] args) {
		new TprMessageListener();
	}
}