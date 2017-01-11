<jsp:useBean id="task" scope="request" class="com.epam.pmt.domain.Task"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TaskPage</title>
</head>
<body>
<p>Id: ${task.id}</p>
<p>Title: ${task.title}</p>
<p>Description: ${task.description}</p>
<p>Created: ${task.createdDate}</p>
<p>StartDate: ${task.startDate}</p>
<p>EndingDate: ${task.endingDate}</p>
<p>Status: ${task.status}</p>
<p>Priority: ${task.priority}</p>
<p>Project: ${task.project}</p>
<hr>
<form method="get">
    <button type="submit" formaction="/task/${task.id}/delete/">Delete this SHIT!</button>
    <button type="submit" formaction="/project/${task.project.id}/">Back to project!</button>
</form>

</body>
</html>
