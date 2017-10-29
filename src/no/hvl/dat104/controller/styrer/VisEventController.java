package no.hvl.dat104.controller.styrer;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hvl.dat104.dataaccess.IAktivitetEAO;
import no.hvl.dat104.dataaccess.IEventEAO;
import no.hvl.dat104.model.Aktivitet;
import no.hvl.dat104.model.Event;

/**
 * Servlet implementation class VisEventController
 */

public class VisEventController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
    private IEventEAO iEventEAO;
	@EJB
    private IAktivitetEAO iAktivitetEAO;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id =Integer.parseInt(request.getParameter("id"));
		Event e = iEventEAO.finnEvent(id);
		Aktivitet a = e.getIdAktivitet();
		request.getSession().setAttribute("aktivitet", a);
		request.getSession().setAttribute("event", e);
		request.getRequestDispatcher("WEB-INF/views/styrer/visevent.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
