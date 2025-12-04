package Com.controller;

import java.io.IOException;

import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Com.dao.StudentDAO;
import Com.model.Student;

@WebServlet("/findByEmail")
public class SearchStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");
		
		StudentDAO dao = new StudentDAO();
		try {
			Student student= dao.searchStudent(email);
			if(student.getId()==0) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("search.jsp");
				request.setAttribute("status", "Student Not Found");
				dispatcher.forward(request, response);
			}
			else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("viewemp.jsp");
				request.setAttribute("student", student);
				dispatcher.forward(request, response);
			}
		
		}
		catch(ClassNotFoundException e){
			System.out.println(e);
		}
		catch(SQLException e) {
			System.out.println(e);
		}
		
	}
}
