package Com.controller;

import java.io.IOException;


import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Com.dao.StudentDAO;
import Com.model.Student;




@WebServlet("/viewprofile")
public class ViewProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession(true);
		String email =(String) session.getAttribute("email");
		
		StudentDAO dao = new StudentDAO();
		
		
		try {
			Student student = dao.searchStudent(email);
			RequestDispatcher dispatcher = request.getRequestDispatcher("viewemp.jsp");
			request.setAttribute("student", student);
			dispatcher.forward(request, response);
			
		} catch (ClassNotFoundException e) {
			System.out.println();
			
		} catch (SQLException e) {
			System.out.println();
			
			;
		}
		
		
		
		}

}
