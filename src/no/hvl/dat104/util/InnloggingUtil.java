package no.hvl.dat104.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import no.hvl.dat104.model.Bruker;

public class InnloggingUtil {
	 /**
     * Sjekker om bruker er innlogget
     * @param request
     * @return
     */
    public static boolean isInnlogget(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return (session != null)
                && (session.getAttribute("currentUser") != null);
    }
    /**
     * Returner sann om bruker er innlogget
     * @param request
     * @return
     */
    public static Bruker isInnloggetSom(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return isInnlogget(request) ?
                (Bruker) session.getAttribute("currentUser") : null;
    }
    /**
     * Logger inn som en deltager
     * @param request
     */
    public static void loggInnSomDeltager(HttpServletRequest request) {
    	loggUt(request);
        HttpSession sesjon = request.getSession(true);
        sesjon.setAttribute("deltager", "deltager");
    }
    /**
     * Logger inn som Bruker
     * @param request
     * @param b
     * @param init henter fra init parameter i xml.
     */
    public static void loggInnSomBruker(HttpServletRequest request, Bruker b, String init) {
        loggUt(request);
        HttpSession sesjon = request.getSession(true);
        sesjon.setMaxInactiveInterval(Integer.parseInt(init));
        sesjon.setAttribute("bruker", b);
    }
  
    /**
     * Denne metoden vil være for en aktivitetsstyrer.
     * Logger inn med Bruker objektet.
     * Setter currentUser som brukeren
     * @param request
     * @param b
     */
    public static void loggInnSomBruker(HttpServletRequest request, Bruker b) {
        loggUt(request);
        HttpSession sesjon = request.getSession(true);
        sesjon.setAttribute("bruker", b);
        sesjon.setMaxInactiveInterval(1200);
    }
    /**
     * Denne metoden vil være for en aktivitetsstyrer.
     * Logger inn med brukernavn som streng
     * @param request
     * @param brukernavn
     */
    public static void loggInnSom(HttpServletRequest request, String brukernavn) {
        loggUt(request);
        HttpSession sesjon = request.getSession(true);
        sesjon.setAttribute("currentUser", brukernavn);
        sesjon.setMaxInactiveInterval(1200);
    }
    /**
     * Sjekker sessionen er admin
     * @param request
     * @return
     */
    public static boolean isInnLoggetSomAdmin(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return (session != null)
                && (session.getAttribute("admin") != null);
    }
    /**
     * Logger inn som admin
     * @param request
     */
    public static void loggInnSomAdmin(HttpServletRequest request) {
        loggUt(request);
        HttpSession sesjon = request.getSession(true);
        sesjon.setAttribute("admin", "admin");
        sesjon.setMaxInactiveInterval(1200);
    }
    
    /**
     * logger deg ut!
     * @param request
     */
    public static void loggUt(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

}
