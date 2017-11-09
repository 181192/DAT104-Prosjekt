package no.hvl.dat104.controller.styrer.event;

import static no.hvl.dat104.controller.JspMappings.MINEAKTIVITETER_JSP;
import static no.hvl.dat104.controller.JspMappings.MINEEVENTER_JSP;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hvl.dat104.controller.UrlMappings;
import no.hvl.dat104.dataaccess.IAktivitetEAO;
import no.hvl.dat104.model.Aktivitet;
import no.hvl.dat104.model.Event;
import no.hvl.dat104.util.EventUtil;
import no.hvl.dat104.util.InnloggingUtil;
import no.hvl.dat104.util.ValidatorUtil;

/**
 * Servlet implementation class MineEventerController
 */
public class MineEventerController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private IAktivitetEAO aktivitetEAO;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (InnloggingUtil.erInnloggetSomBruker(request)) {
			String aktivitetsId = request.getParameter("aktivitetId");
			if (!ValidatorUtil.isNotNull0(aktivitetsId)) {
				request.getRequestDispatcher(MINEAKTIVITETER_JSP).forward(request, response);
			} else {
				int id = Integer.parseInt(aktivitetsId);

				Aktivitet a = aktivitetEAO.finnAktivitet(id);
				List<Event> eventer = aktivitetEAO.finnAlleEventerTilAktivitet(a.getId());
				
				EventUtil.sorterEventer(eventer);
				
				request.getSession().setAttribute("eventer", eventer);
				request.getSession().setAttribute("aktivitet", a);
				request.getRequestDispatcher(MINEEVENTER_JSP).forward(request, response);
			}
		} else {
			response.sendRedirect(UrlMappings.LOGGINN_URL);
		}
	}
}
