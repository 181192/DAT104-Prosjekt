package no.hvl.dat104.controller.styrer.bruker;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hvl.dat104.controller.UrlMappings;
import no.hvl.dat104.util.FlashUtil;
import no.hvl.dat104.util.InnloggingUtil;

/**
 * Servlet implementation class LoggUtController
 */
public class LoggUtController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		InnloggingUtil.loggUt(request);
		FlashUtil.Flash(request, "success", "Du er nå logget ut! Velkommen tilbake!");
		response.sendRedirect(UrlMappings.LOGGINN_URL);
	}
}
