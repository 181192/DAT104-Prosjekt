package no.hvl.dat104.controller.styrer;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		List<Event> eventListe = eventEao.finnAlleEventerTilAktivitet(a);
		
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
		request.getRequestDispatcher("WEB-INF/views/styrer/visaktivitet.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect(UrlMappings.VISAKTIVITET_URL);
	}

}