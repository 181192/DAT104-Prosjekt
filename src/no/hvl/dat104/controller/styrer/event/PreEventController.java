package no.hvl.dat104.controller.styrer.event;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import no.hvl.dat104.controller.Attributter;
import no.hvl.dat104.controller.JspMappings;
import no.hvl.dat104.controller.UrlMappings;
import no.hvl.dat104.dataaccess.IEventEAO;
import no.hvl.dat104.model.Event;
import no.hvl.dat104.model.LiveTilbakemelding;
import no.hvl.dat104.model.Status;

/**
 * Servlet implementation class PreEventController
 */
public class PreEventController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	IEventEAO ieventEAO;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doget i preEventController");
		
		//Skal her teste for at bruker har en session.
		HttpSession session = request.getSession(true);
		
		//Må finne eventet som skal startes på en eller annen måte. 
		Event ev = ieventEAO.finnEvent(20);
		
		ev.setStatus(Status.PLANLAGT);
		ieventEAO.oppdaterEvent(ev);
		
		System.out.println("eventStatus: " + ev.getStatus());
		
		session.setAttribute(Attributter.LIVE_EVENT, ev);
		String denneStatus = ev.getStatus();
		//Hvis eventet er planlagt, skal viser vi PreEventJSP.
		if (denneStatus.equals(Status.PLANLAGT)) {
			request.getRequestDispatcher(JspMappings.PRE_EVENT_JSP).forward(request, response);
		} else if (denneStatus.equals(Status.PAAGANDE)) {
			
			request.getRequestDispatcher(JspMappings.LIVE_EVENT_JSP).forward(request, response);
		} else {

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("dopost i preEventController.... ");
		
		//Trenger session for å finne riktig event. 
		HttpSession session = request.getSession(false);
		
		Event ev = (Event)session.getAttribute(Attributter.LIVE_EVENT);
		System.out.println("liveEvent: " + ev.getId());

		//Trenger test for rett knapp/loggin/osv/bruke hidden?
		//Startknappen trykkes: lager liste, setter status til pågående. 
		if(true) {
			List<LiveTilbakemelding> liveTilbakemeldinger = new ArrayList<>();
			System.out.println("liveTilbakemeldinger.lengde: " + liveTilbakemeldinger.size());
			ev.setLiveTilbakemeldinger(liveTilbakemeldinger);
			ev.setStatus(Status.PAAGANDE);
			
			session.setAttribute(Attributter.LIVE_EVENT, ev);
			response.sendRedirect(UrlMappings.LIVE_EVENT_URL);
		}

		
		
	}

}
