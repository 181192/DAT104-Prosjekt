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
import no.hvl.dat104.model.Bruker;
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
		Bruker bruker = iBrukerEAO.finnBruker(2);
		List<Aktivitet> a = iAktivitetEAO.finnAktiviteterTilBruker(bruker);
		List<Event> alleEventer = null;
		bruker.setAktiviteter(a);
		String[] farger = {"green","orange","red","blue","yellow"};
		for(int i = 0; i < a.size(); i ++) { 
			alleEventer = iEventEAO.finnAlleEventerTilAktivitet(a.get(i));
			a.get(i).setEventer(alleEventer);
			a.get(i).getId().toString();
		}
		request.getSession().setAttribute("alleEventer", alleEventer);
		request.getSession().setAttribute("aktiviteter", a);
		request.getSession().setAttribute("color", farger);
		request.getRequestDispatcher("WEB-INF/views/styrer/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	public void hentOgSetEventer(Aktivitet a) {
		List<Event> e = iEventEAO.finnAlleEventerTilAktivitet(a);
		a.setEventer(e);
	}

}
