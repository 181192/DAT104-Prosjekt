package no.hvl.dat104.util;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;


import no.hvl.dat104.dataaccess.IBrukerEAO;

public class InnloggingValidator {
	private String mail;
	private String passord;
	private String mailFeilmelding;
	private String passordFeilmelding;
	
	@EJB
	private IBrukerEAO brukerEAO;
	
	public InnloggingValidator() {
		
	}
	
	public InnloggingValidator(HttpServletRequest request) {
		mail = ValidatorUtil.escapeHtml(request.getParameter("mail"));
		passord = ValidatorUtil.escapeHtml(request.getParameter("passord"));
	}
	
	private boolean erMailGyldig() {
		return ValidatorUtil.isNotNull0(mail) && ValidatorUtil.isValidMail(mail);
	}
	
	private boolean erMailRegistrert() {
		return brukerEAO.finnBruker(mail) != null;
	}
	
	private boolean erPassordRett() {
		if (!erMailGyldig() || !erMailRegistrert()) {
			return false;
		} if (brukerEAO.finnBruker(mail) != null) {
			return brukerEAO.finnBruker(mail).getPassord().equals(passord);	
		}
		return false;
	}
	
	public boolean gyldigInnlogging() {
		return erMailGyldig() && erMailRegistrert() && erPassordRett();
	}
	
	public void settOppFeilmeldinger(HttpServletRequest request) {
		if (!erPassordRett()) {
			passord = "";
			passordFeilmelding = "Passordet er feil";
		}
		
		if (!erMailGyldig()) {
			mail = "";
			mailFeilmelding = "Mailadressen er ikke gyldig";
		} else if (!erMailRegistrert()) {
			mail = "";
			mailFeilmelding = "Mailadressen er ikke registrert på en bruker";
			passordFeilmelding = ""; //Trenger ikke feilmelding her dersom mail ikke er registrert
		}
	}

	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * @param mail the mail to set
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
	 * @param passord the passord to set
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
	 * @param mailFeilmelding the mailFeilmelding to set
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
	 * @param passordFeilmelding the passordFeilmelding to set
	 */
	public void setPassordFeilmelding(String passordFeilmelding) {
		this.passordFeilmelding = passordFeilmelding;
	}

}
