package no.hvl.dat104.controller.deltager;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import no.hvl.dat104.controller.JspMappings;
import no.hvl.dat104.controller.UrlMappings;
import no.hvl.dat104.dataaccess.IEventEAO;
import no.hvl.dat104.dataaccess.IKodeordEAO;
import no.hvl.dat104.model.Kodeord;

/**
 * Servlet implementation class DeltaEventController
 */
public class DeltaEventController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private IKodeordEAO kodeordEAO;
	@EJB
	private IEventEAO eventEAO;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/" + JspMappings.LANDING_JSP).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String kodeord = request.getParameter("kodeord");
		HttpSession sesjon = request.getSession(true);
		if (DeltaEventHjelpeklasse.riktigKodeordSyntaks(kodeord)) {
			Kodeord k = kodeordEAO.finnKodeordBasertPaaKode(Integer.parseInt(kodeord));
			if (k != null) {
				DeltaEventHjelpeklasse.lastEventTilSesjon(k, sesjon, eventEAO, request);
				response.sendRedirect(UrlMappings.GITILBAKEMELDING_URL);
			} else {
				sesjon.setAttribute("feilmelding", true);
				response.sendRedirect(UrlMappings.DELTAEVENT_URL);// index
			}

		} else {
			sesjon.setAttribute("feilmelding", true);
			response.sendRedirect(UrlMappings.DELTAEVENT_URL);// index
		}
	}

}
