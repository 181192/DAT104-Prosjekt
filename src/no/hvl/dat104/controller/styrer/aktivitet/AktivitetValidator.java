package no.hvl.dat104.controller.styrer.aktivitet;

import javax.servlet.http.HttpServletRequest;

import no.hvl.dat104.util.ValidatorUtil;

public class AktivitetValidator {
	private String tittel;
	private String status;

	public AktivitetValidator() {

	}

	public AktivitetValidator(HttpServletRequest request) {
		tittel = ValidatorUtil.escapeHtml(request.getParameter("tittel"));
		status = "planlagt";
	}

	public boolean erTittelGyldig() {
		return ValidatorUtil.isNotNull0(tittel);
	}

	/**
	 * @return the tittel
	 */
	public String getTittel() {
		return tittel;
	}

	/**
	 * @param tittel
	 *            the tittel to set
	 */
	public void setTittel(String tittel) {
		this.tittel = tittel;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

}
