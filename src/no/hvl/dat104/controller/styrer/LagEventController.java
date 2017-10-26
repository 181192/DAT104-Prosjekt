package no.hvl.dat104.controller.styrer;

import java.io.IOException;
import java.text.ParseException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import no.hvl.dat104.controller.UrlMappings;
import no.hvl.dat104.dataaccess.IEventEAO;
import no.hvl.dat104.model.Aktivitet;
import no.hvl.dat104.model.Bruker;
import no.hvl.dat104.model.Event;
import no.hvl.dat104.util.DatoUtil;

/**
 * Servlet implementation class LagEventController
 */
public class LagEventController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
    private IEventEAO iEventEAO;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	request.getRequestDispatcher("WEB-INF/views/styrer/lagevent.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        EventValidator skjema = new EventValidator(request);
        if (skjema.erAlleDataGyldige()) {        	
        	Event e = lagEvent(request, skjema);
        	iEventEAO.leggTilEvent(e);
            request.getSession().removeAttribute("skjema");
            response.sendRedirect(UrlMappings.LANDING_STYRER_URL);
        } else {
            skjema.settOppFeilmeldinger();
            request.getSession().setAttribute("skjema", skjema);
            response.sendRedirect("skjema");
        }
    }
	public Event lagEvent(HttpServletRequest request, EventValidator skjema) {
		Event e = new Event();
		Aktivitet aktivitet = new Aktivitet();
		Bruker bruker = new Bruker();
    	e.setNavn(skjema.getTittel());
    	e.setSted(skjema.getHvor());
    	e.setStatus("ok");
    	e.setIdAktivitet(aktivitet);
    	try {
			e.setTidTil(DatoUtil.formaterDatoTilStamp(skjema.getDato(), skjema.getTil()));
			e.setTidFra(DatoUtil.formaterDatoTilStamp(skjema.getDato(), skjema.getFra()));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
    	aktivitet.setEvent(e);
		return e;
	}
}
