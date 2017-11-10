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
import no.hvl.dat104.dataaccess.IBrukerEAO;
import no.hvl.dat104.model.Aktivitet;
import no.hvl.dat104.model.Bruker;
import no.hvl.dat104.model.Event;
import no.hvl.dat104.util.EventUtil;
import no.hvl.dat104.util.FlashUtil;
import no.hvl.dat104.util.InnloggingUtil;
import no.hvl.dat104.util.ValidatorUtil;

/**
 * Servlet implementation class MineEventerController
 */
public class MineEventerController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private IAktivitetEAO aktivitetEAO;
	@EJB
	private IBrukerEAO brukerEAO;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (InnloggingUtil.erInnloggetSomBruker(request)) {
			String aktivitetsId = request.getParameter("aktivitetId");
			if (!ValidatorUtil.isNotNull0(aktivitetsId)) {
				request.getRequestDispatcher(MINEAKTIVITETER_JSP).forward(request, response);
			} else {
				int aktivitetId = Integer.parseInt(aktivitetsId);
				Bruker b = InnloggingUtil.innloggetSomBruker(request);
				if (brukerEAO.eierBrukerAktivitet(b.getId(), aktivitetId)) {
					Aktivitet a = aktivitetEAO.finnAktivitet(aktivitetId);
					List<Event> eventer = aktivitetEAO.finnAlleEventerTilAktivitet(a.getId());
					EventUtil.sorterEventer(eventer);

					request.getSession().setAttribute("eventer", eventer);
					request.getSession().setAttribute("aktivitet", a);
					request.getRequestDispatcher(MINEEVENTER_JSP).forward(request, response);
				} else {
					FlashUtil.Flash(request, "error", "Fant ikke aktiviteten");
					response.sendRedirect(UrlMappings.MINEAKTIVITETER_URL);
				}
			}
		} else {
			FlashUtil.Flash(request, "error", "Du er ikke innlogget");
			response.sendRedirect(UrlMappings.LOGGINN_URL);
		}
	}
}
