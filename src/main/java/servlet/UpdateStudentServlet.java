package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import database.DAO;
import model.Student;

@WebServlet(name = "UpdateStudentServlet", value = "/UpdateStudentServlet")
public class UpdateStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 12L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        DAO dao = new DAO();
        Student student = dao.getStudentById(id);

        if (student != null) {
            request.setAttribute("student", student);
            request.getRequestDispatcher("updateStudent.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String birthday = request.getParameter("birthday");
        String address = request.getParameter("address");
        String notes = request.getParameter("notes");

        DAO dao = new DAO();
        boolean isUpdated = dao.updateStudent(id, name, birthday, address, notes);
        List<Student> students = dao.getAllStudents();

        if (students != null) {
            request.setAttribute("Students", students);
            if (isUpdated) {
                request.setAttribute("message", "Update successful!");
                try {
                    request.getRequestDispatcher("viewStudent.jsp").forward(request, response);
                } catch (ServletException | IOException e) {
                    e.printStackTrace();
                }
            } else {
                request.setAttribute("message", "Update failed!");
            }
        } else {
            request.setAttribute("message", "No data");
        }

    }
}
