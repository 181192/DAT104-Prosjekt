package no.hvl.dat104.controller.deltager;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.sql.Timestamp;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hvl.dat104.dataaccess.ITilbakemeldingEAO;
import no.hvl.dat104.model.Tilbakemelding;
import no.hvl.dat104.dataaccess.IEventEAO;

import no.hvl.dat104.util.DatoUtil;

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
    private ITilbakemeldingEAO iTilbakemeldingEAO;
    @EJB
    private IEventEAO iEventEAO;
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		lastOppTilbakemelding(request);
		response.sendRedirect("gitilbakemelding");
	}

	private void lastOppTilbakemelding(HttpServletRequest request) {
		Tilbakemelding tilbakemelding = new Tilbakemelding(request.getParameter("tilbakemelding"), iEventEAO.finnEvent(1), DatoUtil.lagCurrentTimestamp());
		iTilbakemeldingEAO.leggTilTilbakemelding(tilbakemelding);
	}

}
