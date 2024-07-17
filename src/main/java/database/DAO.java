package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;

import model.Course;
import model.Student;

import java.sql.Date;

public class DAO {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public DAO() {}

    // Student database operations
	public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();

        try {
            String query = "SELECT * FROM Student";
            connection = JDBCUntil.getConnection();
            preparedStatement =  connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getString("Id"));
                student.setName(resultSet.getString("Name"));
                student.setBirthday(resultSet.getDate("Birthday"));
                student.setAddress(resultSet.getString("Address"));
                student.setNotes(resultSet.getString("Notes"));
                students.add(student);
            }

            JDBCUntil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        JDBCUntil.closeConnection(connection);

        return students;
    }
	
	public boolean addStudent(String name, String birthday, String address, String notes) {
	    boolean isInserted = false;
	    try {
	        String query = "INSERT INTO Student (Name, Birthday, Address, Notes) VALUES (?, ?, ?, ?)";
	        connection = JDBCUntil.getConnection();
	        preparedStatement = connection.prepareStatement(query);

	        preparedStatement.setString(1, name);
	        preparedStatement.setDate(2, Date.valueOf(birthday));
	        preparedStatement.setString(3, address);
	        preparedStatement.setString(4, notes);

	        int rowAffected = preparedStatement.executeUpdate();
	        isInserted = rowAffected > 0;

	        JDBCUntil.closeConnection(connection);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return isInserted;
	}

    public Student getStudentById(String id) {
        Student student = new Student();
        try {
            String query = "SELECT * FROM Student WHERE Id = ?";
            connection = JDBCUntil.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                student.setId(resultSet.getString("Id"));
                student.setName(resultSet.getString("Name"));
                student.setBirthday(resultSet.getDate("Birthday"));
                student.setAddress(resultSet.getString("Address"));
                student.setNotes(resultSet.getString("Notes"));
            }

            JDBCUntil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    public boolean updateStudent(String id, String name, String birthday, String address, String notes) {
        boolean isUpdated = false;
        try {
            String query = "UPDATE Student SET Name = ?, Birthday = ?, Address = ?, Notes = ? WHERE Id = ?";
            connection = JDBCUntil.getConnection();
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, name);
            preparedStatement.setDate(2, Date.valueOf(birthday));
            preparedStatement.setString(3, address);
            preparedStatement.setString(4, notes);
            preparedStatement.setString(5, id);

            int rowAffected = preparedStatement.executeUpdate();
            isUpdated = rowAffected > 0;

            JDBCUntil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isUpdated;
    }

    public boolean deleteStudentById(String id) {
        boolean isDeleted = false;
        try {
            String query1 = "DELETE FROM Course_Student WHERE Student_Id = ?";
            String query2 = "DELETE FROM Student WHERE Id = ?";

            connection = JDBCUntil.getConnection();

            preparedStatement = connection.prepareStatement(query1);
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(query2);
            preparedStatement.setString(1, id);
            int rowAffected = preparedStatement.executeUpdate();
            isDeleted = rowAffected > 0;

            JDBCUntil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isDeleted;
    }

    public Student getStudentByName(String name) {
        Student student = new Student();
        try {
            String query = "SELECT * FROM Student WHERE Name = ?";
            connection = JDBCUntil.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                student.setId(resultSet.getString("Id"));
                student.setName(resultSet.getString("Name"));
                student.setBirthday(resultSet.getDate("Birthday"));
                student.setAddress(resultSet.getString("Address"));
                student.setNotes(resultSet.getString("Notes"));
            }

            JDBCUntil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    // Course database operations
    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        try {
            String query = "SELECT * FROM Course";
            connection = JDBCUntil.getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Course course = new Course();
                course.setId(resultSet.getString("Id"));
                course.setName(resultSet.getString("Name"));
                course.setLecture(resultSet.getString("Lecture"));
                course.setYear(resultSet.getInt("Year"));
                course.setNotes(resultSet.getString("Notes"));
                courses.add(course);
            }

            JDBCUntil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    public Course getCourseById(String id) {
        Course course = new Course();
        try {
            String query = "SELECT * FROM Course WHERE Id = ?";
            connection = JDBCUntil.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                course.setId(resultSet.getString("Id"));
                course.setName(resultSet.getString("Name"));
                course.setLecture(resultSet.getString("Lecture"));
                course.setYear(resultSet.getInt("Year"));
                course.setNotes(resultSet.getString("Notes"));
            }

            JDBCUntil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return course;
    }

    public boolean addCourse(String name, String lecture, int year, String notes) {
        boolean isInserted = false;
        try {
            String query = "INSERT INTO Course (Name, Lecture, Year, Notes) VALUES (?, ?, ?, ?)";
            connection = JDBCUntil.getConnection();
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lecture);
            preparedStatement.setInt(3, year);
            preparedStatement.setString(4, notes);

            int rowAffected = preparedStatement.executeUpdate();
            isInserted = rowAffected > 0;

            JDBCUntil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isInserted;
    }

    public boolean updateCourse(String id, String name, String lecture, int year, String notes) {
        boolean isUpdated = false;
        try {
            String query = "UPDATE Course SET Name = ?, Lecture = ?, Year = ?, Notes = ? WHERE Id = ?";
            connection = JDBCUntil.getConnection();
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lecture);
            preparedStatement.setInt(3, year);
            preparedStatement.setString(4, notes);
            preparedStatement.setString(5, id);

            int rowAffected = preparedStatement.executeUpdate();
            isUpdated = rowAffected > 0;

            JDBCUntil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isUpdated;
    }

    public boolean deleteCourseById(String id) {
        boolean isDeleted = false;
        try {
            String query1 = "DELETE FROM Course_Student WHERE Course_Id = ?";
            String query2 = "DELETE FROM Course WHERE Id = ?";

            connection = JDBCUntil.getConnection();

            preparedStatement = connection.prepareStatement(query1);
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(query2);
            preparedStatement.setString(1, id);
            int rowAffected = preparedStatement.executeUpdate();
            isDeleted = rowAffected > 0;

            JDBCUntil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isDeleted;
    }

    public String getCourseNameById(String id) {
        String courseName = "";
        try {
            String query = "SELECT Name FROM Course WHERE Id = ?";
            connection = JDBCUntil.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                courseName = resultSet.getString("Name");
            }

            JDBCUntil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseName;
    }

    // Course_Student database operations
    public List<Student> getStudentsByCourseId(String courseId) {
        List<Student> students = new ArrayList<>();
    
        try {
            String query = "SELECT Student.* FROM Student INNER JOIN Course_Student ON Student.Id = Course_Student.Student_Id WHERE Course_Student.Course_Id = ?";
            connection = JDBCUntil.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, courseId);
            resultSet = preparedStatement.executeQuery();
    
            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getString("Id"));
                student.setName(resultSet.getString("Name"));
                student.setBirthday(resultSet.getDate("Birthday"));
                student.setAddress(resultSet.getString("Address"));
                student.setNotes(resultSet.getString("Notes"));
                students.add(student);
            }
    
            JDBCUntil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        JDBCUntil.closeConnection(connection);
    
        return students;
    }

    public List<Student> getStudentsNotInCourseId(String courseId) {
        List<Student> students = new ArrayList<>();
        try {
            String query = "SELECT * FROM Student WHERE Id NOT IN (SELECT Student_Id FROM Course_Student WHERE Course_Id = ?)";
            connection = JDBCUntil.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, courseId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getString("Id"));
                student.setName(resultSet.getString("Name"));
                student.setBirthday(resultSet.getDate("Birthday"));
                student.setAddress(resultSet.getString("Address"));
                student.setNotes(resultSet.getString("Notes"));
                students.add(student);
            }

            JDBCUntil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public boolean insertStudentInCourse(String studentId, String courseId) {
        boolean isInserted = false;
        try {
            String query = "INSERT INTO Course_Student (Course_Id, Student_Id) VALUES (?, ?)";
            connection = JDBCUntil.getConnection();
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, courseId);
            preparedStatement.setString(2, studentId);

            int rowAffected = preparedStatement.executeUpdate();
            isInserted = rowAffected > 0;

            JDBCUntil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isInserted;
    }

    public boolean deleteStudentInCourse(String studentId, String courseId) {
        boolean isDeleted = false;
        try {
            String query = "DELETE FROM Course_Student WHERE Course_Id = ? AND Student_Id = ?";
            connection = JDBCUntil.getConnection();
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, courseId);
            preparedStatement.setString(2, studentId);

            int rowAffected = preparedStatement.executeUpdate();
            isDeleted = rowAffected > 0;

            JDBCUntil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isDeleted;
    }

    public List<Student> getStudentInYear(int year) {
        List<Student> students = new ArrayList<>();
    
        try {
            connection = JDBCUntil.getConnection();
            String sql = "SELECT Student.* FROM Student " +
                         "JOIN Course_Student ON Student.Id = Course_Student.Student_Id " +
                         "JOIN Course ON Course.Id = Course_Student.Course_Id " +
                         "WHERE Course.Year = ?";
    
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, year);
            ResultSet rs = preparedStatement.executeQuery();
    
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getString("Id"));
                student.setName(rs.getString("Name"));
                student.setBirthday(rs.getDate("Birthday"));
                student.setAddress(rs.getString("Address"));
                student.setNotes(rs.getString("Notes"));
    
                students.add(student);
            }
    
            JDBCUntil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return students;
    }

    public List<Course> getCourseByStudentId(String studentId) {
        List<Course> courses = new ArrayList<>();
    
        try {
            connection = JDBCUntil.getConnection();
            String sql = "SELECT Course.*, Course_Student.Grade FROM Course " +
                         "JOIN Course_Student ON Course.Id = Course_Student.Course_Id " +
                         "JOIN Student ON Student.Id = Course_Student.Student_Id " +
                         "WHERE Student.Id = ?";
    
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, studentId);
            ResultSet rs = preparedStatement.executeQuery();
    
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getString("Id"));
                course.setName(rs.getString("Name"));
                course.setLecture(rs.getString("Lecture"));
                course.setYear(rs.getInt("Year"));
                course.setNotes(rs.getString("Notes"));
                course.setGrade(rs.getFloat("Grade"));
    
                courses.add(course);
            }
    
            JDBCUntil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return courses;
    }

    public boolean updateGrade(String studentId, String courseId, float grade) {
        boolean isUpdated = false;
        try {
            connection = JDBCUntil.getConnection();
            String sql = "UPDATE Course_Student SET Grade = ? WHERE Student_Id = ? AND Course_Id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setFloat(1, grade);
            preparedStatement.setString(2, studentId);
            preparedStatement.setString(3, courseId);
    
            int rowAffected = preparedStatement.executeUpdate();
            isUpdated = rowAffected > 0;
    
            JDBCUntil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return isUpdated;
    }
}
