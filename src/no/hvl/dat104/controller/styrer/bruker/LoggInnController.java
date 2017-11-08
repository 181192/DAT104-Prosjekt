package no.hvl.dat104.controller.styrer.bruker;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import no.hvl.dat104.controller.JspMappings;
import no.hvl.dat104.controller.UrlMappings;
import no.hvl.dat104.dataaccess.IBrukerEAO;
import no.hvl.dat104.model.Bruker;
import no.hvl.dat104.util.FlashUtil;
import no.hvl.dat104.util.InnloggingUtil;

/**
 * Servlet implementation class LoggInnController
 */
public class LoggInnController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private IBrukerEAO brukerEAO;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher(JspMappings.LOGGINN_JSP).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// byte[] salt = SHA.getSalt();
		// String hashetPassord = get_SHA_1_SecurePassword(String passwordToHash, byte[]
		// salt)

		InnloggingValidator skjema = new InnloggingValidator(request);
		if (skjema.erMailGyldig() && skjema.erPassordGyldig()) {
			Bruker b = brukerEAO.finnBrukerPaaEmail(skjema.getMail());
			if (b != null) {
				if (skjema.erPassordRett(b)) {
					System.out.println("Brukeren " + b.getEtternavn());
					InnloggingUtil.loggInnSomBruker(request, b);
					response.sendRedirect(UrlMappings.LANDING_STYRER_URL);
				} else {
					// passord ikke korrekt
					response.sendRedirect(UrlMappings.LOGGINN_URL);
				}
			} else {
				// bruker finnes ikke
				
				response.sendRedirect(UrlMappings.LOGGINN_URL);
				
			}
		} else {
			// Skjema data ikke korrekt
			skjema.settOppFeilmeldinger(request);
			request.getSession().setAttribute("skjema", skjema);
			response.sendRedirect(UrlMappings.LOGGINN_URL);
		}
	}

}
