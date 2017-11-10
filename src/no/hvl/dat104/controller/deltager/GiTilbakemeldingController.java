package no.hvl.dat104.controller.deltager;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hvl.dat104.controller.JspMappings;
import no.hvl.dat104.controller.UrlMappings;
import no.hvl.dat104.dataaccess.IEventEAO;
import no.hvl.dat104.dataaccess.ILiveTilbakemeldingEAO;
import no.hvl.dat104.dataaccess.ITilbakemeldingEAO;
import no.hvl.dat104.util.InnloggingUtil;

/**
 * Servlet implementation class GiTilbakemeldingController
 */
public class GiTilbakemeldingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    @EJB
    private ITilbakemeldingEAO tilbakemeldingEAO;
    @EJB
    private IEventEAO eventEAO;
    @EJB
    private ILiveTilbakemeldingEAO liveTilbakemeldingEAO; 

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (InnloggingUtil.erInnloggetSomDeltager(request) && !GiTilbakemeldingHjelpeklasse.avgittStemmeTidligere(request)) {
			request.getRequestDispatcher(JspMappings.GITILBAKEMELDING_JSP).forward(request, response);
		}
		else {
			response.sendRedirect(UrlMappings.DELTAEVENT_URL);// index
		}
	}
	
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	if (GiTilbakemeldingHjelpeklasse.eventHarStartet(request, eventEAO) && !GiTilbakemeldingHjelpeklasse.maaVente(request, 10)) {
        	GiTilbakemeldingHjelpeklasse.lastOppTilbakemelding(request, liveTilbakemeldingEAO, tilbakemeldingEAO, response, eventEAO);
    	}
    	else {
    		GiTilbakemeldingHjelpeklasse.settFeilmelding(request);
    		response.sendRedirect("gitilbakemelding");
    	}
	}
}
