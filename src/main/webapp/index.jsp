<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<title>Guestbook</title>
	</head>
	<body>
		<form action="<c:url value="/guestbook" />" method="post">
			<p><input type="text" name="author" value="Anonymous"></p>
			<p><textarea rows="5" cols="100" name="content"></textarea></p>
			<p><input type="submit" value="Submit"></p>
		</form>
		<c:forEach var="message" items="${messages}">
			<div>
				<p>${message.content}</p>
				<p>By ${message.author}</p>
			</div>
		</c:forEach>
	</body>
</html>