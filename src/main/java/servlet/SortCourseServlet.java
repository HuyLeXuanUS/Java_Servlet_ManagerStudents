package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Course;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import database.DAO;

@WebServlet(name = "SortCourseServlet", value = "/SortCourseServlet")
public class SortCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 9L;
       
    public SortCourseServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String order = request.getParameter("order");

		DAO dao = new DAO();
		List<Course> courses = dao.getAllCourses();

		if (order!= null && order.equals("reset")) {
			request.setAttribute("Courses", courses);
			request.getRequestDispatcher("viewCourse.jsp").forward(request, response);
		} else {
			Collections.sort(courses, new Comparator<Course>() {
				public int compare(Course c1, Course c2) {
					if (order != null && order.equals("desc")) {
						return c2.getName().compareTo(c1.getName());
					} else {
						return c1.getName().compareTo(c2.getName());
					}
				}
			});

			request.setAttribute("Courses", courses);
			request.getRequestDispatcher("viewCourse.jsp").forward(request, response);
		}
	}
}
