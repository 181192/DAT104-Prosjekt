package no.hvl.dat104.controller.styrer.aktivitet;

import javax.servlet.http.HttpServletRequest;

import no.hvl.dat104.util.ValidatorUtil;

public class AktivitetValidator {
	private String tittel;
	private String status;

	private String tittelFeilmelding;
	private String statusFeilmelding;

	public AktivitetValidator(HttpServletRequest request) {
		tittel = ValidatorUtil.escapeHtml(request.getParameter("tittel"));
		status = ValidatorUtil.escapeHtml(request.getParameter("status"));
	}

	public boolean erTittelGyldig() {
		return ValidatorUtil.isNotNull0(tittel);
	}

	public boolean erStatusGyldig() {
		return ValidatorUtil.isNotNull0(status);
	}

	public boolean erAlleDataGyldig() {
		return erTittelGyldig() && erStatusGyldig();
	}

	public void settOppFeilmeldinger(HttpServletRequest request) {
		if (!erTittelGyldig()) {
			tittel = "";
			tittelFeilmelding = "Tittelen er ikke gyldig";
		}
		if (!erStatusGyldig()) {
			status = "";
			statusFeilmelding = "Statusen er ikke gyldig";
		}
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

	/**
	 * @return the tittelFeilmelding
	 */
	public String getTittelFeilmelding() {
		return tittelFeilmelding;
	}

	/**
	 * @param tittelFeilmelding
	 *            the tittelFeilmelding to set
	 */
	public void setTittelFeilmelding(String tittelFeilmelding) {
		this.tittelFeilmelding = tittelFeilmelding;
	}

	/**
	 * @return the statusFeilmelding
	 */
	public String getStatusFeilmelding() {
		return statusFeilmelding;
	}

	/**
	 * @param statusFeilmelding
	 *            the statusFeilmelding to set
	 */
	public void setStatusFeilmelding(String statusFeilmelding) {
		this.statusFeilmelding = statusFeilmelding;
	}

}
