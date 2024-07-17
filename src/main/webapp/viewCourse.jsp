<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="model.Course" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%
    Object obj = request.getAttribute("Courses");
    String message = (String) request.getAttribute("message");
    List<?> list = null;
    if (obj instanceof List<?>) {
        list = (List<?>) obj;
    }
    List<Course> courses = new ArrayList<Course>();
    
    if (list != null) {
        for (Object o : list) {
            if (o instanceof Course) {
                courses.add((Course) o);
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
        .search-name-form input[type="text"] {
            width: 300px; 
            height: 40px; 
            font-size: 16px; 
        }

        .search-name-form input[type="submit"] {
            width: 100px; 
            height: 40px; 
            font-size: 16px; 
        }

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
        <button style="display: block; width: 100%; height: 50px; margin-bottom: 10px;">Trang khóa học</button>
        <form action="viewStudentInYear.jsp">
            <button type="submit" style="display: block; width: 100%; height: 50px; margin-bottom: 10px;">QLSV theo Năm học</button>
        </form>
    </div>
    <div style="flex-grow: 1; padding: 20px;">
        <div style="width: 100%; height: 500px; overflow: auto; border: 1px solid #000;">
            <table border="1" style="width: 100%;">
                <tr>
                    <td colspan="8" style="text-align: center;"><strong>Danh sách khóa học</strong></td>
                </tr>
                <tr>
                    <th>Mã số</th>
                    <th>Tên</th>
                    <th>Giáo viên</th>
                    <th>Năm học</th>
                    <th>Ghi chú</th>
                    <th>Cập nhật</th>
                    <th>Xóa K/H</th>
                    <th>Chi tiết K/H</th>
                </tr>
                <% for(Course course : courses) { %>
                <tr>
                    <td><%= course.getId() %></td>
                    <td><%= course.getName() %></td>
                    <td><%= course.getLecture() %></td>
                    <td><%= course.getYear() %></td>
                    <td><%= course.getNotes() %></td>
                    <td>
                        <a href="UpdateCourseServlet?id=<%= course.getId() %>">Cập nhật</a>
                    </td>
                    <td>
                        <form id="deleteForm<%= course.getId() %>" action="DeleteCourseServlet" method="post">
                            <input type="hidden" name="id" value="<%= course.getId() %>" />
                        </form>
                        <a href="#" onclick="if(confirm('Bạn chắc chắn muốn xóa khóa học này?')) { document.getElementById('deleteForm<%= course.getId() %>').submit(); }">Xóa</a>
                    </td>
                    <td>
                        <a href="ViewDetailCourseServlet?id=<%= course.getId() %>">Chi tiết</a>
                    </td>
                </tr>
                <% } %>
            </table>
        </div>
		
		<div class="button-container">
		    <form action="addCourse.jsp">
		        <input type="submit" value="Thêm khóa học" style="font-size: 20px; padding: 10px;" />
		    </form>
		    <form action="SortCourseServlet" method="get">
		        <input type="submit" value="Tăng dần theo tên" style="font-size: 20px; padding: 10px;" />
		        <input type="hidden" name="order" value="asc" />
		    </form>
		    <form action="SortCourseServlet" method="get">
		        <input type="submit" value="Giảm dần theo tên" style="font-size: 20px; padding: 10px;" />
		        <input type="hidden" name="order" value="desc" />
		    </form>
            <form action="SortCourseServlet" method="get">
		        <input type="submit" value="Reset Danh sách" style="font-size: 20px; padding: 10px;" />
		        <input type="hidden" name="order" value="reset" />
		    </form>
		</div>
		
		<div class="search-name-form">
		    <form action="SearchNameCourseServlet" method="get">
		        <input type="text" id="name" name="searchName" placeholder="Nhập tên khóa học" required/>
		        <input type="submit" value="Tìm kiếm" />
		    </form>
		</div>

        <div class="search-year-form">
		    <form action="SearchYearCourseServlet" method="get">
		        <input type="number" id="year" name="searchYear" placeholder="Nhập năm khóa học" required/>
		        <input type="submit" value="Tìm kiếm" />
		    </form>
		</div>
    </div>
</div>