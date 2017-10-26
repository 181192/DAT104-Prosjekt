package no.hvl.dat104.controller.deltager;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hvl.dat104.dataaccess.ITilbakemeldingEAO;
import no.hvl.dat104.model.Tilbakemelding;

/**
 * Servlet implementation class GiTilbakemeldingController
 */
public class GiTilbakemeldingController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	request.getRequestDispatcher("WEB-INF/views/deltager/gitilbakemelding.jsp").forward(request, response);

	}
	
    @EJB
    private ITilbakemeldingEAO TilbakemeldingEAO;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Tilbakemelding tilbakemelding = new Tilbakemelding(request.getParameter("tilbakemelding"), null);
		TilbakemeldingEAO.leggTilTilbakemelding(tilbakemelding);
		response.sendRedirect("liveevent");
	}

}
