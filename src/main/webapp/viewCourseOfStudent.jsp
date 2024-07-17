<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="model.Course" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%
    Object obj = request.getAttribute("Courses");
    String message = (String) request.getAttribute("message");
    String studentId = (String) request.getAttribute("studentId");
    String studentName = (String) request.getAttribute("studentName");
    String birthday = (String) request.getAttribute("birthday");
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
<div style="display: flex;">
    <div style="flex: 0 0 200px; padding: 20px; background: #f8f9fa;">
        <form action="ViewStudentServlet" method="get">
        	<button style="display: block; width: 100%; height: 50px; margin-bottom: 10px;">Trang sinh viên</button>
        </form>
        <form action="ViewCourseServlet" method="get">
            <button type="submit" style="display: block; width: 100%; height: 50px; margin-bottom: 10px;">Trang khóa học</button>
        </form>
        <form action="viewStudentInYear.jsp">
            <button type="submit" style="display: block; width: 100%; height: 50px; margin-bottom: 10px;">QLSV theo Năm học</button>
        </form>
    </div>

    <div style="flex-grow: 1; padding: 20px;">
        <div style="width: 100%; text-align: left;">
            <h2>Thông tin chi tiết lớp học của sinh viên</h2>
            <h2>Họ và tên: <%= studentName %></h2>
            <h2>Ngày sinh: <%= birthday %></h2>
        </div>
    
        <div style="display: flex; justify-content: space-between; width: 100%;">
            <div style="width: 100%; height: 500px; overflow: auto; border: 1px solid #000;">
                <table border="1" style="width: 100%;">
                    <tr>
                        <td colspan="7" style="text-align: center;"><strong>Danh sách lớp học của sinh viên</strong></td>
                    </tr>
                    <tr>
                        <th>Mã số</th>
                        <th>Tên khóa học</th>
                        <th>Giáo viên</th>
                        <th>Năm học</th>
                        <th>Ghi chú</th>
                        <th>Điểm</th>
                        <th>Cập nhật điểm</th>
                    </tr>
                    <% for(Course course : courses) { %>
                    <tr>
                        <td><%= course.getId() %></td>
                        <td><%= course.getName() %></td>
                        <td><%= course.getLecture() %></td>
                        <td><%= course.getYear() %></td>
                        <td><%= course.getNotes() %></td>
                        <td><%= course.getGrade() %></td>
                        <td>
						    <form id="updateForm<%= course.getId() %>" action="UpdateGradeServlet" method="post">
						        <input type="hidden" name="courseId" value="<%= course.getId() %>">
						        <input type="hidden" name="studentId" value="<%= studentId %>">
						        <input type="number" name="grade" placeholder="Nhập điểm" required>
						        <input type="submit" value="Cập nhật điểm">
						    </form>
						</td>
                    </tr>
                    <% } %>
                </table>
            </div>
        </div>
    </div>
</div>