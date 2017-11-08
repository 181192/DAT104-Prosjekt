package no.hvl.dat104.controller.styrer.bruker;

import javax.servlet.http.HttpServletRequest;

import no.hvl.dat104.model.Bruker;
import no.hvl.dat104.util.ValidatorUtil;

public class InnloggingValidator {
	private String mail;
	private String passord;
	private String mailFeilmelding;
	private String passordFeilmelding;

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

	private boolean erMailRegistrert(Bruker b) {
		return b != null;
	}

	public boolean erPassordRett(Bruker b) {
		if (!erMailGyldig() || !erMailRegistrert(b)) {
			return false;
		}
		if (b != null) {
			return b.getPassord().equals(passord);
		}
		return false;
	}

	public void settOppFeilmeldinger(HttpServletRequest request) {
		if (!erPassordGyldig()) {
			passord = "";
			passordFeilmelding = "Passordet er feil";
		}

		if (!erMailGyldig()) {
			mail = "";
			mailFeilmelding = "Mailadressen er ikke gyldig";
			passord = "";
			passordFeilmelding = "";
		} 
//		else if (!erMailRegistrert(b)) {
//			mail = "";
//			mailFeilmelding = "Mailadressen er ikke registrert p� en bruker";
//			passordFeilmelding = ""; // Trenger ikke feilmelding her dersom mail ikke er registrert
//		}
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

}
