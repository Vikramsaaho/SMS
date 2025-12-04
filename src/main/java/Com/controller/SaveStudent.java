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

@WebServlet("/save")
public class SaveStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Student obj = new Student();
		obj.setName(request.getParameter("name"));
		obj.setEmail(request.getParameter("email"));
		obj.setPassword(request.getParameter("password"));
		obj.setAge(Integer.parseInt(request.getParameter("age")));
		obj.setGender(request.getParameter("gender"));
		obj.setMobile(request.getParameter("mobile"));
		obj.setDepartment(request.getParameter("department"));
		obj.setAddress(request.getParameter("address"));
		RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
		
		StudentDAO dao = new StudentDAO();
		try {
			boolean status = dao.saveEmployee(obj);
			if(status) {
				dao.commit();
				request.setAttribute("status", "Registration Successfully");
				dispatcher.forward(request, response);
			}else {
				dao.rollback();
				request.setAttribute("status", "Registration Failed");
				dispatcher.forward(request, response);
			}
		}
		catch(ClassNotFoundException e) {
			System.out.println(e);
		}
		catch(SQLException e) {
			System.out.println(e);
		}
		
	}

}
		
