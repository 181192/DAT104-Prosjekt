package no.hvl.dat104.controller.importer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hvl.dat104.controller.JspMappings;
import no.hvl.dat104.controller.UrlMappings;
import no.hvl.dat104.dataaccess.IAktivitetEAO;
import no.hvl.dat104.dataaccess.IBrukerEAO;
import no.hvl.dat104.dataaccess.IEventEAO;
import no.hvl.dat104.model.Aktivitet;
import no.hvl.dat104.model.Bruker;
import no.hvl.dat104.model.Event;
import no.hvl.dat104.model.Status;
import no.hvl.dat104.util.DatoUtil;
import no.hvl.dat104.util.FlashUtil;
import no.hvl.dat104.util.InnloggingUtil;
import no.hvl.dat104.util.ValidatorUtil;

/**
 * Servlet implementation class ImporterKalenderController
 */
public class ImporterKalenderController extends HttpServlet {
	@EJB
	private IEventEAO eventEAO;
	@EJB
	private IBrukerEAO brukerEAO;
	@EJB
	private IAktivitetEAO aktivitetEAO;

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(InnloggingUtil.erInnloggetSomBruker(request)) {
			//List<Event> eventer = readCSV(request);
			String url = ValidatorUtil.escapeHtml(request.getParameter("url"));
			System.out.println(url);
			readCSVInternett(request, url);
			FlashUtil.Flash(request, "success", "Kalender er importert!");
			request.getRequestDispatcher(JspMappings.LANDING_STYRER_JSP).forward(request, response);;
		}else {
			response.sendRedirect(UrlMappings.LOGGINN_URL);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(InnloggingUtil.erInnloggetSomBruker(request)) {
			//List<Event> eventer = readCSV(request);
			String url = ValidatorUtil.escapeHtml(request.getParameter("url"));
			System.out.println(url);
			readCSVInternett(request, url);
			FlashUtil.Flash(request, "success", "Kalender er importert!");
			response.sendRedirect(UrlMappings.LANDING_STYRER_URL);
		}else {
			response.sendRedirect(UrlMappings.LOGGINN_URL);
		}

	}
	private void readCSVInternett(HttpServletRequest request, String linken) throws FileNotFoundException, IOException {
		URL url = new URL("https://no.timeedit.net/web/hib/db1/aistudent/ri1Y5vYyQ7f070QYZ5Q7527XZ10Q5.csv");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        if (connection.getResponseCode() == 200) {
            InputStreamReader streamReader = new InputStreamReader(connection.getInputStream(), "UTF-8");
            BufferedReader br = new BufferedReader(streamReader);
            String line = br.readLine();
            line = br.readLine() + br.readLine() + br.readLine();
            String[] fields = line.split(",");
            fields = Arrays.copyOfRange(fields, 3, fields.length);
            while ((line = br.readLine()) != null && !line.isEmpty()) {
				fields = line.split(",");
	            String dato = fields[0].substring(1);
	            String tidFra = fields[1].substring(1);
	            String tidTil = fields[3].substring(1);
	            String aktivitet = fields[6].substring(1);
	            String navn = fields[8].substring(1);
	            String beskrivelse = fields[10].substring(1);
	            String sted = fields[9].substring(1);
	            Timestamp til = null;
	            Timestamp fra = null;
				try {
					fra = DatoUtil.formaterDatoTilStamp(dato, tidFra);
					til = DatoUtil.formaterDatoTilStamp(dato, tidTil);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				Bruker b = InnloggingUtil.innloggetSomBruker(request);
				List<Aktivitet> aktiviteter = brukerEAO.finnAlleAktiviteterTilBruker(b.getId());
				Event e = lagEvent(fra, til, navn, beskrivelse, sted);
				boolean lagtTil = false;
				Aktivitet a = null;
				for(Aktivitet akt : aktiviteter) {
					if(akt != null) {
						if(akt.getNavn().toUpperCase().equals(aktivitet.toUpperCase())) {
							e.setIdAktivitet(akt);
							a = akt;
							lagtTil = true;
						}
					}
				}
				if (!lagtTil) {
					a = lagAktivitet(aktivitet);
					a.setIdBruker(b);
					aktiviteter.add(a);
					aktivitetEAO.leggTilAktivitet(a);
					e.setIdAktivitet(a);
				}
				brukerEAO.finnBrukerLeggTilEvent(b.getId(), e, a.getId());
			}
			br.close();
        }
            
	}

	private List<Event> readCSV(HttpServletRequest request) throws FileNotFoundException, IOException {
		List<Event> eventer = new ArrayList<>();
		try {
			InputStream is = getServletContext().getResourceAsStream("test2.csv");
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line = br.readLine();
			String[] fields = null;
			while ((line = br.readLine()) != null && !line.isEmpty()) {
				fields = line.split(",");
	            String dato = fields[0].substring(1);
	            String tidFra = fields[1].substring(1);
	            String tidTil = fields[3].substring(1);
	            String aktivitet = fields[6].substring(1);
	            String navn = fields[8].substring(1);
	            String beskrivelse = fields[10].substring(1);
	            String sted = fields[9].substring(1);
	            Timestamp til = null;
	            Timestamp fra = null;
				try {
					fra = DatoUtil.formaterDatoTilStamp(dato, tidFra);
					til = DatoUtil.formaterDatoTilStamp(dato, tidTil);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				Bruker b = InnloggingUtil.innloggetSomBruker(request);
				List<Aktivitet> aktiviteter = brukerEAO.finnAlleAktiviteterTilBruker(b.getId());
				Event e = lagEvent(fra, til, navn, beskrivelse, sted);
				boolean lagtTil = false;
				Aktivitet a = null;
				for(Aktivitet akt : aktiviteter) {
					if(akt != null) {
						if(akt.getNavn().toUpperCase().equals(aktivitet.toUpperCase())) {
							e.setIdAktivitet(akt);
							a = akt;
							lagtTil = true;
						}
					}
				}
				if (!lagtTil) {
					a = lagAktivitet(aktivitet);
					a.setIdBruker(b);
					aktiviteter.add(a);
					aktivitetEAO.leggTilAktivitet(a);
					e.setIdAktivitet(a);
				}
				brukerEAO.finnBrukerLeggTilEvent(b.getId(), e, a.getId());
				eventer.add(e);
			}
			br.close();
		} catch (FileNotFoundException ex) {
			System.out.println(ex);
		} catch (IOException ex) {
			System.out.println(ex);
		}
		return eventer;
	}

	private static Event lagEvent(Timestamp fra, Timestamp til, String navn, String beskrivelse, String sted) {
		Event e = new Event();
		e.setNavn(navn);
		e.setStatus(Status.PLANLAGT);
		e.setBeskrivelse(beskrivelse);
		e.setSted(sted);
		e.setTidFra(fra);
		e.setTidTil(til);
		return e;
	}

	private static Aktivitet lagAktivitet(String aktivitet) {
		Aktivitet a = new Aktivitet();
		a.setNavn(aktivitet);
		a.setStatus(Status.PAAGANDE);
		return a;
	}

}
