<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Course" %>
<%
    Course course = (Course) request.getAttribute("course");
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
        input[type="number"] {
            width: 25%;
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
    <h1>Cập nhật thông tin khóa học</h1>
    <form action="UpdateCourseServlet" method="post">
        <input type="hidden" name="id" value="<%= course.getId() %>" />
        <label for="name">Tên khóa học:</label><br>
        <input type="text" id="name" name="name" value="<%= course.getName() %>" required /><br>
        <label for="lecture">Giáo viên:</label><br>
        <input type="text" id="lecture" name="lecture" value="<%= course.getLecture() %>" required /><br>
        <label for="year">Năm học:</label><br>
        <input type="number" id="year" name="year" value="<%= course.getYear() %>" required /><br>
        <label for="notes">Ghi chú:</label><br>
        <textarea id="notes" name="notes" required><%= course.getNotes() %></textarea><br>
        <input type="submit" value="Cập nhật" />
    </form>
</body>
</html>