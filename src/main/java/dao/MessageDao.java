package dao;

import java.util.List;

import domain.Message;

public interface MessageDao {

	void createMessage(Message message) throws Exception;
	List<Message> getMessages() throws Exception;
}
