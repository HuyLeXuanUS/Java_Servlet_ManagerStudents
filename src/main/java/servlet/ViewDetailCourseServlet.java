package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Student;

import java.io.IOException;
import java.util.List;

import database.DAO;

@WebServlet(name = "ViewDetailCourseServlet", value = "/ViewDetailCourseServlet")
public class ViewDetailCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 14L;
       
    public ViewDetailCourseServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");

		DAO dao = new DAO();

		String courseName = dao.getCourseNameById(id);
		
		List<Student> studentsIn = dao.getStudentsByCourseId(id);
		List<Student> studentsNotIn = dao.getStudentsNotInCourseId(id);

		request.setAttribute("StudentsInCourse", studentsIn);
		request.setAttribute("StudentsNotInCourse", studentsNotIn);
		request.setAttribute("courseName", courseName);
		request.setAttribute("courseId", id);
		request.getRequestDispatcher("viewDetailCourse.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
