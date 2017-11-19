package no.hvl.dat104.controller.importer;

import java.sql.Timestamp;

public class EventSetup {
	private String dato;
	private Timestamp tidFra;
	private Timestamp tidTil;
	private String aktivitet;
	private String navn;
	private String beskrivelse;
	private String sted;
	
	public EventSetup(String dato, Timestamp tidFra, Timestamp tidTil, String navn, String beskrivelse, String sted, String aktivitet) {
		super();
		this.dato = dato;
		this.tidFra = tidFra;
		this.aktivitet = aktivitet;
		this.navn = navn;
		this.beskrivelse = beskrivelse;
		this.sted = sted;
		this.tidTil = tidTil;
	}
	

	public Timestamp getTidTil() {
		return tidTil;
	}


	public void setTidTil(Timestamp tidTil) {
		this.tidTil = tidTil;
	}


	public String getDato() {
		return dato;
	}

	public void setDato(String dato) {
		this.dato = dato;
	}

	public Timestamp getTidFra() {
		return tidFra;
	}

	public void setTidFra(Timestamp tidFra) {
		this.tidFra = tidFra;
	}

	public String getAktivitet() {
		return aktivitet;
	}

	public void setAktivitet(String aktivitet) {
		this.aktivitet = aktivitet;
	}

	public String getNavn() {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}

	public String getBeskrivelse() {
		return beskrivelse;
	}

	public void setBeskrivelse(String beskrivelse) {
		this.beskrivelse = beskrivelse;
	}

	public String getSted() {
		return sted;
	}

	public void setSted(String sted) {
		this.sted = sted;
	}
	
	
	
	

}
