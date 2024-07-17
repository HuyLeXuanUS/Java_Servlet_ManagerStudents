<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Course</title>
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
    <h2>Thêm khóa học</h2>
    <form action="AddCourseServlet" method="post">
        <label for="name">Nhập tên khóa học:</label>
        <input type="text" id="name" name="name" required>
        <label for="lecture">Nhập Giáo viên:</label>
        <input type="text" id="lecture" name="lecture" required>
        <label for="year">Nhập năm học:</label>
        <input type="number" id="year" name="year" required>
        <label for="notes">Nhập ghi chú:</label>
        <textarea id="notes" name="notes" required></textarea>
        
        <input type="submit" value="Add Course">
    </form>
</body>

</html>