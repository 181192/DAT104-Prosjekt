package no.hvl.dat104.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hvl.dat104.util.InnloggingUtil;

/**
 * Servlet implementation class LandingServlet
 */
public class LandingServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		if(InnloggingUtil.erInnloggetSomBruker(request) ||InnloggingUtil.erInnloggetSomAdmin((request))) {
			response.sendRedirect(UrlMappings.LOGGINN_URL);
		}else if (InnloggingUtil.erInnloggetSomDeltager(request)){
			response.sendRedirect(UrlMappings.GITILBAKEMELDING_URL);
		}else {
			request.getRequestDispatcher(JspMappings.LANDING_JSP).forward(request, response);
		}
		
	}

	
}
