package no.hvl.dat104.controller.styrer;

import no.hvl.dat104.model.Aktivitet;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

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
        tittel = request.getParameter("tittel");
        dato =  request.getParameter("dato");
        fra = request.getParameter("fra");
        til = request.getParameter("til");
        hvor = request.getParameter("hvor");
        beskrivelse = request.getParameter("beskrivelse");
        aktivitet = request.getParameter("aktivitet");
    }
    private boolean erTittelGyldig() {
        //TODO
        return true;
    }
    private boolean erDatoGyldig() {
        //TODO
        return true;
    }
    private boolean erFraGyldig() {
        //TODO
        return true;
    }
    private boolean erTilGyldig() {
        //TODO
        return true;
    }
    private boolean erHvorGyldig() {
        //TODO
        return true;
    }
    private boolean erBeskrivelseGyldig() {
        //TODO
        return true;
    }
    private boolean erAktivitetGyldig() {
        //TODO
        return true;
    }
    public boolean erAlleDataGyldige() {
        //TODO
        return true;
    }
    public void settOppFeilmeldinger() {
        //TODO

        /*if (!erNavnGyldig()) {
            navn = "";
            navnFeilmelding = "Navnet er ikke gyldig!";
        }
        if (!erPostnrGyldig()) {
            postnr = "";
            postnrFeilmelding = "File postsjf";
        }*/

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
