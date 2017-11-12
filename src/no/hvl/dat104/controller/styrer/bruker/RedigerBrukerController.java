package no.hvl.dat104.controller.styrer.bruker;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hvl.dat104.controller.Attributter;
import no.hvl.dat104.controller.JspMappings;
import no.hvl.dat104.controller.UrlMappings;
import no.hvl.dat104.dataaccess.IBrukerEAO;
import no.hvl.dat104.model.Bruker;
import no.hvl.dat104.util.FlashUtil;
import no.hvl.dat104.util.InnloggingUtil;
import no.hvl.dat104.util.SHA;

/**
 * Servlet implementation class RedigerBrukerController
 */

public class RedigerBrukerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private IBrukerEAO brukerEAO;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Bruker b = (Bruker) request.getSession().getAttribute(Attributter.BRUKER);
		if (InnloggingUtil.erInnloggetSomBruker(request)) {
			if (b != null) {
				request.getSession().setAttribute(Attributter.BRUKER, b);
				request.getRequestDispatcher(JspMappings.REDIGERBRUKER_JSP).forward(request, response);
				request.getSession().removeAttribute("redigerBrukerSkjema");
			} else {
				FlashUtil.Flash(request, "error", "Noe gikk galt, prøv på nytt");
				response.sendRedirect(UrlMappings.LANDING_STYRER_URL);
			}
		} else {
			FlashUtil.Flash(request, "error", "Du er ikke innlogget");
			response.sendRedirect(UrlMappings.LOGGINN_URL);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (InnloggingUtil.erInnloggetSomBruker(request)) {
			RedigerBrukerValidator skjema = new RedigerBrukerValidator(request);
			Bruker b = (Bruker) request.getSession().getAttribute(Attributter.BRUKER);
			if (skjema.erAlleDataGyldig()) {
				if (b != null) {
					boolean ok = true;
					Integer id = b.getId();
					if (!b.getMail().equals(skjema.getMail())) {
						if (skjema.erMailUnik(brukerEAO.finnBrukerPaaEmail(skjema.getMail()))) {
							brukerEAO.endreMailPaaBruker(id, skjema.getMail());
						} else {
							ok = false;
							skjema.setMailFeilmelding("Denne mailadressen er allede i bruk");
							FlashUtil.Flash(request, "error", "Mailadresse allerede i bruk");
						}
					}
					if (skjema.erGammeltPassordGyldig() && skjema.erNyttPassordGyldig()) {
						String gammeltPassord = skjema.getGammeltPassord();
						gammeltPassord = SHA.hashPassord(gammeltPassord, b.getSalt());
						if (gammeltPassord.equals(b.getPassord())) {
							setOppSalt(b);
							String passord = SHA.hashPassord(skjema.getNyttPassord(), b.getSalt());
							brukerEAO.endreSaltPaaBruker(id, b.getSalt());
							brukerEAO.endrePassordPaaBruker(id, passord);
						} else {
							ok = false;
							FlashUtil.Flash(request, "error", "Passordene stemmer ikke");
						}
					}
					oppdaterBruker(request, response, b, skjema);
					if (ok) {
						FlashUtil.Flash(request, "success",
								"Brukeren " + b.getFornavn() + " " + b.getEtternavn() + " er oppdatert!");
					}
					request.getSession().setAttribute("redigerBrukerSkjema", skjema);
					response.sendRedirect(UrlMappings.REDIGERBRUKER_URL);

				} else {
					FlashUtil.Flash(request, "error", "Beklager, noe gikk galt");
					response.sendRedirect(UrlMappings.REDIGERBRUKER_URL);
				}
			} else {
				skjema.settOppFeilmeldinger(request);
				request.getSession().setAttribute("redigerBrukerSkjema", skjema);
				response.sendRedirect(UrlMappings.REDIGERBRUKER_URL);
			}
		} else {
			FlashUtil.Flash(request, "error", "Brukeren er ikke innlogget");
			response.sendRedirect(UrlMappings.LOGGINN_URL);
		}
	}

	public void setOppSalt(Bruker b) {
		try {
			byte[] salt = SHA.getSalt();
			b.setSalt(Arrays.toString(salt));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	public void oppdaterBruker(HttpServletRequest request, HttpServletResponse response, Bruker b,
			RedigerBrukerValidator skjema) {
		Integer id = b.getId();
		brukerEAO.endreEtternavnPaaBruker(id, skjema.getEtternavn());
		brukerEAO.endreFornavnPaaBruker(id, skjema.getFornavn());
		b = brukerEAO.finnBruker(id);
		request.getSession().setAttribute(Attributter.BRUKER, b);
	}
}
