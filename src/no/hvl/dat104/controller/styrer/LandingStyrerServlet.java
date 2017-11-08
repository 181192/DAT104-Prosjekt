package no.hvl.dat104.controller.styrer;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hvl.dat104.controller.JspMappings;
import no.hvl.dat104.controller.UrlMappings;
import no.hvl.dat104.dataaccess.IAktivitetEAO;
import no.hvl.dat104.dataaccess.IBrukerEAO;
import no.hvl.dat104.dataaccess.IEventEAO;
import no.hvl.dat104.model.Aktivitet;
import no.hvl.dat104.model.Bruker;
import no.hvl.dat104.model.Event;
import no.hvl.dat104.util.InnloggingUtil;

/**
 * Indexsiden til brukeren
 */

public class LandingStyrerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private IBrukerEAO brukerEAO;
	@EJB
	private IAktivitetEAO aktivitetEAO;
	@EJB
	private IEventEAO eventEAO;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (InnloggingUtil.erInnloggetSomBruker(request)) {
			Bruker bruker = InnloggingUtil.innloggetSomBruker(request);
			List<Aktivitet> a = brukerEAO.finnAlleAktiviteterTilBruker(bruker.getId());
			System.out.println(a);
			for (Aktivitet ak : a) {
				System.out.println(ak.getNavn());
			}
			List<Event> alleEventer = brukerEAO.finnAlleEventerTilBruker(bruker.getId());
			String[] farger = { "orange", "green", "red", "orange", "yellow" };
			request.getSession().setAttribute("alleEventer", alleEventer);
			request.getSession().setAttribute("aktiviteter", a);
			request.getSession().setAttribute("color", farger);
			request.getRequestDispatcher(JspMappings.LANDING_STYRER_JSP).forward(request, response);
		} else {
			response.sendRedirect(UrlMappings.LOGGINN_URL);
		}
	}

}
