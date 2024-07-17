<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Student</title>
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
    <h2>Thêm sinh viên</h2>
    <form action="AddStudentServlet" method="post">
        <label for="name">Nhập tên:</label>
        <input type="text" id="name" name="name" required>
        <label for="birthday">Nhập ngày sinh:</label>
        <input type="date" id="birthday" name="birthday" required>
        <label for="address">Nhập địa chỉ:</label>
        <input type="text" id="address" name="address" required>
        <label for="notes">Nhập ghi chú:</label>
        <textarea id="notes" name="notes" required></textarea>
        
        <input type="submit" value="Add Student">
    </form>
</body>
</html>