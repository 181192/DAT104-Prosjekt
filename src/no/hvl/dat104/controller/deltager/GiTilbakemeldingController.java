package no.hvl.dat104.controller.deltager;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hvl.dat104.controller.JspMappings;
import no.hvl.dat104.dataaccess.IEventEAO;
import no.hvl.dat104.dataaccess.ITilbakemeldingEAO;
import no.hvl.dat104.model.Event;
import no.hvl.dat104.model.Tilbakemelding;
import no.hvl.dat104.util.DatoUtil;

/**
 * Servlet implementation class GiTilbakemeldingController
 */
public class GiTilbakemeldingController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	request.getRequestDispatcher(JspMappings.GITILBAKEMELDING_JSP).forward(request, response);

	}
	
    @EJB
    private ITilbakemeldingEAO iTilbakemeldingEAO;
    @EJB
    private IEventEAO iEventEAO;
    
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		lastOppTilbakemelding(request);
		response.sendRedirect("gitilbakemelding");
	}

	private void lastOppTilbakemelding(HttpServletRequest request) {
		Tilbakemelding tilbakemelding = new Tilbakemelding(request.getParameter("tilbakemelding"), (Event) request.getSession(true).getAttribute("event"), DatoUtil.lagCurrentTimestamp());
		iTilbakemeldingEAO.leggTilTilbakemelding(tilbakemelding);
	}

}
