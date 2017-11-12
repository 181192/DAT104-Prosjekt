package no.hvl.dat104.controller.styrer.aktivitet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hvl.dat104.controller.JspMappings;
import no.hvl.dat104.controller.UrlMappings;
import no.hvl.dat104.dataaccess.IAktivitetEAO;
import no.hvl.dat104.dataaccess.IEventEAO;
import no.hvl.dat104.dataaccess.ITilbakemeldingEAO;
import no.hvl.dat104.model.Aktivitet;
import no.hvl.dat104.model.Event;
import no.hvl.dat104.model.Tilbakemelding;
import no.hvl.dat104.util.AktivitetTilbakemelding;
import no.hvl.dat104.util.FormaterTilbakemeldingUtil;
import no.hvl.dat104.util.InnloggingUtil;

/**
 * Servlet implementation class VisAktivitetController
 */
public class VisAktivitetController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private IEventEAO eventEao;
	@EJB
	private IAktivitetEAO aktivitetEao;
	@EJB
	private ITilbakemeldingEAO tilbakemeldingEao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (InnloggingUtil.erInnloggetSomBruker(request)) {
			String aktivitetsId = request.getParameter("aktivitetId");
			int id = Integer.parseInt(aktivitetsId);
			
			FormaterTilbakemeldingUtil format = new FormaterTilbakemeldingUtil();
			Aktivitet a = aktivitetEao.finnAktivitet(id);
			List<Event> readonlyEventListe = aktivitetEao.finnAlleEventerTilAktivitet(a.getId());
			List<Event> eventListe = new ArrayList<Event>();
			eventListe.addAll(readonlyEventListe);
			// sorter
			Collections.sort(eventListe, new Comparator<Event>() {
				public int compare(Event e1, Event e2) {
					return e1.getTidFra().compareTo(e2.getTidFra());
				}
			});
			List<AktivitetTilbakemelding> aktivitetsVisning = new ArrayList<AktivitetTilbakemelding>(); 
			
			for(Event e:eventListe){
				List<Tilbakemelding> tb = eventEao.finnAlleTilbakemeldingerTilEvent(e.getId());
				aktivitetsVisning.add(format.formaterTilbakemeldingerForAktivitetsResultatVisning(e.getNavn(), tb));
			}
			request.getSession().setAttribute("aktivitetsNavn", a.getNavn());
			request.setAttribute("arrayMedTilbakemeldinger", aktivitetsVisning);
			request.getRequestDispatcher(JspMappings.VISAKTIVITET_JSP).forward(request, response);
		} else {
			response.sendRedirect(UrlMappings.LOGGINN_URL);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (InnloggingUtil.erInnloggetSomBruker(request)) {
			response.sendRedirect(UrlMappings.VISAKTIVITET_URL);
		} else {
			response.sendRedirect(UrlMappings.LOGGINN_URL);
		}
	}

}