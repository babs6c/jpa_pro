package com.exos.servlets;



import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exos.dao.UtilisateurDao;
import com.exos.entities.Utilisateur;
import com.exos.forms.InscriptionForm;

/**
 * Servlet implementation class Inscription
 */
@WebServlet(urlPatterns="/Inscription")
@MultipartConfig(location="/Users/macair/fichierstemp/",maxFileSize = 10 * 1024 * 1024, maxRequestSize = 5 * 10 * 1024 * 1024, fileSizeThreshold = 1024 * 1024)
public class Inscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
    // Injection de notre EJB (Session Bean Stateless)
	@EJB
	private UtilisateurDao  utilisateurDao;
	public static final String CONF_DAO_FACTORY = "daofactory";

    
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Inscription() {
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
			  
			this.getServletContext().getRequestDispatcher("/WEB-INF/signup.jsp").forward(request, response);
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
		

		
		InscriptionForm form=new InscriptionForm(utilisateurDao);
		Utilisateur utilisateur=form.addMembre(request);
		
		if(!form.getErreurs().isEmpty())
		{
			request.setAttribute("erreurs", form.getErreurs());
			request.setAttribute("utilisateur", utilisateur);
			
		}
		else
		{
			request.setAttribute("message","Inscription réussie !");
		}
      
        
		this.getServletContext().getRequestDispatcher("/WEB-INF/signup.jsp").forward(request, response);

	}
	
	
	

}
