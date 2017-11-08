package no.hvl.dat104.controller.deltager;

import java.util.List;

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
	 * finner eventet til kodeordet
	 * 
	 * @param kodeord
	 * @return Event
	 */
	public static Event finnEvent(Kodeord k, IEventEAO eventEAO) {
		return eventEAO.finnEventBasertPaaKodeord(k);
	}
	
	/**
	 * Sjekker om kodeord finnes
	 * @param Liste av typen Kodeord
	 * @return boolean
	 */
	public static boolean kodeordFinnes(List<Kodeord> k) {
		return k.size() != 0;
	}
}
