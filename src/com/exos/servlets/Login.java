package com.exos.servlets;

import java.io.IOException;


import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exos.dao.UtilisateurDao;
import com.exos.entities.Utilisateur;
import com.exos.forms.ConnexionForm;




/**
 * Servlet implementation class Login
 */
@WebServlet(urlPatterns="/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private UtilisateurDao  utilisateurDao;
	public static final String CONF_DAO_FACTORY = "daofactory";

  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		
		if(session.getAttribute("email")==null)
		{
			
			 Cookie[] cookies = request.getCookies();
		        if (cookies != null) 
		        {
		            for (Cookie cookie : cookies) 
		            {
		                if (cookie.getName().equals("email")) 
		                {
		                    request.setAttribute("cookie_email", cookie.getValue());
		                }
		            }
		        }
			this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
		}
		else
		{
			response.sendRedirect(request.getContextPath() + "/Accueil");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ConnexionForm form=new ConnexionForm(utilisateurDao);
		Utilisateur utilisateur=form.connexion(request);
	
		if(utilisateur==null)
		{
			request.setAttribute("membre", form);
			this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
		}
		else
		{	
			Cookie cookiee=new Cookie("email",utilisateur.getEmail());
			cookiee.setMaxAge(3600*24*30);
			response.addCookie(cookiee);
	    		HttpSession session=request.getSession();
			session.setAttribute("email", utilisateur.getEmail());
			String nom=utilisateur.getNom();
			if(nom.length()>7)
			{
				String[] splited = nom.split("\\s+");
				nom=splited[0];
			}
			session.setAttribute("nom", nom);
			response.sendRedirect(request.getContextPath() + "/Accueil");
		}
		
	}

}
