package servlet;

import java.io.*;
import java.util.List;

import jakarta.servlet.http.*;
import model.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;

import database.DAO;

@WebServlet(name = "AddStudentServlet", value = "/AddStudentServlet")
public class AddStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 2L;
       

    public AddStudentServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String birthday = request.getParameter("birthday");
        String address = request.getParameter("address");
        String notes = request.getParameter("notes");

        DAO dao = new DAO();
        
        boolean isInserted = dao.addStudent(name, birthday, address, notes);
        List<Student> students = dao.getAllStudents();

        if (students != null) {
            request.setAttribute("Students", students);
            if (isInserted) {
                request.setAttribute("message", "Insertion successful!");
                try {
                    request.getRequestDispatcher("viewStudent.jsp").forward(request, response);
                } catch (ServletException | IOException e) {
                    e.printStackTrace();
                }
            } else {
                request.setAttribute("message", "Insertion failed!");
            }
        } else {
            request.setAttribute("message", "No data");
        }
        
    }
}
