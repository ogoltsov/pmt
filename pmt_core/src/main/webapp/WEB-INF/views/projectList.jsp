<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>AgileBoard</title>
</head>
<body>
<c:forEach var="project" items="${projects}">
    <p><b>${project.id}</b></p>
    <p>${project}</p>
    <p>${project.description.length()}</p>
    <a href="/project/${project.id}">View</a>
    <hr>
</c:forEach>
</body>
</html>
