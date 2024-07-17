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

@WebServlet(name = "DeleteStudentServlet", value = "/DeleteStudentServlet")
public class DeleteStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 4L;
       
    public DeleteStudentServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        DAO dao = new DAO();
        boolean isDeleted = dao.deleteStudentById(id);
        List<Student> students = dao.getAllStudents();

		if (students != null) {
			request.setAttribute("Students", students);
			if (isDeleted) {
				request.setAttribute("message", "Deletion successful!");
				try {
					request.getRequestDispatcher("viewStudent.jsp").forward(request, response);
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
			} else {
				request.setAttribute("message", "Deletion failed!");
			}
		} else {
			request.setAttribute("message", "No data");
		}
;
    }

}
