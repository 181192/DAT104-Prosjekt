package no.hvl.dat104.controller.importer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.ParseException;
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
			request.getRequestDispatcher(JspMappings.LANDING_STYRER_JSP).forward(request, response);		
		}else {
			response.sendRedirect(UrlMappings.LOGGINN_URL);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(InnloggingUtil.erInnloggetSomBruker(request)) {
			String url = ValidatorUtil.escapeHtml(request.getParameter("url"));
			if(url.contains("html")) {
				url = url.replace("html", "csv");
	        }
			if(ValidatorUtil.isNotNull0(url) && url.contains("no.timeedit.net")) {
				if(readCSVInternett(request, url)) {
					FlashUtil.Flash(request, "success", "Kalender er importert!");
					response.sendRedirect(UrlMappings.LANDING_STYRER_URL);
				}else {
					response.sendRedirect(UrlMappings.LANDING_STYRER_URL);
				}
			}else {
				FlashUtil.Flash(request, "error", "Kan ikke importere fra denne lenken. Sjekk at Lenken kommer fra TimeEdit!");
				response.sendRedirect(UrlMappings.LANDING_STYRER_URL);
			}
		}else {
			response.sendRedirect(UrlMappings.LOGGINN_URL);
		}

	}
	private boolean readCSVInternett(HttpServletRequest request, String linken) throws FileNotFoundException, IOException {
		URL url;
		try {
		    url = new URL(linken);
		    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        if (connection.getResponseCode() == 200) {
	        	InputStreamReader streamReader = new InputStreamReader(connection.getInputStream(), "UTF-8") ;
	        		BufferedReader br = new BufferedReader(streamReader);
	            	String line = br.readLine();
	                line = br.readLine() + br.readLine() + br.readLine();
	                String[] fields = line.split(",");
	                fields = Arrays.copyOfRange(fields, 3, fields.length);
	                while ((line = br.readLine()) != null && !line.isEmpty()) {
	    				fields = line.split(",");
	    				EventSetup eventHjelp = ImporterSetup.lagEventFraFields(fields);
	    				Bruker b = InnloggingUtil.innloggetSomBruker(request);
	    				List<Aktivitet> aktiviteter = brukerEAO.finnAlleAktiviteterTilBruker(b.getId());
	    				Event e = ImporterSetup.lagEvent(eventHjelp.getTidFra(), eventHjelp.getTidTil(), eventHjelp.getNavn(), eventHjelp.getBeskrivelse(), eventHjelp.getSted());
	    				Aktivitet a = ImporterSetup.lagAktivitet(e, aktiviteter, eventHjelp.getAktivitet(), b, aktivitetEAO);
	    				brukerEAO.finnBrukerLeggTilEvent(b.getId(), e, a.getId());
	    			}
	    			br.close();
	    			return true;
	           }   
		} catch(MalformedURLException e) {
			FlashUtil.Flash(request, "error", "Kalender ble ikke importert!" + " Url'en er ikke gyldig : " + linken);
		}
		FlashUtil.Flash(request, "error", "Kalender ble ikke importert!" + " Url'en er ikke gyldig : " + linken);
        return false;  
	}


}
