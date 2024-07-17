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

@WebServlet(name = "ViewCourseServlet", value = "/ViewCourseServlet")
public class ViewCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 13L;
       
    public ViewCourseServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAO dao = new DAO();
		List<Course> courses = dao.getAllCourses();

		if (courses != null) {
			request.setAttribute("Courses", courses);
			try {
				request.getRequestDispatcher("viewCourse.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		} else {
			request.setAttribute("message", "No data");
		}
		
	}

}
