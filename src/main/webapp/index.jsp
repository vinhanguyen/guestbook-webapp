<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<title>Guestbook</title>
		<link rel="stylesheet" href="<c:url value="/style.css" />">
	</head>
	<body>
		<form action="<c:url value="/guestbook" />" method="post">
			<p>
				Name:<br>
				<input type="text" name="author" value="Anonymous">
			</p>
			<p>
				Message:<br>
				<textarea rows="5" cols="100" name="content"></textarea>
			</p>
			<p><input type="submit" value="Submit"></p>
		</form>
		
		<h1>Messages:</h1>
		<c:forEach var="message" items="${messages}">
			<div class="message">
				<p class="author">${message.author} says:</p>
				<p class="content">${message.content}</p>
			</div>
		</c:forEach>
	</body>
</html>