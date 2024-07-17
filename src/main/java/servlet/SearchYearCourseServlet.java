package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Course;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import database.DAO;

@WebServlet(name = "SearchYearCourseServlet", value = "/SearchYearCourseServlet")
public class SearchYearCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 8L;
       
    public SearchYearCourseServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search = request.getParameter("searchYear");
		int searchInt = Integer.parseInt(search);

		DAO dao = new DAO();
		List<Course> courses = dao.getAllCourses();

		List<Course> searchResults = new ArrayList<Course>();
		for (Course course : courses) {
			if (course.getYear() == searchInt){
				searchResults.add(course);
			}
		}

		request.setAttribute("Courses", searchResults);
		request.getRequestDispatcher("viewCourse.jsp").forward(request, response);
	}

}
