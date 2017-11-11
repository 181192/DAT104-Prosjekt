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
import no.hvl.dat104.model.Status;
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
			Integer eventId = Integer.valueOf(request.getParameter("liveeventid"));
			Event detteEvent = eventEAO.finnEvent(eventId);

			if (detteEvent != null) {
				if (kodeordEAO.finnKodeordTilEvent(detteEvent) == null) {
					Kodeord kodeord = genererKodeord(detteEvent);
					kodeordEAO.leggTilKodeord(kodeord);
					request.setAttribute("koden", kodeord);
				} else {
					// Hente livetilbakemeldinger og kodeordet her
					Kodeord kode = kodeordEAO.finnKodeordTilEvent(detteEvent);
					List<FormatertTilbakemelding> formaterteLiveTilbakemeldinger = null;
					List<LiveTilbakemelding> liveTilbakemeldingListe = eventEAO.finnAlleLiveTilbakemeldingerTilEvent(detteEvent.getId());
					//Sjekker om null eller tom.
					if(liveTilbakemeldingListe != null) {
						if(!liveTilbakemeldingListe.isEmpty()) {
							formaterteLiveTilbakemeldinger = formaterLiveTilbakemeldinger(liveTilbakemeldingListe);
						}
					}
					session.setAttribute("koden", kode);
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
			HttpSession session = request.getSession(false);
			String knappTrykket = (String) request.getParameter(Attributter.LIVE_EVENT_KNAPP);
			Integer eventId = Integer.valueOf(request.getParameter("eventId"));

			// Forleng-knapp
			Boolean test = false;

			if (test) {
				System.out.println("forlengknapp");
			}

			if (knappTrykket.equals("avslutt")) {
				eventEAO.endreEventTilAvsluttet(eventId);
				response.sendRedirect(UrlMappings.POST_LIVE_EVENT_URL);

			}
			/**
			 * START-KNAPP
			 * 
			 * Faktisk tid for start må settes. Kodeord må genereres. Kodeord må settes i
			 * session. Status må edres til pagaende.
			 */

			/**
			 * STOPP-KNAPP
			 * 
			 * Faktisk tid for stop må settes. Kodeord skal slettes fra databasen. Status må
			 * endres til avsluttet.
			 * 
			 */
		} else {
			response.sendRedirect(UrlMappings.LOGGINN_URL);
		}
	}

	/**
	 * Tar en liste med liveTilbakemeldinger og formaterer dem til grafisk bruk.
	 * 
	 * @param liveTb
	 * @return
	 */
	private List<FormatertTilbakemelding> formaterLiveTilbakemeldinger(List<LiveTilbakemelding> liveTb) {
		List<LiveTilbakemelding> l = liveTb;
		List<Tilbakemelding> konverter = new ArrayList<Tilbakemelding>();
		List<FormatertTilbakemelding> formatert;

		for (LiveTilbakemelding el : l) {
			konverter.add(new Tilbakemelding(el.getStemme(), el.getIdEvent(), el.getTid()));
		}
		formatert = FormaterTilbakemeldingUtil.formaterTilbakemeldinger(konverter);
		return formatert;
	}

	/**
	 * Metode for aa generere et kodeord for eventet
	 * 
	 * @param event
	 * @return kodeord klassen
	 */
	private Kodeord genererKodeord(Event e) {
		Boolean unik = false;
		Kodeord kodeord = new Kodeord();
		kodeord.setIdEvent(e);
		Random rand = new Random();
		while (!unik) {
			kodeord.setKode(rand.nextInt((99999 - 10000) - 1) + 10000);
			if (kodeordEAO.sjekkOmKodeordErUnik(kodeord)) {
				unik = true;
			}
		}
		return kodeord;
	}
}
