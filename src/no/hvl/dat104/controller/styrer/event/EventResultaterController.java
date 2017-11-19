package no.hvl.dat104.controller.styrer.event;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hvl.dat104.controller.JspMappings;
import no.hvl.dat104.controller.UrlMappings;
import no.hvl.dat104.dataaccess.IEventEAO;
import no.hvl.dat104.dataaccess.IKodeordEAO;
import no.hvl.dat104.model.Event;
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
			FormaterTilbakemeldingUtil format = new FormaterTilbakemeldingUtil();
			// Hvis eventen er avsluttet:
			if (e.getStatus().equals(Status.AVSLUTTET)) {
				List<Tilbakemelding> t = null;
				t = eventEAO.finnAlleTilbakemeldingerTilEvent(id);
				

				// Får tak i liste med tilbakemeldinger for eventet, deretter konverterer den
				// til et format som kan brukes i grafene
				List<FormatertTilbakemelding> formaterteTilbakemeldinger = null;
				if (!t.isEmpty()) {
					formaterteTilbakemeldinger = format.formaterTilbakemeldinger(t);
				}
				// Attributter får verdiene sine tilsendt
				request.setAttribute("aktivitet", e.getIdAktivitet());
				request.setAttribute("event", e);
				request.setAttribute("formaterteTilbakemeldinger", formaterteTilbakemeldinger);
				request.getRequestDispatcher(JspMappings.EVENTRESULTATER_JSP).forward(request, response);
			} else if (e.getStatus().equals(Status.PAAGANDE)) {// Eventen pågår, gir feilmelding
				FlashUtil.Flash(request, "error", "Eventen har ingen resultater, resultatene vil komme når eventet er ferdig.");
				request.getRequestDispatcher(JspMappings.LANDING_STYRER_JSP).forward(request, response);
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
}
