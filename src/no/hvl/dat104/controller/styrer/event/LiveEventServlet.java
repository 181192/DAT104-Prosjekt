package no.hvl.dat104.controller.styrer.event;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import no.hvl.dat104.controller.Attributter;
import no.hvl.dat104.controller.JspMappings;
import no.hvl.dat104.controller.UrlMappings;
import no.hvl.dat104.dataaccess.IEventEAO;
import no.hvl.dat104.dataaccess.IKodeordEAO;
import no.hvl.dat104.model.Event;
import no.hvl.dat104.model.Kodeord;
import no.hvl.dat104.model.LiveTilbakemelding;
import no.hvl.dat104.model.Tilbakemelding;
import no.hvl.dat104.util.FormaterTilbakemeldingUtil;
import no.hvl.dat104.util.FormatertTilbakemelding;
import no.hvl.dat104.util.InnloggingUtil;

/**
 * Servlet implementation class LiveEventServlet
 */
public class LiveEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private IEventEAO eventEAO;

	@EJB
	private IKodeordEAO kodeordEAO;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setIntHeader("Refresh", 5);

		if (InnloggingUtil.erInnloggetSomBruker(request)) {

			HttpSession session = request.getSession(false);
			Integer eventId = null;
			Event detteEvent = null;
			// For at servletten ikke skal hente fra databasen hver gang sjekkes om eventen
			// er i request attributten
			if (!request.getParameter("liveeventid").isEmpty()) {
				eventId = Integer.valueOf(request.getParameter("liveeventid"));
				try {
					detteEvent = eventEAO.finnEvent(eventId);
				} catch (Exception e) {
					e.printStackTrace();
					doGet(request, response);
				}
			} else {
				detteEvent = (Event) request.getAttribute("eventsend");
				eventId = detteEvent.getId();
			}
			FormaterTilbakemeldingUtil format = new FormaterTilbakemeldingUtil();
			if (detteEvent != null) {
				if (detteEvent.getKodeord() == null) {
					Boolean unik = false;
					Kodeord kodeord = new Kodeord();
					kodeord.setIdEvent(detteEvent);
					Random rand = new Random();
					while (!unik) {
						kodeord.setKode(rand.nextInt((99999 - 10000) - 1) + 10000);
						if (kodeordEAO.sjekkOmKodeordErUnik(kodeord)) {
							unik = true;
						}
					}
					kodeordEAO.leggTilKodeord(kodeord);
					request.setAttribute("koden", kodeord);
				} else {
					// Hente livetilbakemeldinger og kodeordet her
					Kodeord kode = null;
					try {
						kode = kodeordEAO.finnKodeordTilEvent(detteEvent);
					} catch (Exception e) {
						e.printStackTrace();
						doGet(request, response);
					}
					List<FormatertTilbakemelding> formaterteLiveTilbakemeldinger = null;
					List<LiveTilbakemelding> liveTilbakemeldingListe = null;
					try {
						liveTilbakemeldingListe = eventEAO.finnAlleLiveTilbakemeldingerTilEvent(detteEvent.getId());
					} catch (Exception e) {
						e.printStackTrace();
						doGet(request, response);
					}
					

					// Sjekker om null eller tom.
					if (liveTilbakemeldingListe != null) {
						if (!liveTilbakemeldingListe.isEmpty()) {
							List<LiveTilbakemelding> l = liveTilbakemeldingListe;
							List<Tilbakemelding> konverter = new ArrayList<Tilbakemelding>();

							for (LiveTilbakemelding el : l) {
								konverter.add(new Tilbakemelding(el.getStemme(), el.getIdEvent(), el.getTid()));
							}
							formaterteLiveTilbakemeldinger = format.formaterTilbakemeldinger(konverter);
						}
					}
					session.setAttribute("koden", kode);
					session.removeAttribute("liveTilbakemeldinger");
					session.setAttribute("liveTilbakemeldinger", formaterteLiveTilbakemeldinger);
				}
			}
			request.setAttribute("eventsend", detteEvent);
			request.getRequestDispatcher(JspMappings.LIVE_EVENT_JSP).forward(request, response);
		} else {
			response.sendRedirect(UrlMappings.LOGGINN_URL);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (InnloggingUtil.erInnloggetSomBruker(request)) {

			// Må ha test for innlogging og gyldig session her.
			String knappTrykket = (String) request.getParameter(Attributter.LIVE_EVENT_KNAPP);
			Integer eventId = Integer.valueOf(request.getParameter("eventId"));

			// Forleng-knapp
			Boolean test = false;

			if (test) {

			}

			if (knappTrykket.equals("avslutt")) {
				eventEAO.endreEventTilAvsluttet(eventId);
				response.sendRedirect(UrlMappings.POST_LIVE_EVENT_URL);

			}
		} else {
			response.sendRedirect(UrlMappings.LOGGINN_URL);
		}
	}
}
