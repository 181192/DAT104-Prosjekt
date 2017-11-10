package no.hvl.dat104.controller.deltager;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hvl.dat104.controller.UrlMappings;
import no.hvl.dat104.dataaccess.IEventEAO;
import no.hvl.dat104.dataaccess.ILiveTilbakemeldingEAO;
import no.hvl.dat104.dataaccess.ITilbakemeldingEAO;
import no.hvl.dat104.model.Event;
import no.hvl.dat104.model.LiveTilbakemelding;
import no.hvl.dat104.model.Tilbakemelding;
import no.hvl.dat104.util.DatoUtil;
import no.hvl.dat104.util.FlashUtil;

public class GiTilbakemeldingHjelpeklasse {
	private static int sekunderVente;
	
	/**
	 * Laster opp liveTilbakende eller (heletelig)tilbakemelding
	 * @param request
	 * @param liveTilbakemeldingEAO
	 * @param tilbakemeldingEAO
	 * @param response
	 * @throws IOException
	 */

	public static void lastOppTilbakemelding(HttpServletRequest request, ILiveTilbakemeldingEAO liveTilbakemeldingEAO, 
			ITilbakemeldingEAO tilbakemeldingEAO,HttpServletResponse response,IEventEAO eventEAO) throws IOException  {
		if (GiTilbakemeldingHjelpeklasse.eventPaagaar(request, eventEAO)) {
			LiveTilbakemelding liveTilbakemelding = new LiveTilbakemelding(request.getParameter("tilbakemelding"),
					DatoUtil.lagCurrentTimestamp(), (Event) request.getSession(true).getAttribute("event"));
			liveTilbakemeldingEAO.leggTilLiveTilbakemelding(liveTilbakemelding);
    		GiTilbakemeldingHjelpeklasse.settSistStemtTid(request);
    		GiTilbakemeldingHjelpeklasse.settInfomelding(request);
    		response.sendRedirect(UrlMappings.GITILBAKEMELDING_URL);
		}
		else {
			Tilbakemelding tilbakemelding = new Tilbakemelding(request.getParameter("tilbakemelding"),
					(Event) request.getSession(true).getAttribute("event"), DatoUtil.lagCurrentTimestamp());
			tilbakemeldingEAO.leggTilTilbakemelding(tilbakemelding);
			request.getSession().setAttribute("avsluttendeStemmeAvgitt", true);
			GiTilbakemeldingHjelpeklasse.settInfomelding(request);
    		response.sendRedirect(UrlMappings.DELTAEVENT_URL);
		}
	}
	
	/**
	 * Sjekker om man må vente basert på parameter og eventuell tidligere avgitt stemme
	 * @param request
	 * @param sekunderVente
	 * @return boolean
	 */
	
    public static boolean maaVente(HttpServletRequest request, int sekunderVente) {
    	GiTilbakemeldingHjelpeklasse.sekunderVente = sekunderVente;
    	Date sistStemme = (Date) request.getSession().getAttribute("sistStemme");
    	if (sistStemme!=null) {
    		Date naa = new Date();
    		long sekunderMellom = (naa.getTime() - sistStemme.getTime()) / 1000;
    		return sekunderMellom <= sekunderVente;
    		
    	}
    	else {
    		return false;
    	}
    }
    
    /**
     * Setter opp feilmelding ved ikke avgitt stemme
     * @param request
     */
    public static void settFeilmelding(HttpServletRequest request) {
    	String melding;
    	Date sistStemme = (Date) request.getSession().getAttribute("sistStemme");
    	if (sistStemme == null) {
    		melding = "Eventet har ikke startet";
    	}
    	else {
    		Date naa = new Date();
    		long sekunderIgjen = GiTilbakemeldingHjelpeklasse.sekunderVente - ((naa.getTime() - sistStemme.getTime()) / 1000);
    		if (sekunderIgjen > 1) {
        		melding = "Du må vente " + sekunderIgjen + " sekunder.";
    		}
    		else {
    			sekunderIgjen = 1;
        		melding = "Du må vente " + sekunderIgjen + " sekund.";
    		}
    	}
		FlashUtil.Flash(request, "Error", melding);
    }
    
    /**
     * Sjekker om eventer har startet
     * @param request
     * @return boolean
     */
    public static boolean eventHarStartet(HttpServletRequest request, IEventEAO eventEAO) {
    	Event gammelVersjon = (Event) request.getSession().getAttribute("event");
    	Event nyVersjon = eventEAO.finnEvent(gammelVersjon.getId());
    	return nyVersjon.getFaktiskStart()!=null; 
    }
    
    /**
     * Sjekker om event pågår
     * @param request
     * @return boolean
     */
    private static boolean eventPaagaar(HttpServletRequest request, IEventEAO eventEAO) {
    	Event gammelVersjon = (Event) request.getSession().getAttribute("event");
    	Event nyVersjon = eventEAO.finnEvent(gammelVersjon.getId());
    	return nyVersjon.getFaktiskSlutt()==null;
    }
	
    /**
     * Setter klokkeslett og dato ved avgitt stemme
     * @param request
     */
    private static void settSistStemtTid(HttpServletRequest request) {
    	request.getSession().setAttribute("sistStemme", new Date());
    }
    
    /**
     * Setter infomelding ved avgitt stemme
     * @param request
     */
    public static void settInfomelding(HttpServletRequest request) {
    	if (request.getSession().getAttribute("avsluttendeStemmeAvgitt")!=null && (boolean) request.getSession().getAttribute("avsluttendeStemmeAvgitt")==true) {
    		FlashUtil.Flash(request, "Success", "Tilbakemelding registrert. Takk for din innsats!");
    	}
    	else {
    		FlashUtil.Flash(request, "Success", "Stemme avgitt.");
    	}
    }
    
    /**
     * Sjekker om deltaker tidligere har avgitt avsluttende stemme
     * @param request 
     * @return boolean
     */
    public static boolean avgittStemmeTidligere(HttpServletRequest request) {
    	return request.getSession().getAttribute("avsluttendeStemmeAvgitt")!=null;
    }
}
