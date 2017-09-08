<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page session="true" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
	<% 
		String user = (String) request.getSession().getAttribute("userName");
	%>
	<% if(user == null || user == ""){ %>
		<h1>Hello, to see and post messages, please <a href = "login">login</a>!</h1>  
	<% } else { %>
		<h1>Hello, ${userName}!</h1>
		
		<ul style="list-style: none; display: block">
			<li style="float:left; display: inline-block">
				<a href="/miniTweeter2" style="display: inline-block; padding: 5px; background-color: #F0F0F0">
					Home
				</a>
			</li>
			<li style="float:left; display: inline-block">
				<a href="friends" style="display: inline-block; padding: 5px; background-color: #F0F0F0">
					Friends
				</a>
			</li>
			<li style="float:left; display: inline-block">
				<a href="user/<%= (String) request.getSession().getAttribute("userLinkAddress") %>" style="display: inline-block; padding: 5px; background-color: #F0F0F0">
					About
				</a>
			</li>
			<li style="float:left; display: inline-block">
				<a href="logout" style="display: inline-block; padding: 5px; background-color: #F0F0F0">
					Logout
				</a>
			</li>
		</ul>
		<br/>
		
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
		<div style="border: 1px solid green; margin: 20px 10px; padding-left: 15px">
		<h4 style="color:dark-gray; margin-top: 10px">${message.user.name} wrote:</h4>
			<h4>${message.message}</h4>
        </div>
	</c:forEach>
	<% } %>

	
</body>
</html>
