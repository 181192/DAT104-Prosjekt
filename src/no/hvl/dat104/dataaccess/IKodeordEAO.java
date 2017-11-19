package no.hvl.dat104.dataaccess;

import java.util.List;

import no.hvl.dat104.model.Event;
import no.hvl.dat104.model.Kodeord;

public interface IKodeordEAO {
	/**
	 * Legger til et kodeord
	 * 
	 * @param k
	 *            Kodeordet som skal bli lagt til
	 */
	public void leggTilKodeord(Kodeord k);

	/**
	 * Finner ett kodeord basert på id
	 * 
	 * @param id
	 *            Id til kodeordet
	 * @return Ett kodeord
	 */
	public Kodeord finnKodeord(Integer id);

	/**
	 * Oppdaterer kodeordet
	 * 
	 * @param k
	 *            Kodeordet som skal oppdateres
	 */
	public void oppdaterKodeord(Kodeord k);

	/**
	 * Sletter ett kodeord
	 * 
	 * @param k
	 *            Kodeordet som skal slettes
	 */
	public void slettKodeord(Kodeord k);

	/**
	 * Returnerer en liste med alle kodeordene
	 * 
	 * @return Alle kodeordene
	 */
	public List<Kodeord> alleKodeord();

	/**
	 * Endre kode til kodeordet
	 * 
	 * @param id
	 *            Kodeordet
	 * @param kode
	 *            Kode som skal endres
	 */
	public void endreKodePaaKodeord(Integer id, Integer kode);

	/**
	 * Sjekk at kodeordet du har generert er unikt
	 * 
	 * @param kode
	 * 
	 * @return true/false
	 * 
	 */
	public Boolean sjekkOmKodeordErUnik(Kodeord kode);

	/**
	 * Finner kodeordet til event.
	 * 
	 * @param event
	 * 
	 * @return Ett kodeord
	 * 
	 */
	public Kodeord finnKodeordTilEvent(Event event);

	/**
	 * Finner kodeord basert på kode
	 * 
	 * @param kode
	 *            Kode
	 * @return Kodeord
	 */
	public List<Kodeord> finnKodeordBasertPaaKode(Integer kode);

	/**
	 * Sletter kodeord basert på event.
	 * @param e
	 * @return
	 */
	boolean slettKodeordTilEvent(Event e);
}
