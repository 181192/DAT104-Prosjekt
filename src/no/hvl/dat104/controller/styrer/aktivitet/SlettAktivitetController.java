package no.hvl.dat104.controller.styrer.aktivitet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hvl.dat104.dataaccess.IAktivitetEAO;
import no.hvl.dat104.model.Aktivitet;
import no.hvl.dat104.util.ValidatorUtil;

/**
 * Servlet implementation class SlettAktivitetController
 */
@WebServlet("/SlettAktivitetController")
public class SlettAktivitetController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private IAktivitetEAO aktivitetEAO;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String aktivitetId = ValidatorUtil.escapeHtml(request.getParameter("id"));

		if (ValidatorUtil.isNotNull0(aktivitetId)) {
			Integer id = Integer.parseInt(aktivitetId);
			Aktivitet a = aktivitetEAO.finnAktivitet(id);
			if (a != null) {
				aktivitetEAO.slettAktivitet(a);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
