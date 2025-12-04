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





@WebServlet("/edituser")

public class UpdateStudent extends HttpServlet {

	private static final long serialVersionUID = 1L;





	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		EStudent obj=new Student();

		obj.setId(Integer.parseInt(request.getParameter("id")));

		obj.setName(request.getParameter("name"));

		obj.setEmail(request.getParameter("email"));

		

		obj.setAge(Integer.parseInt(request.getParameter("age")));

		obj.setGender(request.getParameter("gender"));

		obj.setMobile(request.getParameter("mobile"));

		obj.setDepartment(request.getParameter("department"));

		obj.setAddress(request.getParameter("address"));

		

		StudentDAO dao=new StudentDAO();

		RequestDispatcher dispatcher =request.getRequestDispatcher("findAll");

		try {

			Boolean status= dao.updateEmployee(obj);

			if(status) {

				dao.commit();

				dispatcher.forward(request, response);

			}else {

				dao.rollback();

				dispatcher.forward(request, response);

			}

		}

		catch(ClassNotFoundException e) {

			System.out.println(e);

			

		}catch(SQLException e) {

			System.out.println(e);

			

		}	

		

	}



}