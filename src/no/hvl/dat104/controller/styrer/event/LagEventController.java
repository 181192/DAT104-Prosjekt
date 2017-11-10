package no.hvl.dat104.controller.styrer.event;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hvl.dat104.controller.JspMappings;
import no.hvl.dat104.controller.UrlMappings;
import no.hvl.dat104.dataaccess.IAktivitetEAO;
import no.hvl.dat104.dataaccess.IBrukerEAO;
import no.hvl.dat104.dataaccess.IEventEAO;
import no.hvl.dat104.model.Aktivitet;
import no.hvl.dat104.model.Bruker;
import no.hvl.dat104.model.Event;
import no.hvl.dat104.model.Status;
import no.hvl.dat104.util.DatoUtil;
import no.hvl.dat104.util.InnloggingUtil;

/**
 * Servlet implementation class LagEventController
 */
public class LagEventController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private IEventEAO iEventEAO;
	@EJB
	private IBrukerEAO iBrukerEAO;
	@EJB
	private IAktivitetEAO iAktivitetEAO;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Bruker bruker = InnloggingUtil.innloggetSomBruker(request);
		String dato = request.getParameter("dato");
		if (InnloggingUtil.erInnloggetSomBruker(request) && bruker != null ) {
			if(dato != null) {
				request.getSession().setAttribute("dato", dato);
				List<Aktivitet> a = iBrukerEAO.finnAlleAktiviteterTilBruker(bruker.getId());
				request.getSession().setAttribute("aktiviteter", a);
				request.getRequestDispatcher(JspMappings.LAGEVENT_JSP).forward(request, response);
			}else {
				response.sendRedirect(UrlMappings.LOGGINN_URL);
			}
		} else {
			response.sendRedirect(UrlMappings.LOGGINN_URL);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Bruker bruker = InnloggingUtil.innloggetSomBruker(request);
		if (InnloggingUtil.erInnloggetSomBruker(request) && bruker != null) {
			EventValidator skjema = new EventValidator(request);
			if (skjema.erAlleDataGyldige()) {
				Event e = lagEvent(request, skjema);
				iBrukerEAO.finnBrukerLeggTilEvent(bruker.getId(), e, Integer.parseInt(skjema.getAktivitet()));
				request.getSession().removeAttribute("eventSkjema");
				response.sendRedirect(UrlMappings.LANDING_STYRER_URL);
			} else {
				skjema.settOppFeilmeldinger();
				request.getSession().setAttribute("eventSkjema", skjema);
				response.sendRedirect(UrlMappings.LAGEVENT_URL);
			}
		} else {
			response.sendRedirect(UrlMappings.LOGGINN_URL);
		}

	}

	public Event lagEvent(HttpServletRequest request, EventValidator skjema) {
		Event e = new Event();
		e.setNavn(skjema.getTittel());
		e.setSted(skjema.getHvor());
		e.setStatus(Status.PLANLAGT);
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
