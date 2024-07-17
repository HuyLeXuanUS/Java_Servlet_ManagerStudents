package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import database.DAO;

import java.io.IOException;

@WebServlet(name = "UpdateGradeServlet", value = "/UpdateGradeServlet")
public class UpdateGradeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UpdateGradeServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String studentId = request.getParameter("studentId");
        String courseId = request.getParameter("courseId");
        float grade = Float.parseFloat(request.getParameter("grade"));

        DAO dao = new DAO();
        dao.updateGrade(studentId, courseId, grade);

        response.sendRedirect("ViewCourseOfStudent?id=" + studentId);
    }
}