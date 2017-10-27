/**
 * 
 */
package no.hvl.dat104.dataaccess;

import java.util.List;

import no.hvl.dat104.model.LiveTilbakemelding;

/**
 * @author krist
 *
 */
public interface ILiveTilbakemeldingEAO {
	/**
	 * Legger til en livetilbakemelding
	 * 
	 * @param t
	 *            LiveTilbakemelding
	 */
	public void leggTilLiveTilbakemelding(LiveTilbakemelding t);

	/**
	 * Finner en livetilbakemelding
	 * 
	 * @param id
	 *            Id til livetilbakemelding
	 */
	public LiveTilbakemelding finnLiveTilbakemelding(Integer id);

	/**
	 * Oppdaterer en livetilbakemelding
	 * 
	 * @param t
	 *            LiveTilbakemelding
	 */
	public void oppdaterLiveTilbakemelding(LiveTilbakemelding t);

	/**
	 * Sletter en livetilbakemelding
	 * 
	 * @param t
	 *            LiveTilbakemelding
	 */
	public void slettLiveTilbakemelding(LiveTilbakemelding t);

	/**
	 * Returnerer en liste med alle livetilbakemeldingene
	 *
	 * @return Alle livetilbakemeldingene
	 */
	public List<LiveTilbakemelding> alleLiveTilbakemeldingene();
}
