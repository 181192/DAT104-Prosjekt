package no.hvl.dat104.dataaccess;

import java.util.List;

import no.hvl.dat104.model.Aktivitet;
import no.hvl.dat104.model.Bruker;
import no.hvl.dat104.model.Event;

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
	 * @param id
	 *            Aktiviteten som skal endres paa
	 * @param navn
	 *            Det nye navnet på aktiviteten
	 */
	public void endreNavnPaaAktivitet(Integer id, String navn);

	/**
	 * Endrer statusen paa aktiviteten
	 * 
	 * @param id
	 *            Aktiviteten som skal endres paa
	 * @param status
	 *            Den nye statusen
	 */
	public void endreStatusPaaAktivitet(Integer id, String status);

	/**
	 * Endrer brukeren paa aktiviteten
	 * 
	 * @param id
	 *            Aktiviteten som skal endres paa
	 * @param bruker
	 *            Den nye brukeren
	 */
	public void flyttAktivitetenTilNyBruker(Integer id, Bruker bruker);

	/**
	 * Returnerer en liste med alle eventer til en gitt aktivitet.
	 * 
	 * @param aktivitet
	 * @return
	 */
	public List<Event> finnAlleEventerTilAktivitet(Integer id);

	/**
	 * 
	 * @param id
	 * @param navn
	 * @param status
	 */
	public void endreParametereTilAktivitet(Integer id, String navn, String status);

	/**
	 * Finner aktiviteten basert paa navn og bruker
	 * 
	 * @param navn
	 *            Navnet paa aktiviteten
	 * @param b
	 *            Brukeren
	 * @return Aktiviteten eller null
	 */
	public Aktivitet finnAktivitetPaaNavnOgBruker(String navn, Bruker b);

}
