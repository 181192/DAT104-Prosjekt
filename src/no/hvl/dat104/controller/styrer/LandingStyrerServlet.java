package no.hvl.dat104.controller.styrer;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hvl.dat104.dataaccess.IAktivitetEAO;
import no.hvl.dat104.dataaccess.IBrukerEAO;
import no.hvl.dat104.dataaccess.IEventEAO;
import no.hvl.dat104.model.Aktivitet;
import no.hvl.dat104.model.Event;

/**
 * Indexsiden til brukeren
 */

public class LandingStyrerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private IAktivitetEAO iAktivitetEAO;
	@EJB
    private IBrukerEAO iBrukerEAO;
	@EJB
    private IEventEAO iEventEAO;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Aktivitet> a = iAktivitetEAO.finnAktiviteterTilBruker(iBrukerEAO.finnBruker(2));
		List<Event> e = iEventEAO.finnAlleEventerTilAktivitet(a.get(0));
		System.out.println("Aktivitet: " + a.get(0).getId() +" " + a.get(0).getNavn() + " Event: " + e.get(0).getNavn());
		System.out.println();
		System.out.println("Aktivitet: " + a.get(0).getEventer().size());
		request.getRequestDispatcher("WEB-INF/views/styrer/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
