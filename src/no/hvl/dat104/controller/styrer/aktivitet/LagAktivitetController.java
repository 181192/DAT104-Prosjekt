package no.hvl.dat104.controller.styrer.aktivitet;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hvl.dat104.controller.JspMappings;
import no.hvl.dat104.controller.UrlMappings;
import no.hvl.dat104.dataaccess.IAktivitetEAO;
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
	@EJB
	private IAktivitetEAO aktivitetEAO;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher(JspMappings.LAGAKTIVITET_JSP).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String tittel = request.getParameter("tittel");
		String status = request.getParameter("status");
		if (ValidatorUtil.isNotNull0(tittel)) {
			Bruker b = brukerEAO.finnBruker(2);
			Aktivitet a = new Aktivitet();
			a.setNavn(tittel);
			a.setStatus(status);
			a.setIdBruker(b);
			List<Aktivitet> aktiviteter = brukerEAO.finnAlleAktiviteterTilBruker(b.getId());
			aktiviteter.add(a);
			aktivitetEAO.leggTilAktivitet(a);
			response.sendRedirect(UrlMappings.MINEAKTIVITETER_URL);
		}
	}
}