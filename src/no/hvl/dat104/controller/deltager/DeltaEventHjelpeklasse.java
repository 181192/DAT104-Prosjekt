package no.hvl.dat104.controller.deltager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import no.hvl.dat104.dataaccess.IEventEAO;
import no.hvl.dat104.model.Event;
import no.hvl.dat104.model.Kodeord;

public class DeltaEventHjelpeklasse {

	/**
	 * Sjekker om kodeord har riktig syntaks
	 * 
	 * @param kodeord
	 * @return boolsk verdi
	 */
	public static boolean riktigKodeordSyntaks(String kodeord) {
		return kodeord != null && kodeord.matches("[0-9]+"); // && kodeord.length()==8
	}

	/**
	 * Laster event til sesjon
	 * @param kodeord
	 * @param iEventEAO
	 * @param request
	 * @throws Exception
	 */

	public static void lastEventTilSesjon(Kodeord kodeord, IEventEAO iEventEAO,
			HttpServletRequest request) {
		HttpSession sesjon = request.getSession();
		sesjon.invalidate();
		sesjon = request.getSession(true);

		Event event = DeltaEventHjelpeklasse.finnEvent(kodeord, iEventEAO);

		sesjon.setAttribute("Event", event);
	}
	
	/**
	 * finner eventet til kodeordet
	 * 
	 * @param kodeord
	 * @return Event
	 */
	private static Event finnEvent(Kodeord k, IEventEAO eventEAO) {
		return eventEAO.finnEventBasertPaaKodeord(k);
	}
}
