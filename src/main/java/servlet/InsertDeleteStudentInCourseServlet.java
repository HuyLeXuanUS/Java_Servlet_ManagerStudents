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

@WebServlet(name = "InsertDeleteStudentInCourseServlet", value = "/InsertDeleteStudentInCourseServlet")
public class InsertDeleteStudentInCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 5L;
       
    public InsertDeleteStudentInCourseServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String order = request.getParameter("order");
		String studentId = request.getParameter("sId");
		String courseId = request.getParameter("courseId");


		boolean isSuccessful = false;

		DAO dao = new DAO();

		if (order!= null && order.equals("insert")) {
			isSuccessful = dao.insertStudentInCourse(studentId, courseId);
		} else {
			isSuccessful = dao.deleteStudentInCourse(studentId, courseId);
		}

		if (isSuccessful) {
			request.setAttribute("message", "Student was successfully " + order + "ed in course");

		} else {
			request.setAttribute("message", "Student was not " + order + "ed in course");
		}

		List<Student> studentsIn = dao.getStudentsByCourseId(courseId);
		List<Student> studentsNotIn = dao.getStudentsNotInCourseId(courseId);

		String courseName = dao.getCourseNameById(courseId);

		request.setAttribute("StudentsInCourse", studentsIn);
		request.setAttribute("StudentsNotInCourse", studentsNotIn);
		request.setAttribute("courseName", courseName);
		request.setAttribute("courseId", courseId);
		request.getRequestDispatcher("viewDetailCourse.jsp").forward(request, response);

	}

}
