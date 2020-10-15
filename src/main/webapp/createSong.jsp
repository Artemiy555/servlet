<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Artemiy
  Date: 15.10.2020
  Time: 03:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>Edit Song</title>
</head>
<body>
<h3>Edit Song</h3>

<p style="color: red;">${errorString}</p>

<form accept-charset="UTF-8" method="post" action="${pageContext.request.contextPath}/createSongServlet">
    <input type="hidden" name="code"/>
    <table border="0">
        <tr>
            <td>Name</td>
            <td><input type="text" name="name"/></td>
        </tr>
        <tr>
            <td>Genre</td>
            <td><input type="text" name="genre"/></td>
        </tr>
        <tr>
            <td>Artist</td>
            <td><input type="text" name="artist"/></td>
        </tr>
        <tr>
            <td>Album</td>
            <td><input type="text" name="album"/></td>
        </tr>
        <tr>
            <td>link</td>
            <td><input type="text" name="link"/></td>
        </tr>
        <tr>
            <td>Time</td>
            <td><input type="text" name="time"/></td>
        </tr>
        <tr>
            <td>Year</td>
            <td><input type="text" name="year"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Submit"/>
                <a href="${pageContext.request.contextPath}/getAllSongServlet">Cancel</a>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
