package no.hvl.dat104.controller.admin;

import static no.hvl.dat104.controller.JspMappings.ADMINISTRER_JSP;
import static no.hvl.dat104.controller.JspMappings.LANDING_JSP;
import static no.hvl.dat104.controller.JspMappings.LANDING_STYRER_JSP;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hvl.dat104.controller.Attributter;
import no.hvl.dat104.controller.UrlMappings;
import no.hvl.dat104.dataaccess.IBrukerEAO;
import no.hvl.dat104.model.Bruker;
import no.hvl.dat104.model.Rolle;
import no.hvl.dat104.util.FlashUtil;
import no.hvl.dat104.util.InnloggingUtil;

/**
 * Servlet implementation class AdministrerController
 */
public class AdministrerController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private IBrukerEAO brukerEAO;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Skriv ut en liste med brukere som ikke er godkjent
		if (!InnloggingUtil.erInnLoggetSomAdmin(request)) {
			if (!InnloggingUtil.erInnloggetSomBruker(request)) {
				// Bruker har ingen sesjon og blir derfor redirectet til index
				FlashUtil.Flash(request, "error", "Ingen tilgang.");
				request.getRequestDispatcher(LANDING_JSP).forward(request, response);
			} else {
				// Bruker er innlogget som aktivitetsstyrer og ikke admin
				FlashUtil.Flash(request, "error", "Ulovlig handling: Du er ikke administrator!");
				request.getRequestDispatcher(LANDING_STYRER_JSP).forward(request, response);
			}
		} else {
			// Bruker er innlogget som admin, list brukere
			List<Bruker> brukere = brukerEAO.alleBrukerne();
			// brukere.remove(InnloggingUtil.innloggetSomBruker(request));

			request.getSession().setAttribute("brukere", brukere);

			request.getRequestDispatcher(ADMINISTRER_JSP).forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("greit");
		if (InnloggingUtil.erInnLoggetSomAdmin(request)) {
			System.out.println("Er logget inn som admin.");
			Integer id = Integer.valueOf(request.getParameter("id"));
			Integer idrolle = Integer.valueOf(request.getParameter("rolle"));
			
			Bruker b = brukerEAO.finnBruker(id);
			System.out.println("Gammel: "+b.getIdRolle().getType());
			Rolle r = b.getIdRolle();
			if (idrolle == 1) {
				System.out.println("ADMIN");
				r.setType(Attributter.ROLLE_TYPE_ADMIN);
				b.setIdRolle(r);
			} else if (idrolle == 2) {
				System.out.println("STYRER");
				r.setType(Attributter.ROLLE_TYPE_STYRER);
				b.setIdRolle(r);
			} else {
				System.out.println("FEIL");
				FlashUtil.Flash(request, "error", "Noe gikk galt.");
			}
			System.out.println("Ny: " + b.getIdRolle().getType());
			brukerEAO.oppdaterBruker(b);
			System.out.println(brukerEAO.finnBruker(b.getId()).getIdRolle().getType());
			FlashUtil.Flash(request, "success",
					"Suksess! Rollen til " + b.getFornavn() + " " + b.getEtternavn() + " ble endret.");
		} else {
			FlashUtil.Flash(request, "error", "Ingen tilgang.");
			request.getRequestDispatcher(LANDING_JSP).forward(request, response);
		}
	}

}
