package no.hvl.dat104.controller.styrer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LagEventController
 */
public class LagEventController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	request.getRequestDispatcher("WEB-INF/views/styrer/lagevent.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//TODO
        EventValidator skjema = new EventValidator(request);
        if (skjema.erAlleDataGyldige()) {

//			Person p = new Person(skjema);
//			p.setNavn(Skjea)
//			p.setPostnr(Integer.pars)
//			personEAO.lagrePerson(p)

            request.getSession().removeAttribute("skjema");
            response.sendRedirect("Hurra.html");
        } else {
            skjema.settOppFeilmeldinger();
            request.getSession().setAttribute("skjema", skjema);
            response.sendRedirect("skjema");
        }
    }
}
