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

@WebServlet(name = "SearchYearStudentServlet", value = "SearchYearStudentServlet")
public class SearchYearStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SearchYearStudentServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search = request.getParameter("searchYear");
		int searchInt = Integer.parseInt(search);

		DAO dao = new DAO();
		List<Student> students = dao.getStudentInYear(searchInt);

		request.setAttribute("Students", students);
		request.setAttribute("year", search);
		request.getRequestDispatcher("viewStudentInYear.jsp").forward(request, response);
	}

}
