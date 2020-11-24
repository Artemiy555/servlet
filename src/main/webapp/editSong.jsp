<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Artemiy
  Date: 15.10.2020
  Time: 03:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <title>Edit Song</title>
</head>
<body bgcolor="#c0c0c0">
<center>
<h3>Edit Song</h3>

<p style="color: red;">${errorString}</p>

<c:if test="${not empty song}">
    <form accept-charset="UTF-8" method="post" action="${pageContext.request.contextPath}/editServlet">
        <input type="hidden" name="id" value="${song.id}" />
        <table border="0">
            <tr>
                <td>Id</td>
                <td style="color:red;">${song.id}</td>
            </tr>
            <tr>
                <td>Name</td>
                <td><input type="text" name="name" value="${song.name}" /></td>
            </tr>
            <tr>
                <td>Genre</td>
                <td><input type="text" name="genre" value="${song.genre}" /></td>
            </tr>
            <tr>
                <td>Artist</td>
                <td><input type="text" name="artist" value="${song.artist}" /></td>
            </tr>
            <tr>
                <td>Album</td>
                <td><input type="text" name="album" value="${song.album}" /></td>
            </tr>
            <tr>
                <td>link</td>
                <td><input type="text" name="link" value="${song.link}"/></td>
            </tr>
            <tr>
                <td>Time</td>
                <td><input type="text" name="time" value="${song.time}"/></td>
            </tr>
            <tr>
                <td>Year</td>
                <td><input type="text" name="year" value="${song.year}"/></td>
            </tr>
            <tr>
                <td colspan = "2">
                    <input type="submit" value="Submit" />
                    <a href="${pageContext.request.contextPath}/getAllSongServlet">Cancel</a>
                </td>
            </tr>
        </table>
    </form>
</c:if>
</center>
</body>
</html>
