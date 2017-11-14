package no.hvl.dat104.controller.styrer.aktivitet;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hvl.dat104.controller.UrlMappings;
import no.hvl.dat104.dataaccess.IAktivitetEAO;
import no.hvl.dat104.dataaccess.IBrukerEAO;
import no.hvl.dat104.dataaccess.IEventEAO;
import no.hvl.dat104.dataaccess.IKodeordEAO;
import no.hvl.dat104.dataaccess.ILiveTilbakemeldingEAO;
import no.hvl.dat104.dataaccess.ITilbakemeldingEAO;
import no.hvl.dat104.model.Aktivitet;
import no.hvl.dat104.model.Bruker;
import no.hvl.dat104.model.Event;
import no.hvl.dat104.model.Kodeord;
import no.hvl.dat104.model.LiveTilbakemelding;
import no.hvl.dat104.model.Tilbakemelding;
import no.hvl.dat104.util.FlashUtil;
import no.hvl.dat104.util.InnloggingUtil;
import no.hvl.dat104.util.ValidatorUtil;

/**
 * Servlet implementation class SlettAktivitetController
 */
public class SlettAktivitetController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private IAktivitetEAO aktivitetEAO;
	@EJB
	private IBrukerEAO brukerEAO;
	@EJB
	private IKodeordEAO kodeEAO;
	@EJB
	private IEventEAO eventEAO;
	@EJB
	private ILiveTilbakemeldingEAO ltmEAO;
	@EJB
	private ITilbakemeldingEAO tmEAO;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (InnloggingUtil.erInnloggetSomBruker(request)) {
			String aktivitetId = ValidatorUtil.escapeHtml(request.getParameter("aktivitetId"));

			Bruker br = InnloggingUtil.innloggetSomBruker(request);

			if (ValidatorUtil.isNotNull0(aktivitetId)) {
				Integer id = Integer.parseInt(aktivitetId);
				Aktivitet a = aktivitetEAO.finnAktivitet(id);
				if (a != null) {
					List<Event> eventer = aktivitetEAO.finnAlleEventerTilAktivitet(a.getId());
					
					for(Event e:eventer) {
						if (e != null) {
							Kodeord k = null;
							List<LiveTilbakemelding> ltm = null;
							List<Tilbakemelding> tm = null;
							k = kodeEAO.finnKodeordTilEvent(e);
							ltm = eventEAO.finnAlleLiveTilbakemeldingerTilEvent(id);
							tm = eventEAO.finnAlleTilbakemeldingerTilEvent(id);
							if (k != null) {
								kodeEAO.slettKodeord(k);
								kodeEAO.slettKodeordTilEvent(e);
							}
							if (ltm != null) {
								ltmEAO.slettAlleLiveTilbakemeldingTilEvent(e);
							}
							if (tm != null) {
								tmEAO.slettAlleTilbakemeldingTilEvent(e);
							}
							eventEAO.slettEvent(e);
						}
					};
					
					aktivitetEAO.slettAktivitet(a);
					FlashUtil.Flash(request, "success", "Aktiviteten " + a.getNavn() + " er slettet!");
					response.sendRedirect(UrlMappings.MINEAKTIVITETER_URL);
				} else {
					System.out.println("den er tom");
					FlashUtil.Flash(request, "error", "Beklager, noe gikk galt");
					response.sendRedirect(UrlMappings.MINEAKTIVITETER_URL);
				}
			} else {
				FlashUtil.Flash(request, "error", "Beklager, noe gikk galt");
				response.sendRedirect(UrlMappings.MINEAKTIVITETER_URL);
			}
		} else {
			response.sendRedirect(UrlMappings.LOGGINN_URL);
		}
	}
}
