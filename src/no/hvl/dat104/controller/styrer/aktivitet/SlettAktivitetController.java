package no.hvl.dat104.controller.styrer.aktivitet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hvl.dat104.controller.UrlMappings;
import no.hvl.dat104.dataaccess.IAktivitetEAO;
import no.hvl.dat104.model.Aktivitet;
import no.hvl.dat104.util.FlashUtil;
import no.hvl.dat104.util.InnloggingUtil;
import no.hvl.dat104.util.ValidatorUtil;

/**
 * Servlet implementation class SlettAktivitetController
 */
public class SlettAktivitetController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private IAktivitetEAO aktivitetEAO;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (InnloggingUtil.erInnloggetSomBruker(request)) {
			String aktivitetId = ValidatorUtil.escapeHtml(request.getParameter("aktivitetId"));
			System.out.println("aktivitetId " + aktivitetId);

			if (ValidatorUtil.isNotNull0(aktivitetId)) {
				Integer id = Integer.parseInt(aktivitetId);
				Aktivitet a = aktivitetEAO.finnAktivitet(id);
				if (a != null) {
					System.out.println(a.getNavn());
					aktivitetEAO.slettAktivitet(a);
					FlashUtil.Flash(request, "success", "Aktiviteten " + a.getNavn() + " er slettet!");
					response.sendRedirect(UrlMappings.MINEAKTIVITETER_URL);
				} else {
					System.out.println("den er tom");
					FlashUtil.Flash(request, "error", "Beklager, noe gikk galt");
					response.sendRedirect(UrlMappings.MINEAKTIVITETER_URL);
				}
			} else {
				FlashUtil.Flash(request, "error", "Beklager, noe gikk galt");
				response.sendRedirect(UrlMappings.MINEAKTIVITETER_URL);
			}
		} else {
			response.sendRedirect(UrlMappings.LOGGINN_URL);
		}
	}
}
