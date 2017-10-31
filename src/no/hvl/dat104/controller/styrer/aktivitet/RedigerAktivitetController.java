package no.hvl.dat104.controller.styrer.aktivitet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import no.hvl.dat104.controller.JspMappings;
import no.hvl.dat104.controller.UrlMappings;

/**
 * Servlet implementation class RedigerAktivitetController
 */
public class RedigerAktivitetController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher(JspMappings.REDIGERAKTIVITET_JSP).forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String aktivitetsId = request.getParameter("aktivitetId");
		HttpSession mySession = request.getSession();
		mySession.setAttribute("aktivitetsId", aktivitetsId);
		response.sendRedirect(UrlMappings.REDIGERAKTIVITET_URL);
	}

}
