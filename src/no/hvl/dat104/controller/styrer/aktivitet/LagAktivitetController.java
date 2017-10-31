package no.hvl.dat104.controller.styrer.aktivitet;

import java.io.IOException;
import java.util.Iterator;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hvl.dat104.controller.UrlMappings;
import no.hvl.dat104.dataaccess.IBrukerEAO;
import no.hvl.dat104.model.Aktivitet;
import no.hvl.dat104.model.Bruker;
import no.hvl.dat104.util.ValidatorUtil;

/**
 * Servlet implementation class LagAktivitetController
 */
public class LagAktivitetController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private IBrukerEAO brukerEAO;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Bruker b = brukerEAO.finnBruker(2);
		Iterator<Aktivitet> i = b.getAktiviteter().iterator();
		while(i.hasNext()) {
			System.out.println(i.next());
		}
		request.getRequestDispatcher("WEB-INF/views/styrer/lagaktivitet.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String tittel = request.getParameter("tittel");
		if (ValidatorUtil.isNotNull0(tittel)) {
			Bruker b = brukerEAO.finnBruker(2);
			Aktivitet a = new Aktivitet();
			a.setNavn(tittel);
			b.getAktiviteter().add(a);
			brukerEAO.oppdaterBruker(b);
			response.sendRedirect(UrlMappings.MINEAKTIVITETER_URL);
		}
	}

	public Aktivitet lagAktivitet(HttpServletRequest request, AktivitetValidator skjema) {
		Aktivitet a = new Aktivitet();
		Bruker bruker = brukerEAO.finnBruker(2);
		a.setNavn("");
		return a;
	}
}