package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import database.DAO;
import model.Student;

@WebServlet(name = "SearchStudentServlet", value = "/SearchStudentServlet")
public class SearchStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 7L;

    public SearchStudentServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search = request.getParameter("search");

		DAO dao = new DAO();
		List<Student> students = dao.getAllStudents();

		List<Student> searchResults = new ArrayList<Student>();
		for (Student student : students) {
			if (student.getName().toLowerCase().contains(search.toLowerCase())) {
				searchResults.add(student);
			}
		}

		request.setAttribute("Students", searchResults);
		request.getRequestDispatcher("viewStudent.jsp").forward(request, response);
	}
}
