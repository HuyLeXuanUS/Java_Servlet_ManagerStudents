<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="model.Student" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%
    Object obj = request.getAttribute("Students");
    String message = (String) request.getAttribute("message");
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
<head>
	<style>
		 .button-container {
		     display: flex;
		     flex-direction: row;
		     justify-content: flex-start;
		     gap: 20px; 
		 }
		
		 .button-container form {
		 	margin-top: 20;
		     margin-bottom: 20; 
		 }
	</style>
    <style>
        .search-form input[type="text"] {
            width: 300px; 
            height: 40px; 
            font-size: 16px; 
        }

        .search-form input[type="submit"] {
            width: 100px; 
            height: 40px; 
            font-size: 16px; 
        }
    </style>
</head>
<div style="display: flex;">
    <div style="flex: 0 0 200px; padding: 20px; background: #f8f9fa;">
        <button style="display: block; width: 100%; height: 50px; margin-bottom: 10px;">Trang sinh viên</button>
        <form action="ViewCourseServlet" method="get">
            <button type="submit" style="display: block; width: 100%; height: 50px; margin-bottom: 10px;">Trang khóa học</button>
        </form>
        <form action="viewStudentInYear.jsp">
            <button type="submit" style="display: block; width: 100%; height: 50px; margin-bottom: 10px;">QLSV theo Năm học</button>
        </form>
    </div>
    <div style="flex-grow: 1; padding: 20px;">
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
                    <th>Cập nhật TT</th>
                    <th>Xóa S/V</th>
                </tr>
                <% for(Student student : students) { %>
                <tr>
                    <td><%= student.getId() %></td>
                    <td><%= student.getName() %></td>
                    <td><%= student.getBirthday() %></td>
                    <td><%= student.getAddress() %></td>
                    <td><%= student.getNotes() %></td>
                    <td>
                        <a href="UpdateStudentServlet?id=<%= student.getId() %>">Cập nhật</a>
                    </td>
                    <td>
                        <form id="deleteForm<%= student.getId() %>" action="DeleteStudentServlet" method="post">
                            <input type="hidden" name="id" value="<%= student.getId() %>" />
                        </form>
                        <a href="#" onclick="if(confirm('Bạn chắc chắn muốn xóa sinh viên này?')) { document.getElementById('deleteForm<%= student.getId() %>').submit(); }">Xóa</a>
                    </td>
                </tr>
                <% } %>
            </table>
        </div>
		
		<div class="button-container">
		    <form action="addStudent.jsp">
		        <input type="submit" value="Thêm sinh viên" style="font-size: 20px; padding: 10px;" />
		    </form>
		    <form action="SortStudentServlet" method="get">
		        <input type="submit" value="Tăng dần theo tên" style="font-size: 20px; padding: 10px;" />
		        <input type="hidden" name="order" value="asc" />
		    </form>
		    <form action="SortStudentServlet" method="get">
		        <input type="submit" value="Giảm dần theo tên" style="font-size: 20px; padding: 10px;" />
		        <input type="hidden" name="order" value="desc" />
		    </form>
		    <form action="SortStudentServlet" method="get">
		        <input type="submit" value="Reset Danh sách" style="font-size: 20px; padding: 10px;" />
		        <input type="hidden" name="order" value="reset" />
		    </form>
		</div>
		
		<div class="search-form">
		    <form action="SearchStudentServlet" method="get">
		        <input type="text" id="name" name="search" placeholder="Nhập tên sinh viên" required/>
		        <input type="submit" value="Tìm kiếm" />
		    </form>
		</div>
    </div>
</div>