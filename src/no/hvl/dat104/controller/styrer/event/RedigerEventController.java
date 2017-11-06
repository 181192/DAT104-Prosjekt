package no.hvl.dat104.controller.styrer.event;

import java.io.IOException;
import java.sql.Timestamp;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import no.hvl.dat104.controller.JspMappings;
import no.hvl.dat104.controller.UrlMappings;
import no.hvl.dat104.dataaccess.IEventEAO;
import no.hvl.dat104.model.Event;
import no.hvl.dat104.util.DatoUtil;
import no.hvl.dat104.util.FlashUtil;
import no.hvl.dat104.util.ValidatorUtil;

/**
 * Servlet implementation class RedigerEventController
 */
public class RedigerEventController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private IEventEAO eventEAO;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String eventId = request.getParameter("eventId");
		if (ValidatorUtil.isNotNull0(eventId)) {
			Integer id = Integer.parseInt(eventId);
			Event e = eventEAO.finnEvent(id);
			if (e != null) {
				HttpSession mySession = request.getSession();
				mySession.setAttribute("event", e);
				request.getRequestDispatcher(JspMappings.REDIGEREVENT_JSP).forward(request, response);
			} else {
				response.sendRedirect(UrlMappings.MINEEVENTER_URL);
				FlashUtil.Flash(request, "error", "Beklager, eventen eksisterer ikke");
			}
		} else {
			response.sendRedirect(UrlMappings.MINEEVENTER_URL);
			FlashUtil.Flash(request, "error", "Beklager, eventen eksisterer ikke");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Integer id = Integer.valueOf(request.getParameter("id"));
		String navn = request.getParameter("navn");
		String beskrivelse = request.getParameter("beskrivelse");
		String dato = request.getParameter("dato");
		String klokkeslettFra = request.getParameter("fra");
		String klokkeslettTil = request.getParameter("til");
		String status = request.getParameter("status");
		String sted = request.getParameter("sted");
		Timestamp dateFra = null;
		Timestamp dateTil = null;
		
		try {
		    dateFra = DatoUtil.parseBasertPaaBindestrek(dato, klokkeslettFra);
		    dateTil = DatoUtil.parseBasertPaaBindestrek(dato, klokkeslettTil);
		} catch(Exception exc) { 
			exc.printStackTrace();
			
		}
		
		//Oppdaterer eventen
		eventEAO.endreParametereTilEvent(id, navn, beskrivelse, dateFra, dateTil, status, sted);

		doGet(request,response);
		
	}

}
