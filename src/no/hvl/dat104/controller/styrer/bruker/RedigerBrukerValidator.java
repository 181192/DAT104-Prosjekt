package no.hvl.dat104.controller.styrer.bruker;

import javax.servlet.http.HttpServletRequest;

import no.hvl.dat104.model.Bruker;
import no.hvl.dat104.util.ValidatorUtil;

public class RedigerBrukerValidator {
	private String fornavn;
	private String etternavn;
	private String mail;
	private String salt;
	private String gammeltPassord;
	private String nyttPassord;

	private String fornavnFeilmelding;
	private String etternavnFeilmelding;
	private String mailFeilmelding;
	private String gammeltPassordFeilmelding;
	private String nyttPassordFeilmelding;

	public RedigerBrukerValidator(Bruker b) {
		fornavn = b.getFornavn();
		etternavn = b.getEtternavn();
		mail = b.getMail();
		gammeltPassord = b.getPassord();
		salt = b.getSalt();
	}

	public RedigerBrukerValidator(HttpServletRequest request) {
		fornavn = ValidatorUtil.escapeHtml(request.getParameter("fornavn"));
		etternavn = ValidatorUtil.escapeHtml(request.getParameter("etternavn"));
		mail = ValidatorUtil.escapeHtml(request.getParameter("mail"));
		salt = ValidatorUtil.escapeHtml(request.getParameter("salt"));
		gammeltPassord = ValidatorUtil.escapeHtml(request.getParameter("gammeltpassord"));
		nyttPassord = ValidatorUtil.escapeHtml(request.getParameter("nyttpassord"));

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

	public boolean erGammeltPassordGyldig() {
		return ValidatorUtil.isNotNull0(gammeltPassord);
	}

	public boolean erNyttPassordGyldig() {
		return ValidatorUtil.isNotNull0(nyttPassord);
	}

	public boolean erMailUnik(Bruker b) {
		if (b != null) {
			return false;
		}
		return true;
	}

	public boolean erAlleDataGyldig() {
		return erFornavnGyldig() && erEtternavnGyldig() && erMailGyldig();
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
		if (!erGammeltPassordGyldig()) {
			gammeltPassord = "";
			gammeltPassordFeilmelding = "Passord er ikke gyldig";
		}
		if (!erNyttPassordGyldig()) {
			nyttPassord = "";
			nyttPassordFeilmelding = "Passord er ikke gyldig";
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

	public String getGammeltPassord() {
		return gammeltPassord;
	}

	public void setGammeltPassord(String gammeltPassord) {
		this.gammeltPassord = gammeltPassord;
	}

	public String getNyttPassord() {
		return nyttPassord;
	}

	public void setNyttPassord(String nyttPassord) {
		this.nyttPassord = nyttPassord;
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

	public String getGammeltPassordFeilmelding() {
		return gammeltPassordFeilmelding;
	}

	public void setGammeltPassordFeilmelding(String gammeltPassordFeilmelding) {
		this.gammeltPassordFeilmelding = gammeltPassordFeilmelding;
	}

	public String getNyttPassordFeilmelding() {
		return nyttPassordFeilmelding;
	}

	public void setNyttPassordFeilmelding(String nyttPassordFeilmelding) {
		this.nyttPassordFeilmelding = nyttPassordFeilmelding;
	}

}
