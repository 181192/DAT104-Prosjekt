package no.hvl.dat104.controller.styrer.event;

import java.io.IOException;
import java.text.ParseException;

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
import no.hvl.dat104.model.Status;
import no.hvl.dat104.util.DatoUtil;
import no.hvl.dat104.util.FlashUtil;
import no.hvl.dat104.util.InnloggingUtil;
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
		if (InnloggingUtil.erInnloggetSomBruker(request)) {
			String eventId = request.getParameter("eventId");
			if (ValidatorUtil.isNotNull0(eventId)) {
				Integer id = Integer.parseInt(eventId);
				Event e = eventEAO.finnEvent(id);
				if (e != null) {
					RedigerEventValidator ev = new RedigerEventValidator(e);
					HttpSession mySession = request.getSession();
					mySession.setAttribute("redigerEventSkjema", ev);
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
		} else {
			FlashUtil.Flash(request, "error", "Du må være logget inn for å gjøre det!");
			response.sendRedirect(UrlMappings.LOGGINN_URL);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (InnloggingUtil.erInnloggetSomBruker(request)) {
			String eventIdString = ValidatorUtil.escapeHtml(request.getParameter("eventId"));
			RedigerEventValidator skjema = new RedigerEventValidator(request);
			Integer eventId = null;
			if (ValidatorUtil.isNotNull0(eventIdString)) {
				eventId = Integer.parseInt(eventIdString);
				if (skjema.erAlleDataGyldige()) {
					Event e = oppdaterEvent(skjema);
					eventEAO.endreParametereTilEvent(eventId, skjema.getTittel(), skjema.getBeskrivelse(), e.getTidFra(), e.getTidTil(), skjema.getHvor());
					FlashUtil.Flash(request, "success", "Eventen " + skjema.getTittel() + " er oppdatert!");
					request.getSession().removeAttribute("eventSkjema");
					response.sendRedirect(UrlMappings.LANDING_STYRER_URL);
				} else {
					skjema.settOppFeilmeldinger();
					request.getSession().setAttribute("eventSkjema", skjema);
					response.sendRedirect(UrlMappings.REDIGEREVENT_URL +"?eventId=" + eventId);
				}
			}else {
				FlashUtil.Flash(request, "error", "Eventen finnes ikke");
			}
			
		} else {
			response.sendRedirect(UrlMappings.LOGGINN_URL);
		}
	}
	public Event oppdaterEvent(RedigerEventValidator skjema) {
		Event e = new Event();
		e.setNavn(skjema.getTittel());
		e.setSted(skjema.getHvor());
		e.setBeskrivelse(skjema.getBeskrivelse());
		try {
			e.setTidTil(DatoUtil.formaterDatoTilStamp(skjema.getDato(), skjema.getTil()));
			e.setTidFra(DatoUtil.formaterDatoTilStamp(skjema.getDato(), skjema.getFra()));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		return e;
	}

}
