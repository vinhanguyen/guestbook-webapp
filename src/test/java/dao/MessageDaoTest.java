package dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.Message;

public class MessageDaoTest {
	
	private MessageDao dao;
	
	@Before
	public void setup() {
		this.dao = new MessageDaoImpl();
	}
	
	@After
	public void tearDown() {
		this.dao = null;
	}

	@Test
	public void testCreateMessage() {
		
		String now = String.format("%d", System.currentTimeMillis()),
				author = String.format("test user %s", now),
				content = String.format("test message %s", now);
		
		Message message = new Message();
		message.setAuthor(author);
		message.setContent(content);
		
		try {
			this.dao.createMessage(message);
		} catch (Exception e) {
			System.out.println(e);
			fail("Error saving message");
		}
		
		try {
			List<Message> list = this.dao.getMessages();
			for (Message m : list) {
				if (m.getAuthor().equals(author)) {
					assertEquals("Content should match", content, m.getContent());
					return;
				}
			}
			fail("Message should be in list");
		} catch (Exception e) {
			System.out.println(e);
			fail("Error getting messages");
		}
	}
}
