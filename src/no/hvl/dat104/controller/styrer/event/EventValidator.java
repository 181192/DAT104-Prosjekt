package no.hvl.dat104.controller.styrer.event;

import javax.servlet.http.HttpServletRequest;

import no.hvl.dat104.util.ValidatorUtil;

public class EventValidator {
    private String tittel;
    private String dato;
    private String fra;
    private String til;
    private String hvor;
    private String beskrivelse;
    private String aktivitet;

    private String tittelFeilmelding;
    private String datoFeilmelding;
    private String fraFeilmelding;
    private String tilFeilmelding;
    private String hvorFeilmelding;
    private String beskrivelseFeilmelding;
    private String aktivitetFeilmelding;

    public EventValidator() {

    }

    public EventValidator(HttpServletRequest request) {
        tittel = ValidatorUtil.escapeHtml(request.getParameter("tittel"));
        dato =  ValidatorUtil.escapeHtml(request.getParameter("dato"));
        fra = ValidatorUtil.escapeHtml(request.getParameter("fra"));
        til = ValidatorUtil.escapeHtml(request.getParameter("til"));
        hvor = ValidatorUtil.escapeHtml(request.getParameter("hvor"));
        beskrivelse = ValidatorUtil.escapeHtml(request.getParameter("beskrivelse"));
        aktivitet = ValidatorUtil.escapeHtml(request.getParameter("aktivitet"));
    }
    private boolean erTittelGyldig() {
        return ValidatorUtil.isNotNull0(tittel);
    }
    private boolean erDatoGyldig() {
        return ValidatorUtil.isNotNull0(dato);
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
    private boolean erAktivitetGyldig() {
        return ValidatorUtil.isNotNull0(aktivitet);
    }
    public boolean erAlleDataGyldige() {
        return erTittelGyldig()&&erDatoGyldig()&&erFraGyldig()&&erTilGyldig()&&erHvorGyldig()&&erAktivitetGyldig();
    }
    public void settOppFeilmeldinger() {
        if (!erTittelGyldig()) {
        	tittel = "";
        	tittelFeilmelding = "Tittel er ikke gyldig!";
        }
        if (!erDatoGyldig()) {
        	dato = "";
        	datoFeilmelding = "Feil Dato";
        }
        if (!erFraGyldig()) {
        	fra = "";
        	fraFeilmelding = "Ugyldig";
        }
        if (!erTilGyldig()) {
        	til = "";
        	tilFeilmelding = "Ugyldig";
        }
        if (!erHvorGyldig()) {
        	hvor = "";
        	hvorFeilmelding = "Ugyldig";
        }
        if (!erAktivitetGyldig()) {
        	aktivitet = "";
        	aktivitetFeilmelding = "Ugyldig";
        }
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

    public String getAktivitet() {
        return aktivitet;
    }

    public void setAktivitet(String aktivitet) {
        this.aktivitet = aktivitet;
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

    public String getAktivitetFeilmelding() {
        return aktivitetFeilmelding;
    }

    public void setAktivitetFeilmelding(String aktivitetFeilmelding) {
        this.aktivitetFeilmelding = aktivitetFeilmelding;
    }
}
