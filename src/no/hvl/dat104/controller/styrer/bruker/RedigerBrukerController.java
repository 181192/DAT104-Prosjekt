package no.hvl.dat104.controller.styrer.bruker;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hvl.dat104.controller.Attributter;
import no.hvl.dat104.controller.JspMappings;
import no.hvl.dat104.controller.UrlMappings;
import no.hvl.dat104.dataaccess.IBrukerEAO;
import no.hvl.dat104.model.Bruker;
import no.hvl.dat104.util.FlashUtil;
import no.hvl.dat104.util.InnloggingUtil;

/**
 * Servlet implementation class RedigerBrukerController
 */

public class RedigerBrukerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private IBrukerEAO brukerEAO;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Bruker b = (Bruker) request.getSession().getAttribute(Attributter.BRUKER);
		if (InnloggingUtil.erInnloggetSomBruker(request)) {
			if (b != null) {
				request.getSession().setAttribute(Attributter.BRUKER, b);
				request.getRequestDispatcher(JspMappings.REDIGERBRUKER_JSP).forward(request, response);
			} else {
				response.sendRedirect(UrlMappings.LANDING_STYRER_URL);
			}
		} else {
			response.sendRedirect(UrlMappings.LOGGINN_URL);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (InnloggingUtil.erInnloggetSomBruker(request)) {
			BrukerValidator skjema = new BrukerValidator(request);
			Bruker b = (Bruker) request.getSession().getAttribute(Attributter.BRUKER);

			if (skjema.erAlleDataGyldig()) {
				if (b != null) {
					Integer id = b.getId();

					brukerEAO.endreEtternavnPaaBruker(id, skjema.getEtternavn());
					brukerEAO.endreFornavnPaaBruker(id, skjema.getFornavn());
					brukerEAO.endreMailPaaBruker(id, skjema.getMail());
					brukerEAO.endrePassordPaaBruker(id, skjema.getPassord());

					FlashUtil.Flash(request, "success",
							"Brukeren " + b.getFornavn() + " " + b.getEtternavn() + " er oppdatert!");
					response.sendRedirect(UrlMappings.LANDING_STYRER_URL);
				} else {
					FlashUtil.Flash(request, "error", "Beklager, noe gikk galt");
					response.sendRedirect(UrlMappings.REDIGERBRUKER_URL);
				}
			} else {
				skjema.settOppFeilmeldinger(request);
				response.sendRedirect(UrlMappings.REDIGERBRUKER_URL);
			}
		} else {
			response.sendRedirect(UrlMappings.LOGGINN_URL);
		}

	}

}
