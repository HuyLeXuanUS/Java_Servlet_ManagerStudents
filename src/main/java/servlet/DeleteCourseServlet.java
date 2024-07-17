package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Course;

import java.io.IOException;
import java.util.List;

import database.DAO;

@WebServlet(name = "DeleteCourseServlet", value = "/DeleteCourseServlet")
public class DeleteCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 3L;

    public DeleteCourseServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");

		DAO dao = new DAO();
		boolean isDeleted = dao.deleteCourseById(id);
		List<Course> courses = dao.getAllCourses();

		if (courses != null) {
			request.setAttribute("Courses", courses);
			if (isDeleted) {
				request.setAttribute("message", "Deletion successful!");
				try {
					request.getRequestDispatcher("viewCourse.jsp").forward(request, response);
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
			} else {
				request.setAttribute("message", "Deletion failed!");
			}
		} else {
			request.setAttribute("message", "No data");
		}
	}

}
