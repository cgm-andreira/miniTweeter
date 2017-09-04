<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
	<% String user = (String) request.getSession().getAttribute("userName"); %>
	<% if(user == null || user == ""){ %>
		<h1>Hello, to see and post messages, please <a href = "login">login</a>!</h1>  
	<% } else { %>
		<h1>Hello, ${userName}!</h1>
		<form:form id="newMessageForm" modelAttribute="message" action="postMessage" method="post">
		<table >
		     <tr>
		         <td>
		             <label path="message">Post-a-tweet: </label>
		         </td>
		         <td>
		             <input path="message" name="message" id="message" />
		         </td>
		     </tr>
		     <tr>
		         <td></td>
		         <td align="left">
		             <button type="submit" id="post" name="post">Post it!</button>
		         </td>
		     </tr>
	 	</table>
	 	</form:form>
	 <h3>Messages: </h3>
	 <c:forEach items="${messages}" var="message">
		<div style="border: 1px solid green; margin: 0px 20px;">
			<h4>${message.message}</h4>
			<h4>${message.user.name}</h4>
        </div>
	</c:forEach>
	<% } %>

	
</body>
</html>
