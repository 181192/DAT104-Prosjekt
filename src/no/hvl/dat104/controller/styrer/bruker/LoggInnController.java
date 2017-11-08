package no.hvl.dat104.controller.styrer.bruker;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hvl.dat104.controller.JspMappings;
import no.hvl.dat104.controller.UrlMappings;
import no.hvl.dat104.dataaccess.IBrukerEAO;
import no.hvl.dat104.model.Bruker;
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
		InnloggingValidator skjema = new InnloggingValidator(request);
		if (skjema.erMailGyldig() && skjema.erPassordGyldig()) {
			Bruker b = brukerEAO.finnBrukerPaaEmail(skjema.getMail());
			if (b != null) {
				skjema.setBruker(b);
				if (skjema.erPassordRett()) {
					InnloggingUtil.loggInnSomBruker(request, b);
					response.sendRedirect(UrlMappings.LANDING_STYRER_URL);
				} else {
					skjema.settOppFeilmeldinger(request);
					request.getSession().setAttribute("skjema", skjema);
					response.sendRedirect(UrlMappings.LOGGINN_URL);
				}
			} else {
				skjema.settOppFeilmeldinger(request);
				request.getSession().setAttribute("skjema", skjema);
				response.sendRedirect(UrlMappings.LOGGINN_URL);
				
			}
		} else {
			skjema.settOppFeilmeldinger(request);
			request.getSession().setAttribute("skjema", skjema);
			response.sendRedirect(UrlMappings.LOGGINN_URL);
		}
	}

}
