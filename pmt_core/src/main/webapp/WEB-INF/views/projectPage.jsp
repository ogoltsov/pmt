<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Kirill_Ogoltsov
  Date: 12/27/2016
  Time: 10:03 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${project.title}</title>
</head>
<body>
<h1>${project.title}</h1>
<p>${project.description}</p>
<p>${project.startDate}</p>
<p>${project.endDate}</p>
<h2>Tasks:</h2>
<c:forEach items="${project.tasks}" var="task">
    <p>${task.title}</p>
    <p>${task.description}</p>
    <p>${task.createdDate} - ${task.startDate} - ${task.endingDate}</p>
    <p>${task.project.title}</p>
    <p>${task.status.title}</p>
    <hr>
</c:forEach>


<form action="/project/delete/${project.id}" method="get">
    <button type="submit">Delete this SHIT!</button>
</form>
</body>
</html>
