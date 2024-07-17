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

@WebServlet(name = "AddCourseServlet", value = "/AddCourseServlet")
public class AddCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddCourseServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String lecture = request.getParameter("lecture");
		String year = request.getParameter("year");
		String notes = request.getParameter("notes");

		DAO dao = new DAO();

		try {
			int yearInt = Integer.parseInt(year);
			boolean isInserted = dao.addCourse(name, lecture, yearInt, notes);
			List<Course> courses = dao.getAllCourses();

			if (courses != null) {
				request.setAttribute("Courses", courses);
				if (isInserted) {
					request.setAttribute("message", "Insertion successful!");
					try {
						request.getRequestDispatcher("viewCourse.jsp").forward(request, response);
					} catch (ServletException | IOException e) {
						e.printStackTrace();
					}
				} else {
					request.setAttribute("message", "Insertion failed!");
				}
			} else {
				request.setAttribute("message", "No data");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
