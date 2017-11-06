package no.hvl.dat104.dataaccess;

import java.sql.Timestamp;
import java.util.List;

import no.hvl.dat104.model.Aktivitet;
import no.hvl.dat104.model.Event;
import no.hvl.dat104.model.Kodeord;
import no.hvl.dat104.model.Tilbakemelding;

public interface IEventEAO {

	/**
	 * Legger til et event
	 * 
	 * @param e
	 *            Event
	 */
	public void leggTilEvent(Event e);

	/**
	 * Finner et event
	 * 
	 * @param id
	 *            Id til eventet
	 */
	public Event finnEvent(Integer id);

	/**
	 * Oppdaterer et event
	 * 
	 * @param e
	 *            Event
	 */
	public void oppdaterEvent(Event e);

	/**
	 * Sletter et event
	 * 
	 * @param e
	 *            Event
	 */
	public void slettEvent(Event e);

	/**
	 * Returnerer en liste med alle eventer
	 *
	 * @return Alle eventer
	 */
	public List<Event> alleEventer();

	/**
	 * Returnerer en liste med alle tilbakemeldingene til eventet
	 * 
	 * @param id
	 *            Id til eventet
	 * @return Liste med tilbakemeldinger
	 */
	public List<Tilbakemelding> finnAlleTilbakemeldingerTilEvent(Integer id);

	/**
	 * Endre status på event
	 * 
	 * @param id
	 *            Id til event
	 * @param status
	 *            Statusen som skal endres
	 */
	public void endreStatusPaaEvent(Integer id, String status);

	/**
	 * Finner eventet basert på kodeordet
	 * 
	 * @param k
	 *            Kodeord
	 * @return Eventet
	 */
	public Event finnEventBasertPaaKodeord(Kodeord k);

	/**
	 * Finner aktiviteten til eventet
	 * 
	 * @param id
	 *            Id til event
	 * @return Aktiviteten til eventet
	 */
	public Aktivitet finnAktivitetTilEvent(Integer id);
	
	/**
	 * Endrer eventet basert på parametere
	 * @param id
	 * @param navn
	 * @param beskrivelse
	 * @param tidFra
	 * @param tidTil
	 * @param status
	 * @param sted
	 */
	public void endreParametereTilEvent(Integer id, String navn, String beskrivelse, Timestamp tidFra, Timestamp tidTil, String status, String sted);
}
