<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="model.Student" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%
    Object objIn = request.getAttribute("StudentsInCourse");
    Object objNotIn = request.getAttribute("StudentsNotInCourse");
    String message = (String) request.getAttribute("message");
    String courseId = (String) request.getAttribute("courseId");
    String courseName = (String) request.getAttribute("courseName");

    List<?> listIn = null;
    List<?> listNotIn = null;

    if (objIn instanceof List<?>) {
        listIn = (List<?>) objIn;
    }

    if (objNotIn instanceof List<?>) {
        listNotIn = (List<?>) objNotIn;
    }

    List<Student> studentsInCourse = new ArrayList<Student>();
    List<Student> studentsNotInCourse = new ArrayList<Student>();
    
    if (listIn != null) {
        for (Object o : listIn) {
            if (o instanceof Student) {
                studentsInCourse.add((Student) o);
            }
        }
    }

    if (listNotIn != null) {
        for (Object o : listNotIn) {
            if (o instanceof Student) {
                studentsNotInCourse.add((Student) o);
            }
        }
    }
%> 

<% if (message != null) { %>
    <p><%= message %></p>
<% } %>

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
        <div style="width: 100%; text-align: center;">
            <h2>Thông tin chi tiết lớp học: <%= courseName %></h2>
        </div>
    
        <div style="display: flex; justify-content: space-between; width: 100%;">
            <div style="width: 45%; height: 500px; overflow: auto; border: 1px solid #000;">
                <table border="1" style="width: 100%;">
                    <tr>
                        <td colspan="4" style="text-align: center;"><strong>Danh sách sinh viên trong lớp</strong></td>
                    </tr>
                    <tr>
                        <th>Mã số</th>
                        <th>Họ tên</th>
                        <th>Ngày sinh</th>
                        <th>Xóa S/V khỏi lớp</th>
                    </tr>
                    <% for(Student student1 : studentsInCourse) { %>
                    <tr>
                        <td><%= student1.getId() %></td>
                        <td><%= student1.getName() %></td>
                        <td><%= student1.getBirthday() %></td>
                        <td>
                            <form id="deleteForm<%= student1.getId() %>" action="InsertDeleteStudentInCourseServlet" method="post">
                                <input type="hidden" name="order" value="delete">
                                <input type="hidden" name="courseId" value="<%= courseId %>">
                                <input type="hidden" name="sId" value="<%= student1.getId() %>">
                            </form>
                            <a href="#" onclick="if(confirm('Bạn có chắc chắn muốn xóa sinh viên này khỏi lớp không?')) { document.getElementById('deleteForm<%= student1.getId() %>').submit(); }">Xóa khỏi lớp</a>
                        </td>
                    </tr>
                    <% } %>
                </table>
            </div>
    
            <div style="width: 45%; height: 500px; overflow: auto; border: 1px solid #000;">
                <table border="1" style="width: 100%;">
                    <tr>
                        <td colspan="4" style="text-align: center;"><strong>Danh sách sinh viên ngoài lớp</strong></td>
                    </tr>
                    <tr>
                        <th>Mã số</th>
                        <th>Họ tên</th>
                        <th>Ngày sinh</th>
                        <th>Thêm S/V vào lớp</th>
                    </tr>
                    <% for(Student student2 : studentsNotInCourse) { %>
                    <tr>
                        <td><%= student2.getId() %></td>
                        <td><%= student2.getName() %></td>
                        <td><%= student2.getBirthday() %></td>
                        <td>
                            <form id="insertForm<%= student2.getId() %>" action="InsertDeleteStudentInCourseServlet" method="post">
                                <input type="hidden" name="order" value="insert">
                                <input type="hidden" name="courseId" value="<%= courseId %>">
                                <input type="hidden" name="sId" value="<%= student2.getId() %>">
                            </form>
                            <a href="#" onclick="if(confirm('Bạn có chắc chắn muốn thêm sinh viên này vào lớp không?')) { document.getElementById('insertForm<%= student2.getId() %>').submit(); }">Thêm vào lớp</a>
                        </td>
                    </tr>
                    <% } %>
                </table>
            </div>
        </div>
    </div>
</div>