<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<html>
<head>
    <link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet" >
    <title>Todo List</title>
</head>
<body>
    <div class="container">
        <h1>Welcome ${name}</h1><br>
        <table class="table">
            <thead>
            <tr>
                <th>id</th>
                <th>description</th>
                <th>targetDate</th>
                <th>done</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${todoList}" var="todo">
                <tr>
                    <td>${todo.id}</td>
                    <td>${todo.description}</td>
                    <td>${todo.targetDate}</td>
                    <td>${todo.done}</td>
                    <td><a href="deleteTodo?id=${todo.id}" class="btn btn-warning">Delete</a> </td>
                    <td><a href="updateTodo?id=${todo.id}" class="btn btn-success">Update</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <script src="webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
        <script src="webjars/jquery/3.6.0/jquery.min.js"></script>
        <a href="addTodo" class="btn btn-success">Add</a>
    </div>
</body>
</html>
