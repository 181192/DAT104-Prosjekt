package no.hvl.dat104.controller.styrer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import no.hvl.dat104.controller.UrlMappings;

/**
 * Servlet implementation class VisAktivitetController
 */
public class VisAktivitetController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String aktivitetsId = request.getParameter("aktivitetId");
		// hent aktivitet i databasen.
		
		HttpSession mySession = request.getSession();
		mySession.setAttribute("aktivitetsId", aktivitetsId);
		
		request.getRequestDispatcher("WEB-INF/views/styrer/visaktivitet.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect(UrlMappings.VISAKTIVITET_URL);
	}

}
