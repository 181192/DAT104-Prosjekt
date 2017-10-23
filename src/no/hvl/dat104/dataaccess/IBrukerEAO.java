package no.hvl.dat104.dataaccess;

import java.util.List;

import no.hvl.dat104.model.Bruker;

public interface IBrukerEAO {

	/**
	 * Legger til en bruker
	 * 
	 * @param b
	 *            Brukeren som skal bli lagt til
	 */
	public void leggTilBruker(Bruker b);

	/**
	 * Finner en bruker basert på id
	 * 
	 * @param id
	 *            Id til brukeren
	 * @return En bruker
	 */
	public Bruker finnBruker(Integer id);

	/**
	 * Oppdaterer brukeren
	 * 
	 * @param b
	 *            Brukeren som skal oppdateres
	 */
	public void oppdaterBruker(Bruker b);

	/**
	 * Sletter en bruker
	 * 
	 * @param b
	 *            Brukeren som skal slettes
	 */
	public void slettBruker(Bruker b);

	/**
	 * Returnerer en liste med alle brukerne
	 * 
	 * @return Alle brukerne
	 */
	public List<Bruker> alleBrukerne();

}
