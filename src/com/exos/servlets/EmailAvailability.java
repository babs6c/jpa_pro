package com.exos.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.exos.dao.UtilisateurDao;
import com.exos.entities.Utilisateur;

/**
 * Servlet implementation class EmailAvailability
 */
@WebServlet(urlPatterns="/EmailAvailability/*")
public class EmailAvailability extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private UtilisateurDao utilisateurDao;

   
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmailAvailability() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	 	String email=request.getParameter("email");
	 	Utilisateur utilisateur=utilisateurDao.trouver(email);
	 // Set content type of the response so that jQuery knows what it can expect.
	    response.setContentType("text/plain");  
	    response.setCharacterEncoding("UTF-8"); 
	    if(utilisateur==null)
	    {
	    		response.getWriter().write("true"); 
	    }
	    else
	    {
	    	response.getWriter().write("false");
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
