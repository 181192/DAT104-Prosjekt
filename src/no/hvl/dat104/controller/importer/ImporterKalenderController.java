package no.hvl.dat104.controller.importer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hvl.dat104.model.Event;
import no.hvl.dat104.util.DatoUtil;

/**
 * Servlet implementation class ImporterKalenderController
 */
public class ImporterKalenderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	public static List readCSV() throws FileNotFoundException, IOException {
        List eventer = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader("test.csv"));
        String line = br.readLine();
        while ((line = br.readLine()) != null && !line.isEmpty()) {
            String[] fields = line.split(",");
            String dato = fields[0];
            String tidFra = fields[1];
            String tidTil = fields[3];
            String aktivitet = fields[6];
            String navn = fields[8];
            String beskrivelse = fields[10];
            String sted = fields[9];
            Timestamp til = null;
            Timestamp fra = null;
            try {
                fra = DatoUtil.formaterDatoTilStamp(dato, tidFra);
                til = DatoUtil.formaterDatoTilStamp(dato, tidTil);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            //Event event = new Event(navn,  beskrivelse,  fra,  til,  sted, aktivitet);
            //countries.add(event);
        }
        br.close();
        return eventer;

    }

}
