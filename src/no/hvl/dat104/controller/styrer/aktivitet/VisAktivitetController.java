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
		
		String aktivitetsId = request.getParameter("aktivitetId");
		int id = Integer.parseInt(aktivitetsId);
		
		Aktivitet a = aktivitetEao.finnAktivitet(id);
		List<Event> readonlyEventListe = aktivitetEao.finnAlleEventerTilAktivitet(a.getId());
		List<Event> eventListe = new ArrayList<Event>(); 
		eventListe.addAll(readonlyEventListe);
		//sorter
		Collections.sort(eventListe, new Comparator<Event>() {
		    public int compare(Event e1, Event e2) {
		        return e1.getTidFra().compareTo(e2.getTidFra());
		    }
		});
		
		//kapasitet for dataListe
		int count = eventListe.size();
		
		//Her blir dataen lagt inn
		String[][] dataListe = new String[count][4];
		
		//Genererer test data -begin
		Random rand = new Random();
		int i = 0;
		for(Event e : eventListe) {
			dataListe[i][0] = e.getNavn();
			dataListe[i][1] = String.valueOf(rand.nextInt(10));
			dataListe[i][2] = String.valueOf(rand.nextInt(10));
			dataListe[i][3] = String.valueOf(rand.nextInt(10));
			i++;
		}
		
		request.getSession().setAttribute("aktivitetsNavn", a.getNavn());
		request.setAttribute("arrayMedTilbakemeldinger", dataListe);
		request.getRequestDispatcher(JspMappings.VISAKTIVITET_JSP).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect(UrlMappings.VISAKTIVITET_URL);
	}

}