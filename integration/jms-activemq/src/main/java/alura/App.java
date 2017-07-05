package alura;

import java.util.Scanner;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;

public class App {
	public static void main(String[] args) throws Exception {
		InitialContext ctx = new InitialContext();

		ConnectionFactory factory = (ConnectionFactory) ctx.lookup("ConnectionFactory");
		Connection conn = factory.createConnection();
		conn.start();
		
		Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination destination = (Destination) ctx.lookup("financeiro");
		MessageConsumer consumer = session.createConsumer(destination);
		
		consumer.setMessageListener(
				(m) -> { 
					try {
						System.out.println(((TextMessage) m).getText());
					} catch (JMSException e) {
						e.printStackTrace();
					} 
					
				}
				
		);
		
		
		try (Scanner scanner = new Scanner(System.in)) {
			scanner.nextLine();
		}
		
		session.close();
		conn.close();
		ctx.close();
	}
}
