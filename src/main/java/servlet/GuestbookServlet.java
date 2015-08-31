package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MessageDao;
import dao.MessageDaoImpl;
import domain.Message;

public class GuestbookServlet extends HttpServlet {

	private static Logger logger = Logger.getLogger(GuestbookServlet.class.getName());
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MessageDao dao = new MessageDaoImpl();
		
		List<Message> list = new ArrayList<Message>();
		
		try {
			list = dao.getMessages();
		} catch (Exception e) {
			logger.log(Level.WARNING, "Error getting messages", e);
		}
		
		req.setAttribute("messages", list);
		
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MessageDao dao = new MessageDaoImpl();
		
		Message message = new Message();
		message.setAuthor(req.getParameter("author"));
		message.setContent(req.getParameter("content"));
		
		try {
			dao.createMessage(message);
		} catch (Exception e) {
			logger.log(Level.WARNING, "Error saving message", e);
		}
		
		resp.sendRedirect(req.getRequestURI());
	}
}
