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

import no.hvl.dat104.dataaccess.IBrukerEAO;
import no.hvl.dat104.model.Bruker;
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
		if (InnloggingUtil.erInnLoggetSomAdmin(request)) {
			if (!InnloggingUtil.erInnloggetSomBruker(request)) {
				// Bruker har ingen sesjon og blir derfor redirectet til index
				request.getRequestDispatcher(LANDING_JSP).forward(request, response);
			} else {
				// Bruker er innlogget som aktivitetsstyrer og ikke admin
				request.getRequestDispatcher(LANDING_STYRER_JSP).forward(request, response);
			}
		} else {
			//Bruker er innlogget som admin, list brukere
			List<Bruker> brukere = brukerEAO.alleBrukerne();
			//brukere.remove(InnloggingUtil.innloggetSomBruker(request));
			
			request.getSession().setAttribute("brukere", brukere);
			
			request.getRequestDispatcher(ADMINISTRER_JSP).forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(!InnloggingUtil.erInnLoggetSomAdmin(request)) {
			System.out.println("Fikk postet");
			
			//doGet(request, response);
		}
	}

}
