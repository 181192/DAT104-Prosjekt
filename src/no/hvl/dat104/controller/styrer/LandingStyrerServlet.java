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
import no.hvl.dat104.model.Aktivitet;

/**
 * Indexsiden til brukeren
 */

public class LandingStyrerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private IAktivitetEAO iAktivitetEAO;
	@EJB
    private IBrukerEAO iBrukerEAO;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Aktivitet> a = iAktivitetEAO.finnAktiviteterTilBruker(iBrukerEAO.finnBruker(2));
		for(Aktivitet k : a) {
			System.out.println(k.getNavn());
		}
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
