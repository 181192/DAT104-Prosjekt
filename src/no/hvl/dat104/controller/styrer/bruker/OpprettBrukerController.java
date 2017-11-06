package no.hvl.dat104.controller.styrer.bruker;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hvl.dat104.controller.JspMappings;
import no.hvl.dat104.controller.UrlMappings;
import no.hvl.dat104.dataaccess.IBrukerEAO;
import no.hvl.dat104.dataaccess.IRolleEAO;
import no.hvl.dat104.model.Bruker;

/**
 * Servlet implementation class OpprettBrukerController
 */
public class OpprettBrukerController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private IBrukerEAO brukerEAO;
	private IRolleEAO rolleEAO;

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
			bruker.setIdRolle(rolleEAO.finnRolle(0));
			// Må endres senere
			bruker.setSalt("testSalt");
			brukerEAO.leggTilBruker(bruker);
			response.sendRedirect(UrlMappings.LOGGINN_URL);
		} else {
			skjema.settOppFeilmeldinger(request);
			request.getSession().setAttribute("skjema", skjema);
			response.sendRedirect(UrlMappings.OPPRETTBRUKER_URL);
		}
	}
}
