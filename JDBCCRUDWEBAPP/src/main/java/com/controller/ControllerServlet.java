package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.Student;
import com.services.IStudentService;
import com.servicesFactory.StudentServiceFactory;

@WebServlet("/controller/*")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CREATE(request, response);
	}

	private void CREATE(HttpServletRequest request, HttpServletResponse response) throws IOException {
		IStudentService studentService = StudentServiceFactory.getStudentService();

		if (request.getRequestURI().endsWith("addform")) {
			String age = request.getParameter("sage");
			String name = request.getParameter("sname");
			String address = request.getParameter("saddr");

			Student std = new Student();
			std.setSname(name);
			std.setSage(Integer.parseInt(age));
			std.setSaddress(address);

			String status = studentService.addStudent(std);
			PrintWriter out = response.getWriter();

			if (status.equals("success")) {
				out.println("<h1 style='color: green; text-align: center;'>REGISTRATION SUCCESSFULL</h1>");
			} else {
				out.println("<h1 style='color: red; text-align: center;'>REGISTRATION FAILED</h1>");
			}
			out.close();
		}
	}
}
