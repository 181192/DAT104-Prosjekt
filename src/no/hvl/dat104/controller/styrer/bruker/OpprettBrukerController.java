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
import no.hvl.dat104.dataaccess.IRolleEAO;
import no.hvl.dat104.model.Bruker;
import no.hvl.dat104.util.SHA;

/**
 * Servlet implementation class OpprettBrukerController
 */
public class OpprettBrukerController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private IBrukerEAO brukerEAO;
	@EJB
	private IRolleEAO rolleEAO;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher(JspMappings.OPPRETTBRUKER_JSP).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BrukerValidator skjema = new BrukerValidator(request);
		if (skjema.erAlleDataGyldig()) {
			Bruker bruker = setOppBruker(skjema);
			if (skjema.erMailUnik(brukerEAO.finnBrukerPaaEmail(bruker.getMail()))) {
				SHA.hashPassord(bruker.getPassord(), bruker.getSalt());
				brukerEAO.leggTilBruker(bruker);
				request.getSession().removeAttribute("skjema");
				response.sendRedirect(UrlMappings.LANDING_STYRER_URL);	
			} else {
				skjema.setMailFeilmelding("Denne mailadressen er allerede registrert");
				request.getSession().setAttribute("skjema", skjema);
				response.sendRedirect(UrlMappings.OPPRETTBRUKER_URL);
			}
		} else {
			skjema.settOppFeilmeldinger(request);
			request.getSession().setAttribute("skjema", skjema);
			response.sendRedirect(UrlMappings.OPPRETTBRUKER_URL);
		}
	}
	public Bruker setOppBruker(BrukerValidator skjema) {
		Bruker bruker = new Bruker();
		bruker.setFornavn(skjema.getFornavn());
		bruker.setEtternavn(skjema.getEtternavn());
		bruker.setMail(skjema.getMail());
		bruker.setPassord(skjema.getPassord());
		bruker.setIdRolle(rolleEAO.finnRolle(Attributter.ROLLE_BRUKER));
		setOppSalt(bruker);
		return bruker;
	}
	public void setOppSalt(Bruker b) {
		try {
			byte[] salt = SHA.getSalt();
			b.setSalt(Arrays.toString(salt));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
}
