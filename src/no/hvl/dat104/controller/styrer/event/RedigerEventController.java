package no.hvl.dat104.controller.styrer.event;

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
import no.hvl.dat104.model.Event;
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
