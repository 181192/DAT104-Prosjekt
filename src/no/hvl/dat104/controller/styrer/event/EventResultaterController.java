package no.hvl.dat104.controller.styrer.event;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hvl.dat104.controller.Attributter;
import no.hvl.dat104.controller.JspMappings;
import no.hvl.dat104.controller.UrlMappings;
import no.hvl.dat104.dataaccess.IEventEAO;
import no.hvl.dat104.dataaccess.IKodeordEAO;
import no.hvl.dat104.model.Event;
import no.hvl.dat104.model.Kodeord;
import no.hvl.dat104.model.LiveTilbakemelding;
import no.hvl.dat104.model.Status;
import no.hvl.dat104.model.Tilbakemelding;
import no.hvl.dat104.util.FlashUtil;
import no.hvl.dat104.util.FormaterTilbakemeldingUtil;
import no.hvl.dat104.util.FormatertTilbakemelding;
import no.hvl.dat104.util.InnloggingUtil;

/**
 * Servlet implementation class EventResultaterController
 */
public class EventResultaterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private IEventEAO eventEAO;
	@EJB
	private IKodeordEAO kodeEAO;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (InnloggingUtil.erInnloggetSomBruker(request)) {
			// Får tak i event id
			Integer id = Integer.parseInt(request.getParameter("eventId"));
			Event e = null;
			e = eventEAO.finnEvent(id);
			// Hvis eventen er avsluttet:
			if (e.getStatus().equals(Status.AVSLUTTET)) {
				List<Tilbakemelding> t = null;
				t = eventEAO.finnAlleTilbakemeldingerTilEvent(id);

				// Får tak i liste med tilbakemeldinger for eventet, deretter konverterer den
				// til et format som kan brukes i grafene
				List<FormatertTilbakemelding> formaterteTilbakemeldinger = null;
				if (!t.isEmpty()) {
					formaterteTilbakemeldinger = FormaterTilbakemeldingUtil.formaterTilbakemeldinger(t);
				}
				// Attributter får verdiene sine tilsendt
				request.setAttribute("aktivitet", e.getIdAktivitet());
				request.setAttribute("event", e);
				request.setAttribute("formaterteTilbakemeldinger", formaterteTilbakemeldinger);
				request.getRequestDispatcher(JspMappings.EVENTRESULTATER_JSP).forward(request, response);
			} else if (e.getStatus().equals(Status.PAAGANDE)) {// Eventen pågår, bytter visning til live-visning, henter
																// også live tilbakemleding listen
				
				List<LiveTilbakemelding> lt = eventEAO.finnAlleLiveTilbakemeldingerTilEvent(id);
				List<FormatertTilbakemelding> formartertLt = null;
				if(lt != null) {
					formartertLt = formaterLiveTilbakemeldinger(lt);
				}
				Kodeord kodeord = kodeEAO.finnKodeordTilEvent(e);
				request.getSession().setAttribute(Attributter.KODEORD, kodeord);
				request.setAttribute("liveTilbakemeldinger", formartertLt);
				request.setAttribute("aktivitet", e.getIdAktivitet());
				request.setAttribute("event", e);
				request.getRequestDispatcher(JspMappings.LIVE_EVENT_JSP).forward(request, response);
			} else { // Eventen er planlagt, ingen tilbakemeldinger å vise.
				request.setAttribute("aktivitet", e.getIdAktivitet());
				request.setAttribute("event", e);
				request.getRequestDispatcher(JspMappings.EVENTRESULTATER_JSP).forward(request, response);
			}
		} else {
			FlashUtil.Flash(request, "error", "Du må logge inn for å se resultatene for eventet ditt!");
			response.sendRedirect(UrlMappings.LOGGINN_URL);
		}
	}
	
	private List<FormatertTilbakemelding> formaterLiveTilbakemeldinger(List<LiveTilbakemelding> liveTb){
		List<LiveTilbakemelding> l = liveTb;
		List<Tilbakemelding> konverter = new ArrayList<Tilbakemelding>();
		List<FormatertTilbakemelding> formatert;
		
		for(LiveTilbakemelding el: l) {
			konverter.add(new Tilbakemelding(el.getStemme(), el.getIdEvent(), el.getTid()));
		}
		formatert = FormaterTilbakemeldingUtil.formaterTilbakemeldinger(konverter);
		return formatert;
	}
}
