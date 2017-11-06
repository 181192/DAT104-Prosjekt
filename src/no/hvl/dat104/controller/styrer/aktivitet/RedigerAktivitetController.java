package no.hvl.dat104.controller.styrer.aktivitet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import no.hvl.dat104.controller.JspMappings;
import no.hvl.dat104.controller.UrlMappings;
import no.hvl.dat104.dataaccess.IAktivitetEAO;
import no.hvl.dat104.model.Aktivitet;
import no.hvl.dat104.util.FlashUtil;
import no.hvl.dat104.util.ValidatorUtil;

/**
 * Servlet implementation class RedigerAktivitetController
 */
public class RedigerAktivitetController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private IAktivitetEAO aktivitetEAO;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String aktivitetsId = request.getParameter("aktivitetId");
		if (ValidatorUtil.isNotNull0(aktivitetsId)) {
			Integer id = Integer.parseInt(aktivitetsId);
			Aktivitet a = aktivitetEAO.finnAktivitet(id);
			if (a != null) {
				HttpSession mySession = request.getSession();
				mySession.setAttribute("aktivitet", a);
				request.getRequestDispatcher(JspMappings.REDIGERAKTIVITET_JSP).forward(request, response);
			} else {
				response.sendRedirect(UrlMappings.MINEAKTIVITETER_URL);
				FlashUtil.Flash(request, "error", "Beklager, aktiviteten eksisterer ikke");
			}
		} else {
			response.sendRedirect(UrlMappings.MINEAKTIVITETER_URL);
			FlashUtil.Flash(request, "error", "Beklager, aktiviteten eksisterer ikke");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Sende inn redigert aktivitet og lagre i databasen
		String navn = ValidatorUtil.escapeHtml(request.getParameter("navn"));
		String status = ValidatorUtil.escapeHtml(request.getParameter("status"));
		String aktivitetId = ValidatorUtil.escapeHtml(request.getParameter("id"));

		if (ValidatorUtil.isNotNull0(aktivitetId)) {
			Integer id = Integer.parseInt(aktivitetId);
			Aktivitet a = aktivitetEAO.finnAktivitet(id);
			if (a != null && ValidatorUtil.isNotNull0(navn) && ValidatorUtil.isNotNull0(status)) {
				aktivitetEAO.endreNavnPaaAktivitet(id, navn);
				aktivitetEAO.endreStatusPaaAktivitet(id, status);
				FlashUtil.Flash(request, "success", "Aktiviteten " + a.getNavn() + " er oppdatert!");
				response.sendRedirect(UrlMappings.MINEAKTIVITETER_URL);
			} else {
				FlashUtil.Flash(request, "error", "Beklager, noe gikk galt");
				response.sendRedirect(UrlMappings.REDIGERAKTIVITET_URL + "?aktivitetId=" + a.getId());
			}
		} else {
			response.sendRedirect(UrlMappings.MINEAKTIVITETER_URL);
			FlashUtil.Flash(request, "error", "Beklager, noe gikk galt");
		}

	}

}
