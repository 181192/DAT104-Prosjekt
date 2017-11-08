package no.hvl.dat104.controller.styrer.bruker;

import javax.servlet.http.HttpServletRequest;

import no.hvl.dat104.model.Bruker;
import no.hvl.dat104.util.SHA;
import no.hvl.dat104.util.ValidatorUtil;

public class InnloggingValidator {
	private String mail;
	private String passord;
	private String mailFeilmelding;
	private String passordFeilmelding;
	private Bruker bruker;

	public InnloggingValidator() {

	}

	public InnloggingValidator(HttpServletRequest request) {
		mail = ValidatorUtil.escapeHtml(request.getParameter("mail"));
		passord = ValidatorUtil.escapeHtml(request.getParameter("passord"));
	}

	public boolean erMailGyldig() {
		return ValidatorUtil.isNotNull0(mail) && ValidatorUtil.isValidMail(mail);
	}
	
	public boolean erPassordGyldig() {
		return ValidatorUtil.isNotNull0(passord);
	}

	private boolean erMailRegistrert() {
		return bruker != null;
	}

	public boolean erPassordRett() {
		if (!erMailGyldig() || !erMailRegistrert()) {
			return false;
		}
		if (bruker != null) {
			String hashetPassord = SHA.hashPassord(passord, bruker.getSalt());
			String brukerPassord = bruker.getPassord();
			return brukerPassord.equals(hashetPassord);
		}
		return false;
	}

	public void settOppFeilmeldinger(HttpServletRequest request) {
		if (!erPassordRett()) {
			passord = "";
			passordFeilmelding = "Passordet er feil";
		}
		if (!erMailGyldig()) {
			mail = "";
			mailFeilmelding = "Mailadressen er ikke gyldig";
			passord = "";
			passordFeilmelding = "";
		} 
		if (!erMailRegistrert()) {
			mail = "";
			mailFeilmelding = "Mailadressen er ikke registrert på en bruker";
			passord = "";
			passordFeilmelding = "";
		}
	}

	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * @param mail
	 *            the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * @return the passord
	 */
	public String getPassord() {
		return passord;
	}

	/**
	 * @param passord
	 *            the passord to set
	 */
	public void setPassord(String passord) {
		this.passord = passord;
	}

	/**
	 * @return the mailFeilmelding
	 */
	public String getMailFeilmelding() {
		return mailFeilmelding;
	}

	/**
	 * @param mailFeilmelding
	 *            the mailFeilmelding to set
	 */
	public void setMailFeilmelding(String mailFeilmelding) {
		this.mailFeilmelding = mailFeilmelding;
	}

	/**
	 * @return the passordFeilmelding
	 */
	public String getPassordFeilmelding() {
		return passordFeilmelding;
	}

	/**
	 * @param passordFeilmelding
	 *            the passordFeilmelding to set
	 */
	public void setPassordFeilmelding(String passordFeilmelding) {
		this.passordFeilmelding = passordFeilmelding;
	}

	/**
	 * @return the bruker
	 */
	public Bruker getBruker() {
		return bruker;
	}

	/**
	 * @param bruker the bruker to set
	 */
	public void setBruker(Bruker bruker) {
		this.bruker = bruker;
	}

}
