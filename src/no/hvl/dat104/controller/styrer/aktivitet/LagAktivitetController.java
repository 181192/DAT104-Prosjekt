package no.hvl.dat104.controller.styrer.aktivitet;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hvl.dat104.controller.JspMappings;
import no.hvl.dat104.controller.UrlMappings;
import no.hvl.dat104.dataaccess.IAktivitetEAO;
import no.hvl.dat104.dataaccess.IBrukerEAO;
import no.hvl.dat104.model.Aktivitet;
import no.hvl.dat104.model.Bruker;
import no.hvl.dat104.util.FlashUtil;
import no.hvl.dat104.util.InnloggingUtil;

/**
 * Servlet implementation class LagAktivitetController
 */
public class LagAktivitetController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private IBrukerEAO brukerEAO;
	@EJB
	private IAktivitetEAO aktivitetEAO;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (InnloggingUtil.erInnloggetSomBruker(request)) {
			request.getRequestDispatcher(JspMappings.LAGAKTIVITET_JSP).forward(request, response);
		} else {
			FlashUtil.Flash(request, "error", "Du er ikke innlogget");
			response.sendRedirect(UrlMappings.LOGGINN_URL);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (InnloggingUtil.erInnloggetSomBruker(request)) {
			AktivitetValidator skjema = new AktivitetValidator(request);
			Bruker b = InnloggingUtil.innloggetSomBruker(request);
			if (skjema.erAlleDataGyldig()) {
				System.out.println("data er gyldig");
				if (b != null) {
					Aktivitet a = new Aktivitet();
					a.setNavn(skjema.getTittel());
					a.setStatus(skjema.getStatus());
					a.setIdBruker(b);
					List<Aktivitet> aktiviteter = brukerEAO.finnAlleAktiviteterTilBruker(b.getId());
					aktiviteter.add(a);
					aktivitetEAO.leggTilAktivitet(a);
					FlashUtil.Flash(request, "success", "Aktiviteten ble opprettet");
					response.sendRedirect(UrlMappings.LANDING_STYRER_URL);
				} else {
					skjema.settOppFeilmeldinger(request);
					FlashUtil.Flash(request, "error", "Vennligst prøv på nytt");
					request.getSession().setAttribute("skjema", skjema);
					response.sendRedirect(UrlMappings.MINEAKTIVITETER_URL);
				}
			} else {
				skjema.settOppFeilmeldinger(request);
				FlashUtil.Flash(request, "error", "Har du husket å fylle ut alle feltene?");
				request.getSession().setAttribute("skjema", skjema);
				response.sendRedirect(UrlMappings.LAGAKTIVITET_URL);
			}
		} else {
			FlashUtil.Flash(request, "error", "Du er ikke innlogget");
			response.sendRedirect(UrlMappings.LOGGINN_URL);
		}
	}
}