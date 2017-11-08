package no.hvl.dat104.controller.styrer.aktivitet;

import static no.hvl.dat104.controller.JspMappings.MINEAKTIVITETER_JSP;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hvl.dat104.controller.UrlMappings;
import no.hvl.dat104.dataaccess.IBrukerEAO;
import no.hvl.dat104.model.Aktivitet;
import no.hvl.dat104.model.Bruker;
import no.hvl.dat104.util.InnloggingUtil;

/**
 * Servlet implementation class MineAktiviteterController
 */
public class MineAktiviteterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private IBrukerEAO brukerEAO;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Bruker b = InnloggingUtil.innloggetSomBruker(request);
		if (InnloggingUtil.erInnloggetSomBruker(request)) {
			if (b != null) {
				List<Aktivitet> a = brukerEAO.finnAlleAktiviteterTilBruker(b.getId());
				request.getSession().setAttribute("aktiviteter", a);
				request.getRequestDispatcher(MINEAKTIVITETER_JSP).forward(request, response);
			} else {
				response.sendRedirect(UrlMappings.LOGGINN_URL);
			}
		} else {
			response.sendRedirect(UrlMappings.LOGGINN_URL);
		}
	}
}
