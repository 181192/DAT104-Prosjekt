package no.hvl.dat104.controller.deltager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import no.hvl.dat104.dataaccess.IEventEAO;
import no.hvl.dat104.dataaccess.IKodeordEAO;
import no.hvl.dat104.model.Event;
import no.hvl.dat104.model.Kodeord;
 
public class DeltaEventHjelpeklasse {
	
	/**
     * Sjekker om kodeord har riktig syntaks
     * @param kodeord
     * @return boolsk verdi
     */
	public static boolean riktigKodeordSyntaks(String kodeord) {
		return kodeord!=null && kodeord.matches("[0-9]+"); //&& kodeord.length()==8
	}
	
	/**
     * finner eventet til kodeordet
     * @param kodeord
     * @return Event
     */
	private static Event finnEvent(String kodeord, IEventEAO iEventEAO) {
		return new Event();
	}
	
	public static void lastEventTilSesjon(String kodeord, HttpSession sesjon, IEventEAO iEventEAO, HttpServletRequest request) {
		sesjon.invalidate();
		sesjon = request.getSession(true);
		Event event = DeltaEventHjelpeklasse.finnEvent(kodeord, iEventEAO);
		sesjon.setAttribute("Event", event);
	}
}
