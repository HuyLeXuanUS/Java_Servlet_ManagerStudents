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

@WebServlet(name = "UpdateCourseServlet", value = "/UpdateCourseServlet")
public class UpdateCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 11L;
       
    public UpdateCourseServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        DAO dao = new DAO();
        Course course = dao.getCourseById(id);

        if (course != null) {
            request.setAttribute("course", course);
            request.getRequestDispatcher("updateCourse.jsp").forward(request, response);
        }
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String lecture = request.getParameter("lecture");
		String year = request.getParameter("year");
		String notes = request.getParameter("notes");

		DAO dao = new DAO();

		try {
			int yearInt = Integer.parseInt(year);
			boolean isUpdated = dao.updateCourse(id, name, lecture, yearInt, notes);
			List<Course> courses = dao.getAllCourses();

			if (courses != null) {
				request.setAttribute("Courses", courses);
				if (isUpdated) {
					request.setAttribute("message", "Update successful!");
					try {
						request.getRequestDispatcher("viewCourse.jsp").forward(request, response);
					} catch (ServletException | IOException e) {
						e.printStackTrace();
					}
				} else {
					request.setAttribute("message", "Update failed!");
				}
			} else {
				request.setAttribute("message", "No data");
			}
			
		} catch (NumberFormatException e) {
			
		}

	}
}
