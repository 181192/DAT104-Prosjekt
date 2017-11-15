package no.hvl.dat104.controller.styrer.event;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import no.hvl.dat104.controller.Attributter;
import no.hvl.dat104.controller.JspMappings;
import no.hvl.dat104.controller.UrlMappings;
import no.hvl.dat104.dataaccess.IEventEAO;
import no.hvl.dat104.dataaccess.jpa.EventEAO;
import no.hvl.dat104.model.Event;
import no.hvl.dat104.model.Kodeord;
import no.hvl.dat104.model.Status;
import no.hvl.dat104.model.Tilbakemelding;
import no.hvl.dat104.util.FormaterTilbakemeldingUtil;
import no.hvl.dat104.util.InnloggingUtil;

/**
 * Servlet implementation class PostLiveEventController
 */
public class PostLiveEventController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	IEventEAO eventEAO;
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setIntHeader("Refresh", 5);
		
		if (InnloggingUtil.erInnloggetSomBruker(request)) {
			HttpSession session = request.getSession(false);
			Kodeord koden = (Kodeord)session.getAttribute("koden");
			System.out.println("koden: " + koden.getKode());
			Event detteEvent= (Event)session.getAttribute(Attributter.LIVE_EVENT);
			
			Integer eventId = detteEvent.getId();
			
			List<Tilbakemelding> endeligTB = eventEAO.finnAlleTilbakemeldingerTilEvent(eventId);
			
			
			Integer[] formaterteTB = new Integer[3];

			formaterteTB = FormaterTilbakemeldingUtil.frekventTilbakemeldinger(endeligTB);
			
			request.setAttribute(Attributter.TILBAKEMELDINGS_ARRAY, formaterteTB);
			
			request.getRequestDispatcher(JspMappings.POST_LIVE_EVENT_JSP).forward(request, response);
		} else {
			response.sendRedirect(UrlMappings.LOGGINN_URL);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (InnloggingUtil.erInnloggetSomBruker(request)) {
			HttpSession session = request.getSession(false);
			
			Event denneEvent = (Event) session.getAttribute(Attributter.LIVE_EVENT);
			
			eventEAO.endreEventTilAvsluttet(denneEvent.getId());
			
			session.removeAttribute(Attributter.LIVE_EVENT);
			response.sendRedirect(UrlMappings.LANDING_STYRER_URL);

		} else {
			response.sendRedirect(UrlMappings.LOGGINN_URL);
		}
	}
	
	
}
