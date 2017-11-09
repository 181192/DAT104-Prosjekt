package no.hvl.dat104.controller.styrer.event;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import no.hvl.dat104.controller.Attributter;
import no.hvl.dat104.controller.JspMappings;
import no.hvl.dat104.controller.UrlMappings;
import no.hvl.dat104.dataaccess.IAktivitetEAO;
import no.hvl.dat104.dataaccess.IBrukerEAO;
import no.hvl.dat104.dataaccess.IEventEAO;
import no.hvl.dat104.model.Aktivitet;
import no.hvl.dat104.model.Bruker;
import no.hvl.dat104.model.Event;
import no.hvl.dat104.model.LiveTilbakemelding;
import no.hvl.dat104.model.Status;
import no.hvl.dat104.util.InnloggingUtil;

/**
 * Servlet implementation class PreEventController
 */
public class PreEventController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	IEventEAO eventEAO;
	
	@EJB
	IBrukerEAO brukerEAO;
	
	
	@EJB
	IAktivitetEAO aktivitetEAO;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (InnloggingUtil.erInnloggetSomBruker(request)) {

			// Skal her teste for at bruker har en session.
			HttpSession session = request.getSession(false);
			
			Bruker br = (Bruker) session.getAttribute(Attributter.BRUKER);
			
			List<Aktivitet> akt = brukerEAO.finnAlleAktiviteterTilBruker(br.getId());
			List<Event> eventer = new ArrayList<>();
			
			for( Aktivitet a : akt) {
				List<Event> temp = aktivitetEAO.finnAlleEventerTilAktivitet(a.getId());
				for(Event ev : temp) {
					ev.setStatus(Status.PLANLAGT);
					//Legger til alle eventer som ikke er avsluttet. 
					if(!ev.getStatus().equals(Status.AVSLUTTET)) {
						eventer.add(ev);
					}
				}
			}
			
			
			System.out.println("Skal være davids eventer: " + eventer.toString());
			
			

			// Må finne eventet som skal startes på en eller annen måte.
			Event ev = eventEAO.finnEvent(1);

			ev.setStatus(Status.PLANLAGT);

			eventEAO.endreStatusPaaEvent(ev.getId(), Status.PLANLAGT);

			System.out.println("eventStatus: " + ev.getStatus());

			session.setAttribute(Attributter.LIVE_EVENT, ev);
			String denneStatus = ev.getStatus();
			// Hvis eventet er planlagt, skal viser vi PreEventJSP.
			if (denneStatus.equals(Status.PLANLAGT)) {

				request.getRequestDispatcher(JspMappings.PRE_EVENT_JSP).forward(request, response);

			} else if (denneStatus.equals(Status.PAAGANDE)) {

				request.getRequestDispatcher(JspMappings.LIVE_EVENT_JSP).forward(request, response);

			} else {

				System.out.println("Status til eventet er satt til Avsluttet, må implementere en JSP for dette.");

			}
		} else {
			response.sendRedirect(UrlMappings.LOGGINN_URL);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (InnloggingUtil.erInnloggetSomBruker(request)) {

			// Trenger session for å finne riktig event.
			HttpSession session = request.getSession(false);

			Event ev = (Event) session.getAttribute(Attributter.LIVE_EVENT);
			System.out.println("liveEvent: " + ev.getId());

			// Trenger test for rett knapp/loggin/osv/bruke hidden?
			// Startknappen trykkes: lager liste, setter status til pågående.
			if (true) {
				List<LiveTilbakemelding> liveTilbakemeldinger = new ArrayList<>();
				System.out.println("liveTilbakemeldinger.lengde: " + liveTilbakemeldinger.size());
				ev.setLiveTilbakemeldinger(liveTilbakemeldinger);
				ev.setStatus(Status.PAAGANDE);

				session.setAttribute(Attributter.LIVE_EVENT, ev);
				response.sendRedirect(UrlMappings.LIVE_EVENT_URL);
			}
		} else {
			response.sendRedirect(UrlMappings.LOGGINN_URL);
		}

	}

}
