<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="true" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Welcome to miniTweeter 
</h1>
<h3 style="background-color: pink">${message}</h3>
<h3>Before you proceed, please login</h3>
 	<form:form id="loginForm" modelAttribute="login" action="processLogin" method="post">
                <table >
                    <tr>
                        <td>
                            <label path="username">Username: </label>
                        </td>
                        <td>
                            <input path="username" name="username" id="username" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label path="password">Password:</label>
                        </td>
                        <td>
                            <input type="password" path="password" name="password" id="password" />
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td align="left">
                            <button type="submit" id="login" name="login">Login</button>
                        </td>
                    </tr>
                </table>
            </form:form>
</body>
</html>
