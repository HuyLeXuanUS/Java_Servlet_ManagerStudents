package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Course;
import model.Student;

import java.io.IOException;
import java.util.List;

import database.DAO;

@WebServlet(name = "ViewCourseOfStudent", value = "/ViewCourseOfStudent")
public class ViewCourseOfStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ViewCourseOfStudent() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String studentId = request.getParameter("id");

		DAO dao = new DAO();
		List<Course> courses = dao.getCourseByStudentId(studentId);
		Student student = dao.getStudentById(studentId);

		request.setAttribute("Courses", courses);
		request.setAttribute("studentId", studentId);
		request.setAttribute("studentName", student.getName());
		request.setAttribute("birthday", student.getBirthday());
		request.getRequestDispatcher("viewCourseOfStudent.jsp").forward(request, response);
	}

}
