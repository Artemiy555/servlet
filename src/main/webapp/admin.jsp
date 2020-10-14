<%@ page import="com.devEducation.model.Song" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="com.google.gson.reflect.TypeToken" %>
<%@ page import="org.jsoup.Jsoup" %>
<%@ page import="org.jsoup.nodes.Document" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: Artemiy
  Date: 14.10.2020
  Time: 02:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script>

        $(document).ready(function () {
            $("#update").click(function () {

                var url = "${pageContext.request.contextPath}/updateSong" +
                    "?id=" + $("#id").val() +
                    "&name=" + $("#name").val();
                alert(url);
                $.ajax({
                    url: url,
                    success: function () {
                        $("#result").val("OK");
                    },
                    error: function () {
                        $("#result").val("Error");
                    }
                });


            });
        });


    </script>


    <title>Title</title>
</head>
<body>

<input type="number" id="id"/>

<input type="text" id="name"/>

<button id="update">Submit</button>

<input name="result" type="text" id="result"/>
<br/>


<%

    String url = "http://localhost:8080/servlet_war/getSong?artist=*";
    System.out.println(url);
    Document doc = Jsoup.connect(url).ignoreContentType(true).get();

    List<Song> songs;
    songs = new Gson().fromJson(String.valueOf(doc.text()),
            new TypeToken<List<Song>>() {
            }.getType());
    for (Song song : songs) {
        out.println("<p>" + song.toString() + "</p>");
    }
%>



</body>
</html>
