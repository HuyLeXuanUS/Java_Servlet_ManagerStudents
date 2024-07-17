package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Student;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import database.DAO;

@WebServlet(name = "SortStudentServlet", value = "/SortStudentServlet")
public class SortStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 10L;

    public SortStudentServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String order = request.getParameter("order");

        DAO dao = new DAO();
        List<Student> students = dao.getAllStudents();

        if (order!= null && order.equals("reset")) {
            request.setAttribute("Students", students);
            request.getRequestDispatcher("viewStudent.jsp").forward(request, response);
        } else {
            // Sort students by last name
            Collections.sort(students, new Comparator<Student>() {
                public int compare(Student s1, Student s2) {
                    String lastName1 = s1.getName().split(" ")[s1.getName().split(" ").length - 1];
                    String lastName2 = s2.getName().split(" ")[s2.getName().split(" ").length - 1];

                    if (order != null && order.equals("desc")) {
                        return lastName2.compareTo(lastName1);
                    } else {
                        return lastName1.compareTo(lastName2);
                    }
                }
            });

            request.setAttribute("Students", students);
            request.getRequestDispatcher("viewStudent.jsp").forward(request, response);
        }
	}

}
