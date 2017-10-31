package no.hvl.dat104.controller.styrer.bruker;

import static no.hvl.dat104.controller.UrlMappings.LOGGINN_URL;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hvl.dat104.controller.JspMappings;
import no.hvl.dat104.dataaccess.IBrukerEAO;
import no.hvl.dat104.model.Bruker;

/**
 * Servlet implementation class OpprettBrukerController
 */
public class OpprettBrukerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private IBrukerEAO brukerEAO;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher(JspMappings.OPPRETTBRUKER_JSP).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BrukerValidator skjema = new BrukerValidator(request);
		if (skjema.erAlleDataGyldig()) {
			Bruker bruker = new Bruker();
			bruker.setFornavn(request.getParameter("fornavn"));
			bruker.setEtternavn(request.getParameter("etternavn"));
			bruker.setMail(request.getParameter("mail"));
			bruker.setPassord(request.getParameter("passord"));
			brukerEAO.leggTilBruker(bruker);
			request.getSession().removeAttribute("skjema");
			response.sendRedirect(LOGGINN_URL);
		}
	}

}
