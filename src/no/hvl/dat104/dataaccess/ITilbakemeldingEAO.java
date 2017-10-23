package no.hvl.dat104.dataaccess;

import java.util.List;

import no.hvl.dat104.model.Tilbakemelding;

public interface ITilbakemeldingEAO {
	/**
	 * Legger til en tilbakemelding
	 * 
	 * @param t
	 *            Tilbakemelding
	 */
	public void leggTilTilbakemelding(Tilbakemelding t);

	/**
	 * Finner en tilbakemelding
	 * 
	 * @param id
	 *            Id til tilbakemelding
	 */
	public Tilbakemelding finnTilbakemelding(Integer id);

	/**
	 * Oppdaterer en tilbakemelding
	 * 
	 * @param t
	 *            Tilbakemelding
	 */
	public void oppdaterTilbakemelding(Tilbakemelding t);

	/**
	 * Sletter en tilbakemelding
	 * 
	 * @param t
	 *            Tilbakemelding
	 */
	public void slettTilbakemelding(Tilbakemelding t);

	/**
	 * Returnerer en liste med alle tilbakemeldingene
	 *
	 * @return Alle tilbakemeldingene
	 */
	public List<Tilbakemelding> alleTilbakemeldingene();

}
