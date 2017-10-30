package no.hvl.dat104.controller.styrer;

import java.io.IOException;

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
		request.getRequestDispatcher("WEB-INF/views/styrer/lagaktivitet.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (ValidatorUtil.isNotNull0((String) request.getAttribute("tittel"))) {
			request.getSession().removeAttribute("tittel");
			response.sendRedirect(UrlMappings.VISAKTIVITET_URL);
		}
	}
	
	public Aktivitet lagAktivitet(HttpServletRequest request, AktivitetValidator skjema) {
		Aktivitet a = new Aktivitet();
		Bruker bruker = brukerEAO.finnBruker(2);
		a.setNavn("");
		return a;
	}
}