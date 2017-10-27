package no.hvl.dat104.dataaccess;

import java.util.List;

import no.hvl.dat104.model.Aktivitet;
import no.hvl.dat104.model.Bruker;

public interface IAktivitetEAO {

	/**
	 * Legger til en aktivitet
	 * 
	 * @param a
	 *            Aktivitet
	 */
	public void leggTilAktivitet(Aktivitet a);

	/**
	 * Finner en aktivitet
	 * 
	 * @param id
	 *            Id til aktiviteten
	 */
	public Aktivitet finnAktivitet(Integer id);

	/**
	 * Oppdaterer en aktivitet
	 * 
	 * @param a
	 *            Aktivitet
	 */
	public void oppdaterAktivitet(Aktivitet a);

	/**
	 * Sletter en aktivitet
	 * 
	 * @param a
	 *            Aktivitet
	 */
	public void slettAktivitet(Aktivitet a);

	/**
	 * Returnerer en liste med alle aktiviteter
	 *
	 * @return Alle aktiviteter
	 */
	public List<Aktivitet> alleAktiviteter();

	/**
	 * Endrer navnet paa aktiviteten
	 * 
	 * @param a
	 *            Aktiviteten som skal endres paa
	 * @param navn
	 *            Det nye navnet på aktiviteten
	 */
	public void endreNavnPaaAktivitet(Aktivitet a, String navn);

	/**
	 * Endrer statusen paa aktiviteten
	 * 
	 * @param a
	 *            Aktiviteten som skal endres paa
	 * @param status
	 *            Den nye statusen
	 */
	public void endreStatusPaaAktivitet(Aktivitet a, String status);

	/**
	 * Endrer brukeren paa aktiviteten
	 * 
	 * @param a
	 *            Aktiviteten som skal endres paa
	 * @param bruker
	 *            Den nye brukeren
	 */
	public void flyttAktivitetenTilNyBruker(Aktivitet a, Bruker bruker);

}
