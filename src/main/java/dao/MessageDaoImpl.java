package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import domain.Message;

public class MessageDaoImpl implements MessageDao {
	
	private Connection getConnection() throws Exception {
		return DriverManager.getConnection(
				System.getenv("GUESTBOOK_DB_URL"), 
				System.getenv("GUESTBOOK_DB_USER"), 
				System.getenv("GUESTBOOK_DB_PASS"));
	}

	public void createMessage(Message message) throws Exception {
		String sql = "insert into messages (author, content) values (?, ?)";
		
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			
			statement.setString(1, message.getAuthor());
			statement.setString(2, message.getContent());
			
			statement.executeUpdate();
		}
	}

	public List<Message> getMessages() throws Exception {
		String sql = "select id, author, content from messages order by id desc";
		
		List<Message> list = new ArrayList<Message>();
		
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Message message = new Message();
				message.setId(resultSet.getLong(1));
				message.setAuthor(resultSet.getString(2));
				message.setContent(resultSet.getString(3));
				list.add(message);
			}
			return list;
		}
	}

}
