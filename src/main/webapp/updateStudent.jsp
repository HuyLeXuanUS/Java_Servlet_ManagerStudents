<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Student" %>
<%
    Student student = (Student) request.getAttribute("student");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Cập nhật thông tin sinh viên</title>
    <style>
        body {
            font-size: 18px;
            padding: 20px;
        }
        label {
            display: block;
            margin-top: 20px;
        }
        input[type="text"], input[type="date"], textarea {
            width: 80%;
            padding: 10px;
            font-size: 16px;
        }
        input[type="submit"] {
            margin-top: 20px;
            font-size: 20px;
            padding: 10px 20px;
            width: 80%;
        }
    </style>
</head>
<body>
    <h1>Cập nhật thông tin sinh viên</h1>
    <form action="UpdateStudentServlet" method="post">
        <input type="hidden" name="id" value="<%= student.getId() %>" />
        <label for="name">Họ tên:</label><br>
        <input type="text" id="name" name="name" value="<%= student.getName() %>" required /><br>
        <label for="birthday">Ngày sinh:</label><br>
        <input type="date" id="birthday" name="birthday" value="<%= student.getBirthdayDate() %>" required /><br>
        <label for="address">Địa chỉ:</label><br>
        <input type="text" id="address" name="address" value="<%= student.getAddress() %>" required /><br>
        <label for="notes">Ghi chú:</label><br>
        <textarea id="notes" name="notes" required><%= student.getNotes() %></textarea><br>
        <input type="submit" value="Cập nhật" />
    </form>
</body>
</html>