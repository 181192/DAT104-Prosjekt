package no.hvl.dat104.controller.styrer.bruker;

import javax.servlet.http.HttpServletRequest;

import no.hvl.dat104.model.Bruker;
import no.hvl.dat104.model.Rolle;
import no.hvl.dat104.util.ValidatorUtil;

public class BrukerValidator {
	private String fornavn;
	private String etternavn;
	private String mail;
	private String passord;
	private String salt;
	
	private String fornavnFeilmelding;
	private String etternavnFeilmelding;
	private String mailFeilmelding;
	private String passordFeilmelding;

	public BrukerValidator() {

	}
	
	public BrukerValidator(Bruker b) {
		fornavn = b.getFornavn();
		etternavn = b.getEtternavn();
		mail = b.getMail();
		passord = b.getPassord();
		salt = b.getSalt();
		
	}

	public BrukerValidator(HttpServletRequest request) {
		fornavn = ValidatorUtil.escapeHtml(request.getParameter("fornavn"));
		etternavn = ValidatorUtil.escapeHtml(request.getParameter("etternavn"));
		mail = ValidatorUtil.escapeHtml(request.getParameter("mail"));
		passord = ValidatorUtil.escapeHtml(request.getParameter("passord"));
		salt = ValidatorUtil.escapeHtml(request.getParameter("salt"));

	}

	private boolean erFornavnGyldig() {
		return ValidatorUtil.isNotNull0(fornavn) && ValidatorUtil.isValidfornavn(fornavn);
	}

	private boolean erEtternavnGyldig() {
		return ValidatorUtil.isNotNull0(etternavn) && ValidatorUtil.isValidetternavn(etternavn);
	}

	private boolean erMailGyldig() {
		return ValidatorUtil.isNotNull0(mail) && ValidatorUtil.isValidMail(mail);
	}

	private boolean erPassordGyldig() {
		return ValidatorUtil.isNotNull0(passord) && ValidatorUtil.isValidString(passord);
	}

	public boolean erAlleDataGyldig() {
		return erFornavnGyldig() && erEtternavnGyldig() && erMailGyldig() && erPassordGyldig();
	}

	public void settOppFeilmeldinger(HttpServletRequest request) {

		if (!erFornavnGyldig()) {
			fornavn = "";
			fornavnFeilmelding = "Fornavn er ikke gyldig";
		}
		if (!erEtternavnGyldig()) {
			etternavn = "";
			etternavnFeilmelding = "Etternavn er ikke gyldig";
		}
		if (!erMailGyldig()) {
			mail = "";
			mailFeilmelding = "Mailadressen er ikke gyldig";
		}
		if (!erPassordGyldig()) {
			passord = "";
			passordFeilmelding = "Passord er ikke gyldig";
		}

	}

	public String getFornavn() {
		return fornavn;
	}

	public void setFornavn(String fornavn) {
		this.fornavn = fornavn;
	}

	public String getEtternavn() {
		return etternavn;
	}

	public void setEtternavn(String etternavn) {
		this.etternavn = etternavn;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassord() {
		return passord;
	}

	public void setPassord(String passord) {
		this.passord = passord;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getFornavnFeilmelding() {
		return fornavnFeilmelding;
	}

	public void setFornavnFeilmelding(String fornavnFeilmelding) {
		this.fornavnFeilmelding = fornavnFeilmelding;
	}

	public String getEtternavnFeilmelding() {
		return etternavnFeilmelding;
	}

	public void setEtternavnFeilmelding(String etternavnFeilmelding) {
		this.etternavnFeilmelding = etternavnFeilmelding;
	}

	public String getMailFeilmelding() {
		return mailFeilmelding;
	}

	public void setMailFeilmelding(String mailFeilmelding) {
		this.mailFeilmelding = mailFeilmelding;
	}

	public String getPassordFeilmelding() {
		return passordFeilmelding;
	}

	public void setPassordFeilmelding(String passordFeilmelding) {
		this.passordFeilmelding = passordFeilmelding;
	}

}
