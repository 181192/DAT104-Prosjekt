package no.hvl.dat104.dataaccess;

import java.util.List;

import no.hvl.dat104.model.Aktivitet;
import no.hvl.dat104.model.Bruker;
import no.hvl.dat104.model.Event;

public interface IEventEAO {
	
	/**
	 * Legger til et event
	 * 
	 * @param e
	 *		Event
	 */
	public void leggTilEvent(Event e);

	/**
	 * Finner et event
	 * 
	 * @param id
	 *		Id til eventet
	 */
	public Event finnEvent(Integer id);
	
	/**
	 * Oppdaterer et event
	 * 
	 * @param e
	 *		Event
	 */
	public void oppdaterEvent(Event e);
	
	/**
	 * Sletter et event
	 * 
	 * @param e
	 *		Event
	 */
	public void slettEvent(Event e);
	
	/**
	 * Returnerer en liste med alle eventer
	 *
	 * @return Alle eventer
	 */
	public List<Event> alleEventer();
	
	/**
	 * Returnerer en liste med alle eventer til en gitt aktivitet.
	 * @param aktivitet
	 * @return
	 */
	public List<Event> finnAlleEventerTilAktivitet(Aktivitet id);
	
	

}
