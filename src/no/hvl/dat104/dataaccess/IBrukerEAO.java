package no.hvl.dat104.dataaccess;

import java.util.List;

import no.hvl.dat104.model.Aktivitet;
import no.hvl.dat104.model.Bruker;
import no.hvl.dat104.model.Event;
import no.hvl.dat104.model.Rolle;

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
	 * Finner en bruker ut fra mailadresse
	 * 
	 * @param mail
	 *            Mailadressen til brukeren
	 * @return En bruker
	 */
	public Bruker finnBrukerPaaEmail(String email);

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

	/**
	 * Endre mailen til brukeren
	 * 
	 * @param id
	 *            Brukeren
	 * @param mail
	 *            Den nye mailen
	 */
	public void endreMailPaaBruker(Integer id, String mail);

	/**
	 * Endre fornavnet til brukeren
	 * 
	 * @param id
	 *            Brukeren
	 * @param fornavn
	 *            Det nye fornavnet
	 */
	public void endreFornavnPaaBruker(Integer id, String fornavn);

	/**
	 * Endre etternavnet til brukeren
	 * 
	 * @param id
	 *            Brukeren
	 * @param etternavn
	 *            Det nye etternavnet
	 */
	public void endreEtternavnPaaBruker(Integer id, String etternavn);

	/**
	 * Endre passordet til brukeren
	 * 
	 * @param id
	 *            Brukeren
	 * @param passord
	 *            Det nye passordet
	 */
	public void endrePassordPaaBruker(Integer id, String passord);

	/**
	 * Endre saltet på passordet til brukeren
	 * 
	 * @param id
	 *            Brukeren
	 * @param salt
	 *            Det nye saltet til passordet
	 * 
	 */
	public void endreSaltPaaBruker(Integer id, String salt);

	/**
	 * Endre rollen til brukeren
	 * 
	 * @param id
	 *            Brukeren
	 * @param r
	 *            RolleF
	 */
	public void endreRollePaaBruker(Integer id, Rolle r);

	/**
	 * Finner alle eventer til brukeren
	 * 
	 * @param id
	 *            Brukeren
	 * @return Eventer til brukeren
	 */
	public List<Event> finnAlleEventerTilBruker(Integer id);

	/**
	 * Finner alle aktiviteter til brukeren
	 * 
	 * @param id
	 *            Brukeren
	 * @return Alle aktiviteter til brukeren
	 */
	public List<Aktivitet> finnAlleAktiviteterTilBruker(Integer id);
	
	/**
	 * test
	 * @param id
	 * @param e
	 */
	public void finnBrukerLeggTilEvent(Integer id, Event e, Integer aktivitetId);
	
	/**
	 * Returnerer sortert liste ved hjelp av JPQL
	 * @param id
	 * @return
	 */
	public List<Aktivitet> alleAktiviteterIJPQL(Integer id);


}
