package no.hvl.dat104.controller.styrer;

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

	private boolean erTittelGyldig() {
		return ValidatorUtil.isNotNull0(tittel);
	}

}
