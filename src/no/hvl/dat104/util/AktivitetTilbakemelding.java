package no.hvl.dat104.util;

public class AktivitetTilbakemelding {
	private String navn;
	private Integer fornoyd;
	private Integer noytral;
	private Integer misfornoyd;

	public AktivitetTilbakemelding() {
		this.navn = "";
		this.fornoyd = 0;
		this.noytral = 0;
		this.misfornoyd = 0;
	}

	public AktivitetTilbakemelding(String navn, Integer fornoyd, Integer noytral, Integer misfornoyd) {
		super();
		this.navn = navn;
		this.fornoyd = fornoyd;
		this.noytral = noytral;
		this.misfornoyd = misfornoyd;
	}

	public void incrementFornoyd() {
		fornoyd++;
	}

	public void incrementNoytral() {
		noytral++;
	}

	public void incrementMisfornoyd() {
		misfornoyd++;
	}

	public String getNavn() {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}

	public Integer getFornoyd() {
		return fornoyd;
	}

	public void setFornoyd(Integer fornoyd) {
		this.fornoyd = fornoyd;
	}

	public Integer getNoytral() {
		return noytral;
	}

	public void setNoytral(Integer noytral) {
		this.noytral = noytral;
	}

	public Integer getMisfornoyd() {
		return misfornoyd;
	}

	public void setMisfornoyd(Integer misfornoyd) {
		this.misfornoyd = misfornoyd;
	}
	
	/**
	 * Returnerer en streng med tid, antall fornøyd, antall nøytral og antall misfornøyde for den formaterte tilbakemeldingsobjektet
	 */
	public String toString() {
		return ("Navn: " + this.navn.toString() + " Fornøyd: " + this.fornoyd + " Nøytral: " + this.noytral + " Misfornøyd: " + this.misfornoyd);
	}
}
