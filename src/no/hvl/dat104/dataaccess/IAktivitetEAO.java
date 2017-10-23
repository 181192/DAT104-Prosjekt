package no.hvl.dat104.dataaccess;

import java.util.List;

import no.hvl.dat104.model.Aktivitet;

public interface IAktivitetEAO {

	/**
	 * Legger til en aktivitet
	 * 
	 * @param a
	 *		Aktivitet
	 */
	public void leggTilAktivitet(Aktivitet a);

	/**
	 * Finner en aktivitet
	 * 
	 * @param id
	 *		Id til aktiviteten
	 */
	public Aktivitet finnAktivitet(Integer id);
	
	/**
	 * Oppdaterer en aktivitet
	 * 
	 * @param a
	 *		Aktivitet
	 */
	public void oppdaterAktivitet(Aktivitet a);
	
	/**
	 * Sletter en aktivitet
	 * 
	 * @param a
	 *		Aktivitet
	 */
	public void slettAktivitet(Aktivitet a);
	
	/**
	 * Returnerer en liste med alle aktiviteter
	 *
	 * @return Alle aktiviteter
	 */
	public List<Aktivitet> alleAktiviteter();
	
	
}
