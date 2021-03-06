package no.hvl.dat104.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import no.hvl.dat104.controller.Attributter;
import no.hvl.dat104.model.Bruker;
import no.hvl.dat104.model.Event;

public class InnloggingUtil {
	/**
	 * Sjekker om bruker er innlogget
	 * 
	 * @param request
	 * @return True eller False
	 */
	public static boolean erInnloggetSomBruker(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		return (session != null) && (session.getAttribute(Attributter.BRUKER) != null);
	}

	/**
	 * Returner brukeren som er innlogget, ellers null
	 * 
	 * @param request
	 * @return Brukeren
	 */
	public static Bruker innloggetSomBruker(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		return erInnloggetSomBruker(request) ? (Bruker) session.getAttribute(Attributter.BRUKER) : null;
	}
	
	/**
	 * Sjekker om admin er innlogget
	 * 
	 * @param request
	 * @return True eller False
	 */
	public static boolean erInnloggetSomAdmin(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		return (session != null) && (session.getAttribute(Attributter.ADMIN) != null);
	}
	
	/**
	 * Returner admin som er innlogget, ellers null
	 * 
	 * @param request
	 * @return Brukeren
	 */
	public static Bruker innloggetSomAdmin(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		return erInnloggetSomAdmin(request) ? (Bruker) session.getAttribute(Attributter.ADMIN) : null;
	}

	/**
	 * Logger inn som Bruker
	 * 
	 * @param request
	 * @param b
	 * @param init
	 *            henter fra init parameter i xml.
	 */
	public static void loggInnSomBruker(HttpServletRequest request, Bruker b, String init) {
		loggUt(request);
		HttpSession sesjon = request.getSession(true);
		sesjon.setMaxInactiveInterval(Integer.parseInt(init));
		sesjon.setAttribute(Attributter.BRUKER, b);
	}

	/**
	 * Denne metoden vil v�re for en aktivitetsstyrer. Logger inn med Bruker
	 * objektet. Setter bruker som brukeren
	 * 
	 * @param request
	 * @param b Brukeren
	 */
	public static void loggInnSomBruker(HttpServletRequest request, Bruker b) {
		loggUt(request);
		HttpSession sesjon = request.getSession(true);
		sesjon.setAttribute(Attributter.BRUKER, b);
		sesjon.setMaxInactiveInterval(10800);
	}

	/**
	 * Logger inn som admin
	 * 
	 * @param request
	 */
	public static void loggInnSomAdmin(HttpServletRequest request, Bruker b) {
		loggUt(request);
		HttpSession sesjon = request.getSession(true);
		sesjon.setAttribute(Attributter.ADMIN, b);
		sesjon.setMaxInactiveInterval(1200);
	}
	/**
	 * Logger inn som admin eller bruker basert p� rettighet
	 * @param request
	 * @param b
	 */
	public static void loggPaaRettighet(HttpServletRequest request, Bruker b) {
		request.getSession().invalidate();
		HttpSession sesjon = request.getSession(true);
		if(b.getIdRolle().getType().equals(Attributter.ROLLE_TYPE_STYRER)) {
			sesjon.setAttribute(Attributter.NY_BRUKER, Attributter.NY_BRUKER);
			sesjon.setAttribute(Attributter.BRUKER, b);
		}else {
			sesjon.setAttribute(Attributter.ADMIN, b);
		}
	}

	/**
	 * Sjekker sessionen er admin
	 * 
	 * @param request
	 * @return
	 */
	public static boolean erInnLoggetSomAdmin(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		return (session != null) && (session.getAttribute(Attributter.ADMIN) != null);
	}

	/**
	 * Logger inn som en deltager
	 * 
	 * @param request
	 */
	public static void loggInnSomDeltager(HttpServletRequest request, Event event) {
		loggUt(request);
		HttpSession sesjon = request.getSession(true);
		sesjon.setAttribute("event", event);
	}

	/**
	 * Sjekker om deltager har skrivd inn kodeord / er innlogget
	 * 
	 * @param request
	 * @return boolean
	 */
	public static boolean erInnloggetSomDeltager(HttpServletRequest request) {
		HttpSession sesjon = request.getSession(false);
		if (sesjon != null) {
			return sesjon.getAttribute("event") != null;
		} else {
			return false;
		}
	}

	/**
	 * logger deg ut!
	 * 
	 * @param request
	 */
	public static void loggUt(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
	}

}
