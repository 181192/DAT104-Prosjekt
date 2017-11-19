package no.hvl.dat104.controller.styrer;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hvl.dat104.controller.JspMappings;
import no.hvl.dat104.controller.Meldinger;
import no.hvl.dat104.controller.UrlMappings;
import no.hvl.dat104.dataaccess.IAktivitetEAO;
import no.hvl.dat104.dataaccess.IBrukerEAO;
import no.hvl.dat104.dataaccess.IEventEAO;
import no.hvl.dat104.model.Aktivitet;
import no.hvl.dat104.model.Bruker;
import no.hvl.dat104.model.Event;
import no.hvl.dat104.model.Status;
import no.hvl.dat104.util.FlashUtil;
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
		Bruker bruker = InnloggingUtil.innloggetSomBruker(request);
		if (bruker != null ) {
			List<Aktivitet> a = brukerEAO.alleAktiviteterIJPQL(bruker.getId());
			List<Event> alleEventer = brukerEAO.finnAlleEventerTilBruker(bruker.getId());
			setOppFarger(request);
			request.getSession().setAttribute("alleEventer", alleEventer);
			request.getSession().setAttribute("aktiviteter", a);
			request.getSession().setAttribute("paagandeEvent", sjekkPaagandeEventer(alleEventer));
			settOppFlash(request);
			request.getRequestDispatcher(JspMappings.LANDING_STYRER_JSP).forward(request, response);
		} else {
			FlashUtil.Flash(request, "error", Meldinger.UGYLDIG_MELD);
			response.sendRedirect(UrlMappings.LOGGINN_URL);
		}
	}
	private void settOppFlash(HttpServletRequest request) {
		String newcommer = (String) request.getSession().getAttribute("nuub");
		if(newcommer != null) {
			FlashUtil.Flash(request, "success", Meldinger.OPPRETT_BRUKER_MELD);
		}
		request.getSession().removeAttribute("nuub");
	}
	private void setOppFarger(HttpServletRequest request) {
		String[] farger = { "orange", "green", "red", "blue", "yellow", "purple", "teal", "cyan", "magenta", "brown", "black", "white" };
		request.getSession().setAttribute("color", farger);
	}
	/**
	 * Sjekker om eventene har et event som har tidFra før nåværende tid, og tidTil etter nåværende tid, altså om et event har "startet".
	 * @param eventer
	 * @return
	 */
	private Event sjekkPaagandeEventer(List<Event> eventer) {
		Event paagandeEvent = null;
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		for(Event e:eventer) {
			if(!e.getStatus().equals(Status.AVSLUTTET)) { //
				if(currentTime.after(e.getTidFra()) && currentTime.before(e.getTidTil())) {
					paagandeEvent = e;
					break; //Første event som er innenfor tidsrommet blir returnert, hvis det er andre som pågår samtidig blir de ignorert.
				}
			}
		}
		return paagandeEvent;
	}

}
