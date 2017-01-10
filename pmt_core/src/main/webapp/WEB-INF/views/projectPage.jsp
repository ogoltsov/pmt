<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <a href="/task/${task.id}">View Task</a>
    <hr>
</c:forEach>

<form method="get">
    <button type="submit" formaction="/project/${project.id}/delete">Delete this SHIT!</button>
    <button type="submit" formaction="/project/">Back to list of project</button>
</form>
</body>
</html>
