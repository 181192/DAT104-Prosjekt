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
import no.hvl.dat104.model.Tilbakemelding;
import no.hvl.dat104.util.EventUtil;
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
					eventEAO.endreStatusPaaEvent(ev.getId(), Status.PLANLAGT);
					if(!ev.getStatus().equals(Status.AVSLUTTET)) {
						eventer.add(ev);
					}
				}
			}
			
			EventUtil.sorterEventer(eventer);
			request.setAttribute(Attributter.EVENT_LISTE, eventer);
			request.getRequestDispatcher(JspMappings.PRE_EVENT_JSP).forward(request, response);	

		} else {
			response.sendRedirect(UrlMappings.LOGGINN_URL);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (InnloggingUtil.erInnloggetSomBruker(request)) {
			
			HttpSession session = request.getSession(false);
			
			//Finner riktig event i databasen;
			String evId = (String)request.getParameter(Attributter.LIVE_EVENT_ID);
			Event ev = eventEAO.finnEvent(Integer.parseInt(evId));
			
			if(ev != null) {
				List<LiveTilbakemelding> liveTilbakemeldinger = new ArrayList<>();
				ev.setLiveTilbakemeldinger(liveTilbakemeldinger);
				ev.setStatus(Status.PAAGANDE);	
				eventEAO.oppdaterEvent(ev);
			}

			session.setAttribute(Attributter.LIVE_EVENT, ev);
			response.sendRedirect(UrlMappings.LIVE_EVENT_URL);
			
		} else {
			response.sendRedirect(UrlMappings.LOGGINN_URL);
		}

	}

}
