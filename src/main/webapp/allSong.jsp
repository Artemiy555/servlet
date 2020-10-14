
<%--
  Created by IntelliJ IDEA.
  User: Artemiy
  Date: 15.10.2020
  Time: 02:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>


<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>Product List</h3>

<p style="color: red;">${errorString}</p>

<table border="1" cellpadding="5" cellspacing="1" >
    <tr>
        <th>Code</th>
        <th>Name</th>
        <th>Price</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
<%--    <jsp:useBean id="songs" scope="request" type="java.util.List"/>--%>
    <c:forEach items="${songs}" var="product" >
        <tr>
            <td>${songs.name}</td>
            <td>${songs.genre}</td>
            <td>${songs.artist}</td>
            <td>${songs.album}</td>
            <td>${songs.time}</td>
<%--            <td>--%>
<%--                <a href="editProduct?code=${product.code}">Edit</a>--%>
<%--            </td>--%>
<%--            <td>--%>
<%--                <a href="deleteProduct?code=${product.code}">Delete</a>--%>
<%--            </td>--%>
        </tr>
    </c:forEach>
</table>
</body>
</html>
