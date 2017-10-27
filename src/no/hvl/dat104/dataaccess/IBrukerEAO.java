package no.hvl.dat104.dataaccess;

import java.util.List;



import no.hvl.dat104.model.Bruker;
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
	 * @param b
	 *            Brukeren
	 * @param mail
	 *            Den nye mailen
	 */
	public void endreMailPaaBruker(Bruker b, String mail);

	/**
	 * Endre fornavnet til brukeren
	 * 
	 * @param b
	 *            Brukeren
	 * @param fornavn
	 *            Det nye fornavnet
	 */
	public void endreFornavnPaaBruker(Bruker b, String fornavn);

	/**
	 * Endre etternavnet til brukeren
	 * 
	 * @param b
	 *            Brukeren
	 * @param etternavn
	 *            Det nye etternavnet
	 */
	public void endreEtternavnPaaBruker(Bruker b, String etternavn);

	/**
	 * Endre passordet til brukeren
	 * 
	 * @param b
	 *            Brukeren
	 * @param passord
	 *            Det nye passordet
	 */
	public void endrePassordPaaBruker(Bruker b, String passord);

	/**
	 * Endre saltet på passordet til brukeren
	 * 
	 * @param b
	 *            Brukeren
	 * @param salt
	 *            Det nye saltet til passordet
	 * 
	 */
	public void endreSaltPaaBruker(Bruker b, String salt);

	/**
	 * Endre rollen til brukeren
	 * 
	 * @param b
	 *            Brukeren
	 * @param r
	 *            RolleF
	 */
	public void endreRollePaaBruker(Bruker b, Rolle r);

}
