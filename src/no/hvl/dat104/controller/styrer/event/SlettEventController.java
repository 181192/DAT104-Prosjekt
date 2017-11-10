package no.hvl.dat104.controller.styrer.event;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hvl.dat104.controller.UrlMappings;
import no.hvl.dat104.dataaccess.IEventEAO;
import no.hvl.dat104.model.Event;
import no.hvl.dat104.util.FlashUtil;
import no.hvl.dat104.util.InnloggingUtil;
import no.hvl.dat104.util.ValidatorUtil;

/**
 * Servlet implementation class SlettEventController
 */
public class SlettEventController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private IEventEAO eventEAO;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (InnloggingUtil.erInnloggetSomBruker(request)) {
			String eventId = ValidatorUtil.escapeHtml(request.getParameter("eventId"));
			if (ValidatorUtil.isNotNull0(eventId)) {
				Integer id = Integer.parseInt(eventId);
				Event e = eventEAO.finnEvent(id);
				if (e != null) {
					eventEAO.slettEvent(e);
					FlashUtil.Flash(request, "success", "Eventet " + e.getNavn() + " er slettet!");
					response.sendRedirect(UrlMappings.LANDING_STYRER_URL);
				} else {
					FlashUtil.Flash(request, "error", "Beklager, noe gikk galt");
					response.sendRedirect(UrlMappings.LANDING_STYRER_URL);
				}
			} else {
				FlashUtil.Flash(request, "error", "Beklager, noe gikk galt");
				response.sendRedirect(UrlMappings.LANDING_STYRER_URL);
			}
		} else {
			response.sendRedirect(UrlMappings.LOGGINN_URL);
		}

	}
}
