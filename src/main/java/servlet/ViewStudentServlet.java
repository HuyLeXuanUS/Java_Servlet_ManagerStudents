package servlet;

import java.io.*;

import java.util.List;

import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;

import database.DAO;
import model.Student;

@WebServlet(name = "ViewStudentServlet", value = "/ViewStudentServlet")
public class ViewStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 15L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        DAO dao = new DAO();
        List<Student> students = dao.getAllStudents();

        if (students != null) {
            request.setAttribute("Students", students);
            try {
				request.getRequestDispatcher("viewStudent.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
        } else {
            request.setAttribute("message", "No data");
        }
    }
}