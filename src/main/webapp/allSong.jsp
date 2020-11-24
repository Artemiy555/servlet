<%--
  Created by IntelliJ IDEA.
  User: Artemiy
  Date: 15.10.2020
  Time: 02:21
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>All Song</title>
</head>
<body bgcolor="#c0c0c0">
<CENTER>
    <h1>Song List</h1>

    <p style="color: red;">${errorString}</p>
    <p><a href="${pageContext.request.contextPath}/createSongServlet">
        <button>Создать</button>
    </a></p>

    <table border="1" cellpadding="5" cellspacing="1">
        <tr>
            <th>Name</th>
            <th>Genre</th>
            <th>Artist</th>
            <th>Album</th>
            <th>Time</th>
            <th>Year</th>
            <th>Link</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>

        <c:forEach items="${songs}" var="song">
            <tr>
                <td>${song.name}</td>
                <td>${song.genre}</td>
                <td>${song.artist}</td>
                <td>${song.album}</td>
                <td>${song.time}</td>
                <td>${song.year}</td>
                <td>${song.link}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/editServlet" method="GET">
                        <input type="hidden" name="id" value="${song.id}"/>
                        <input type="submit" value="Edit"/>
                    </form>
                </td>
                <td>
                    <form action="${pageContext.request.contextPath}/removeSong?id=${song.id}" method="POST">
                        <input type="submit" value="Remove"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</CENTER>
</body>
</html>
