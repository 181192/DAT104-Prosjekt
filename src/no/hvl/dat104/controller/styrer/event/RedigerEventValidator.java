package no.hvl.dat104.controller.styrer.event;

import javax.servlet.http.HttpServletRequest;

import no.hvl.dat104.model.Event;
import no.hvl.dat104.util.ValidatorUtil;

public class RedigerEventValidator {
	private String tittel;
	private String dato;
	private String fra;
	private String til;
	private String hvor;
	private String beskrivelse;

	private String tittelFeilmelding;
	private String datoFeilmelding;
	private String fraFeilmelding;
	private String tilFeilmelding;
	private String hvorFeilmelding;
	private String beskrivelseFeilmelding;
	private String framindreennminFeilmeilding;

	public RedigerEventValidator(Event e) {
		tittel = e.getNavn();
		fra = e.getTidFra().toString().substring(10, 16);
		til = e.getTidTil().toString().substring(10, 16);
		hvor = e.getSted();
		beskrivelse = e.getBeskrivelse();
		dato = e.getTidFra().toString().substring(0,10);
	}
	public RedigerEventValidator(HttpServletRequest request) {
		tittel = ValidatorUtil.escapeHtml(request.getParameter("tittel"));
		dato = ValidatorUtil.escapeHtml(request.getParameter("dato"));
		fra = ValidatorUtil.escapeHtml(request.getParameter("fra"));
		til = ValidatorUtil.escapeHtml(request.getParameter("til"));
		hvor = ValidatorUtil.escapeHtml(request.getParameter("hvor"));
		beskrivelse = ValidatorUtil.escapeHtml(request.getParameter("beskrivelse"));
	}
	private boolean erFraMindreEnnTilGyilding() {
		int fraint = 0;
		int tilint = 0;
		if(erFraGyldig() && erTilGyldig()) {
			fraint = Integer.parseInt(fra.substring(0,2)+fra.substring(3,5));
	        tilint = Integer.parseInt(til.substring(0,2)+til.substring(3,5));
	        return fraint < tilint;
		}
		return fraint == tilint;
	}

	private boolean erBeskrivelseGyldig() {
		return ValidatorUtil.isNotNull0(beskrivelse);
	}

	private boolean erTittelGyldig() {
		return ValidatorUtil.isNotNull0(tittel);
	}

	private boolean erDatoGyldig() {
		return ValidatorUtil.isNotNull0(dato) && ValidatorUtil.isValidDateFormat(dato);
	}

	private boolean erFraGyldig() {
		return ValidatorUtil.isValidTimeFormat(fra);
	}

	private boolean erTilGyldig() {
		return ValidatorUtil.isValidTimeFormat(til);
	}

	private boolean erHvorGyldig() {
		return ValidatorUtil.isNotNull0(hvor);
	}


	public boolean erAlleDataGyldige() {
		return erTittelGyldig() && erDatoGyldig() && erFraGyldig() && erTilGyldig() && erHvorGyldig()
				&& erBeskrivelseGyldig()  && erFraMindreEnnTilGyilding();
	}

	public void settOppFeilmeldinger() {
		if (!erTittelGyldig()) {
			tittel = "";
			tittelFeilmelding = "Kan ikke være tom";
		}
		if (!erDatoGyldig()) {
			dato = "";
			datoFeilmelding = "Feil Dato";
		}
		if (!erFraGyldig()) {
			fra = "";
			fraFeilmelding = "Ugyldig: Tid må være på formatet HH:mm";
		}
		if (!erTilGyldig()) {
			til = "";
			tilFeilmelding = "Ugyldig: Tid må være på formatet HH:mm";
		}
		if (!erHvorGyldig()) {
			hvor = "";
			hvorFeilmelding = "Ops! Tomt";
		}
		if (!erBeskrivelseGyldig()) {
			beskrivelse = "";
			beskrivelseFeilmelding = "Ops! Tomt";
		}
		if(!erFraMindreEnnTilGyilding()) {
			til = "";
			framindreennminFeilmeilding = "Fra kan ikke være mindre enn til";
		}
	}
	public String getFramindreennminFeilmeilding() {
		return framindreennminFeilmeilding;
	}

	public void setFramindreennminFeilmeilding(String framindreennminFeilmeilding) {
		this.framindreennminFeilmeilding = framindreennminFeilmeilding;
	}

	public String getTittel() {
		return tittel;
	}

	public void setTittel(String tittel) {
		this.tittel = tittel;
	}

	public String getDato() {
		return dato;
	}

	public void setDato(String dato) {
		this.dato = dato;
	}

	public String getFra() {
		return fra;
	}

	public void setFra(String fra) {
		this.fra = fra;
	}

	public String getTil() {
		return til;
	}

	public void setTil(String til) {
		this.til = til;
	}

	public String getHvor() {
		return hvor;
	}

	public void setHvor(String hvor) {
		this.hvor = hvor;
	}

	public String getBeskrivelse() {
		return beskrivelse;
	}

	public void setBeskrivelse(String beskrivelse) {
		this.beskrivelse = beskrivelse;
	}

	public String getTittelFeilmelding() {
		return tittelFeilmelding;
	}

	public void setTittelFeilmelding(String tittelFeilmelding) {
		this.tittelFeilmelding = tittelFeilmelding;
	}

	public String getDatoFeilmelding() {
		return datoFeilmelding;
	}

	public void setDatoFeilmelding(String datoFeilmelding) {
		this.datoFeilmelding = datoFeilmelding;
	}

	public String getFraFeilmelding() {
		return fraFeilmelding;
	}

	public void setFraFeilmelding(String fraFeilmelding) {
		this.fraFeilmelding = fraFeilmelding;
	}

	public String getTilFeilmelding() {
		return tilFeilmelding;
	}

	public void setTilFeilmelding(String tilFeilmelding) {
		this.tilFeilmelding = tilFeilmelding;
	}

	public String getHvorFeilmelding() {
		return hvorFeilmelding;
	}

	public void setHvorFeilmelding(String hvorFeilmelding) {
		this.hvorFeilmelding = hvorFeilmelding;
	}

	public String getBeskrivelseFeilmelding() {
		return beskrivelseFeilmelding;
	}

	public void setBeskrivelseFeilmelding(String beskrivelseFeilmelding) {
		this.beskrivelseFeilmelding = beskrivelseFeilmelding;
	}


}
