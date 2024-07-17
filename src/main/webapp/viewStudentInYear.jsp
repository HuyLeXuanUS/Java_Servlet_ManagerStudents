<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="model.Student" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%
    Object obj = request.getAttribute("Students");
    String message = (String) request.getAttribute("message");
    String year = (String) request.getAttribute("year");

    List<?> list = null;
    if (obj instanceof List<?>) {
        list = (List<?>) obj;
    }
    List<Student> students = new ArrayList<Student>();
    
    if (list != null) {
        for (Object o : list) {
            if (o instanceof Student) {
                students.add((Student) o);
            }
        }
    }
%> 

<% if (message != null) { %>
    <p><%= message %></p>
<% } %>

<% if (year == null) { %>
<% year = "Nhập năm học"; %>
<% } %>

<head>
    <style>
        .search-year-form input[type="number"] {
            width: 300px; 
            height: 40px; 
            font-size: 16px; 
        }

        .search-year-form input[type="submit"] {
            width: 100px; 
            height: 40px; 
            font-size: 16px; 
        }
    </style>
</head>

<div style="display: flex;">
    <div style="flex: 0 0 200px; padding: 20px; background: #f8f9fa;">
        <form action="ViewStudentServlet" method="get">
        	<button style="display: block; width: 100%; height: 50px; margin-bottom: 10px;">Trang sinh viên</button>
        </form>
        <form action="ViewCourseServlet" method="get">
            <button type="submit" style="display: block; width: 100%; height: 50px; margin-bottom: 10px;">Trang khóa học</button>
        </form>
        <button style="display: block; width: 100%; height: 50px; margin-bottom: 10px;">QLSV theo Năm học</button>
    </div>
    <div style="flex-grow: 1; padding: 20px;">
        <div class="search-year-form">
		    <form action="SearchYearStudentServlet" method="get">
                <input type="number" id="year" name="searchYear" placeholder="<%= year == null ? "Nhập năm học" : year %>" required/>
                <input type="submit" value="Tìm kiếm" />
            </form>
		</div>
        <div style="width: 100%; height: 500px; overflow: auto; border: 1px solid #000;">
            <table border="1" style="width: 100%;">
                <tr>
                    <td colspan="7" style="text-align: center;"><strong>Danh sách sinh viên</strong></td>
                </tr>
                <tr>
                    <th>Mã số</th>
                    <th>Họ tên</th>
                    <th>Ngày sinh</th>
                    <th>Địa chỉ</th>
                    <th>Ghi chú</th>
					<th>Danh sách K/H</th>
                </tr>
                <% for(Student student : students) { %>
                <tr>
                    <td><%= student.getId() %></td>
                    <td><%= student.getName() %></td>
                    <td><%= student.getBirthday() %></td>
                    <td><%= student.getAddress() %></td>
                    <td><%= student.getNotes() %></td>
                    <td>
                        <a href="ViewCourseOfStudent?id=<%= student.getId() %>">Xem khóa học</a>
                    </td>
                </tr>
                <% } %>
            </table>
        </div>
    </div>
</div>