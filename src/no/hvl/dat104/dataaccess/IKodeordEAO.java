package no.hvl.dat104.dataaccess;

import java.util.List;

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
}
