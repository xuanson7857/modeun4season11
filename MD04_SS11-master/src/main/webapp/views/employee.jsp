<%--
  Created by IntelliJ IDEA.
  User: 84787
  Date: 27-Nov-23
  Time: 10:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <style>
        body {
            padding: 5%;
        }
    </style>
</head>
<body>
<h1 class="text-center">Emlpoyee List</h1>
<form action="employee?action=search" method="post">

    <input type="text" class="form-control inline-block" name="searchTerm"/>
    <button class="btn btn-success inline-block">Search</button>
</form>
<table class="table">
    <thead>
    <tr>
        <th scope="col"> ID</th>
        <th scope="col">Name</th>
        <th scope="col"> Phone</th>
        <th scope="col">Address</th>
        <th scope="col">Birthday</th>
        <th scope="col">Sex</th>
        <th scope="col">Salary</th>
        <th scope="col">Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${employeeList}" var="employee">
        <tr>
            <td>${employee.id}</td>
            <td>${employee.name}</td>
            <td>${employee.phone}</td>
            <td>${employee.address}</td>
            <td>${employee.birthday}</td>
            <td>${employee.sex ? "Nam" : "Nữ"}</td>
            <td>${employee.salary}</td>

            <td>
                <a href="employee?action=edit&id=${employee.id}" class="btn btn-success">Edit</a>
                <a href="employee?action=delete&id=${employee.id}" class="btn btn-danger">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="employee?action=add" class="btn btn-primary">Add</a>
<a href="employee?action=sort" class="btn btn-info">Sort by name</a>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>

</body>
</html>