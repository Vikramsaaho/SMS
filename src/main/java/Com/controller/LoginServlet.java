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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		HttpSession session= request.getSession(true);
		
		if(email.equalsIgnoreCase("admin@cg.com")&& password.equals("admin@123")) {
			session.setAttribute("email", email);
			response.sendRedirect("admin.jsp");
		}
		
		else {
			StudentDAO dao= new StudentDAO();
			try {
			boolean status= dao.checkLogin(email, password);
			if(status) {
				session.setAttribute("email", email);
				response.sendRedirect("student.jsp");
			}
			else {
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
				request.setAttribute("status", "invalid credentials");
				dispatcher.forward(request, response);
				
			}
			}
			catch(ClassNotFoundException e) {
			}
			catch(SQLException e) {
				System.out.println(e);
		}	
	
		
	}

}
		}
