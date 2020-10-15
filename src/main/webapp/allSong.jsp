<%--
  Created by IntelliJ IDEA.
  User: Artemiy
  Date: 15.10.2020
  Time: 02:21
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <title>All Song</title>
</head>
<body>
<h3>Song List</h3>

<p style="color: red;">${errorString}</p>

<%--<input type="file" id="ctrl" webkitdirectory directory multiple/>--%>

<table border="1" cellpadding="5" cellspacing="1" >
    <tr>
        <th>Name</th>
        <th>Genre</th>
        <th>Artist</th>
        <th>Album</th>
        <th>Time</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>

    <c:forEach items="${songs}" var="song" >
        <tr>
            <td>${song.name}</td>
            <td>${song.genre}</td>
            <td>${song.artist}</td>
            <td>${song.album}</td>
            <td>${song.time}</td>
            <td>
                <a href="${pageContext.request.contextPath}/editServlet?id=${song.id}">Edit</a>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/removeSong?name=${song.name}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
