package no.hvl.dat104.controller.deltager;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hvl.dat104.controller.JspMappings;
import no.hvl.dat104.controller.UrlMappings;
import no.hvl.dat104.dataaccess.IEventEAO;
import no.hvl.dat104.dataaccess.IKodeordEAO;
import no.hvl.dat104.dataaccess.ILiveTilbakemeldingEAO;
import no.hvl.dat104.model.Event;
import no.hvl.dat104.model.Kodeord;
import no.hvl.dat104.model.LiveTilbakemelding;
import no.hvl.dat104.util.DatoUtil;
import no.hvl.dat104.util.FlashUtil;
import no.hvl.dat104.util.InnloggingUtil;

/**
 * Servlet implementation class DeltaEventController
 */
public class DeltaEventController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private IKodeordEAO kodeordEAO;
	@EJB
	private IEventEAO eventEAO;
	@EJB
	private ILiveTilbakemeldingEAO liveTilbakemeldingEAO;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/" + JspMappings.LANDING_JSP).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String kodeord = request.getParameter("kodeord");
		if (DeltaEventHjelpeklasse.riktigKodeordSyntaks(kodeord)) {
			List<Kodeord> k = kodeordEAO.finnKodeordBasertPaaKode(Integer.parseInt(kodeord));
			if (DeltaEventHjelpeklasse.kodeordFinnes(kodeordEAO.finnKodeordBasertPaaKode(Integer.parseInt(kodeord)))) {
				InnloggingUtil.loggInnSomDeltager(request, eventEAO.finnEventBasertPaaKodeord(k.get(0)));
				response.sendRedirect(UrlMappings.GITILBAKEMELDING_URL);
			} else {
				FlashUtil.Flash(request, "error", "Kodeordet finnes ikke");
				response.sendRedirect(UrlMappings.DELTAEVENT_URL);
			}
		} else {
			FlashUtil.Flash(request, "error", "Kodeord består av 5 siffer");
			response.sendRedirect(UrlMappings.DELTAEVENT_URL);
		}
	}
}
