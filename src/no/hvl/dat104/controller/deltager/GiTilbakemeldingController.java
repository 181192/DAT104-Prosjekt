package no.hvl.dat104.controller.deltager;

import java.io.IOException;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hvl.dat104.controller.JspMappings;
import no.hvl.dat104.controller.UrlMappings;
import no.hvl.dat104.dataaccess.IEventEAO;
import no.hvl.dat104.dataaccess.ITilbakemeldingEAO;
import no.hvl.dat104.dataaccess.ILiveTilbakemeldingEAO;
import no.hvl.dat104.model.Event;
import no.hvl.dat104.model.Tilbakemelding;
import no.hvl.dat104.model.LiveTilbakemelding;
import no.hvl.dat104.util.DatoUtil;
import no.hvl.dat104.util.FlashUtil;
import no.hvl.dat104.util.InnloggingUtil;

/**
 * Servlet implementation class GiTilbakemeldingController
 */
public class GiTilbakemeldingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    @EJB
    private ITilbakemeldingEAO iTilbakemeldingEAO;
    @EJB
    private IEventEAO iEventEAO;
    @EJB
    private ILiveTilbakemeldingEAO ILiveTilbakemeldingEAO; 

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (InnloggingUtil.erInnloggetSomDeltager(request)) {
			request.getRequestDispatcher(JspMappings.GITILBAKEMELDING_JSP).forward(request, response);
		}
		else {
			response.sendRedirect(UrlMappings.DELTAEVENT_URL);// index
		}
	}
	
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doPost metoden");
    	if (eventHarStartet(request) && !maaVente(request, 10)) {
    		System.out.println("event har startet");
    		if(eventPaagaar(request)) {
        		System.out.println("event paagaar");
        		lastOppTilbakemelding(request,"paagaaende");
        		FlashUtil.Flash(request, "Success", "Sist stemme avgitt " + new Date().toString().substring(11, 19));
        		response.sendRedirect("gitilbakemelding");
    		}
    		else {//helhetlig tilbakemelding
        		System.out.println("event avsluttet");
        		lastOppTilbakemelding(request,"heletlig");
        		FlashUtil.Flash(request, "Success", "Sist stemme avgitt " + new Date().toString().substring(11, 19));
        		response.sendRedirect("gitilbakemelding");
    		}
    	}
    	else {
    		System.out.println("event har ikke startet");
			FlashUtil.Flash(request, "Error", "Eventet har ikke startet");
    		response.sendRedirect("gitilbakemelding");
    	}
	}
    
    private boolean maaVente(HttpServletRequest request, int sekunder) {
    	Date avgittDato = (Date) request.getAttribute("sistStemme");
    	if (request.getAttribute("sistStemme")!=null) {
    		Date naa = new Date();
    		long sekunderMellom = (naa.getTime() - avgittDato.getTime() / 1000);
    		return sekunderMellom >= sekunder;
    		
    	}
    	else {
    		return false;
    	}
    }

    
    private boolean eventPaagaar(HttpServletRequest request) {
    	Event event = (Event) request.getSession().getAttribute("event");
    	return event.getTidTil().after(new Date()); 
    }
    
    private boolean eventHarStartet(HttpServletRequest request) {
    	Event event = (Event) request.getSession().getAttribute("event");
		System.out.println("Tid fra: " + event.getTidFra());
    	return new Date().after(event.getTidFra()); 
    }

	private void lastOppTilbakemelding(HttpServletRequest request, String type) {
		if (type.equals("paagaaende")) {
			System.out.println("Par 1: " + request.getParameter("tilbakemelding") + ", par 2: " + DatoUtil.lagCurrentTimestamp() + ", par 3: " + (request.getSession(true).getAttribute("event")!=null));
			LiveTilbakemelding liveTilbakemelding = new LiveTilbakemelding(request.getParameter("tilbakemelding"),
					DatoUtil.lagCurrentTimestamp(), (Event) request.getSession(true).getAttribute("event"));
			System.out.println(liveTilbakemelding != null);
			//denne kaster nullpointexception
			ILiveTilbakemeldingEAO.leggTilLiveTilbakemelding(liveTilbakemelding);
		}
		else {//helehtlig
			Tilbakemelding tilbakemelding = new Tilbakemelding(request.getParameter("tilbakemelding"),
					(Event) request.getSession(true).getAttribute("event"), DatoUtil.lagCurrentTimestamp());
			iTilbakemeldingEAO.leggTilTilbakemelding(tilbakemelding);
		}
	}
}
