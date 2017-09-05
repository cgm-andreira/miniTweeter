<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page session="true" %>
<html>
<head>
	<title>${user.name}</title>
</head>
<body>
	<% String user = (String) request.getSession().getAttribute("userName"); %>
	<% if(user == null || user == ""){ %>
		<h1>Hello, to see and post messages, please <a href = "login">login</a>!</h1>  
	<% } else { %>
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
		
		<h3>Friends: </h3>
	 	<c:forEach items="${friends}" var="friend">
			<div style="border: 1px solid green; margin: 20px 10px; padding-left: 15px">
				<h4><a href="user/${friend.username}">${friend.name}</a></h4>
	        </div>
		</c:forEach>
		
	<% } %>

	
</body>
</html>
