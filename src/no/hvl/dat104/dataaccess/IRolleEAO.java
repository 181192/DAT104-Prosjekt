package no.hvl.dat104.dataaccess;

import java.util.List;

import no.hvl.dat104.model.Rolle;

public interface IRolleEAO {
	/**
	 * Legger til en rolle
	 * 
	 * @param r
	 *            Rolle
	 */
	public void leggTilRolle(Rolle r);

	/**
	 * Finner en rolle
	 * 
	 * @param id
	 *            Id til rolle
	 */
	public Rolle finnRolle(Integer id);

	/**
	 * Oppdaterer en rolle
	 * 
	 * @param r
	 *            Rolle
	 */
	public void oppdaterRolle(Rolle r);

	/**
	 * Sletter en rolle
	 * 
	 * @param r
	 *            Rolle
	 */
	public void slettRolle(Rolle r);

	/**
	 * Returnerer en liste med alle rollene
	 *
	 * @return Alle rollene
	 */
	public List<Rolle> alleRollene();

	/**
	 * Endre type paa rolle
	 * 
	 * @param id
	 *            Rolle
	 * @param type
	 *            Type
	 */
	public void endreTypePaaRolle(Integer id, String type);
}
