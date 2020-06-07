package com.makotojava.intro;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DispatchServlet
 */
//@WebServlet({  "/home", "/antarctica", "/alaska", "/arctic", "/australia"})
@WebServlet({  "/home"})
public class DispatchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final WebsiteTitle myapp = new WebsiteTitle();
	
	private HumanResourcesApplication hrApp;

    /**
     * Default constructor. 
     */
    public DispatchServlet() {
    	hrApp = new HumanResourcesApplication();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getRequestURI().substring(request.getContextPath().length());
		
		System.out.println(path);
		request.setAttribute("myapp", myapp);
		request.setAttribute("employees", hrApp.getEmployees());
		RequestDispatcher view = request.getRequestDispatcher(path + ".jsp");
        view.forward(request, response); 		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
