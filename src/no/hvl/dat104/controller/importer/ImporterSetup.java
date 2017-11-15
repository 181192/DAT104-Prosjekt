package no.hvl.dat104.controller.importer;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;

import no.hvl.dat104.dataaccess.IAktivitetEAO;
import no.hvl.dat104.model.Aktivitet;
import no.hvl.dat104.model.Bruker;
import no.hvl.dat104.model.Event;
import no.hvl.dat104.model.Status;
import no.hvl.dat104.util.DatoUtil;

public class ImporterSetup {
	public static Aktivitet lagAktivitet(Event e, List<Aktivitet> aktiviteter, String aktivitet, Bruker b, IAktivitetEAO aktivitetEAO) {
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
		return a;
	}

	public static Event lagEvent(Timestamp fra, Timestamp til, String navn, String beskrivelse, String sted) {
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
	public static EventSetup lagEventFraFields(String[] fields) {
		String dato = fields[0];
        String tidFra = fields[1].substring(1);
        String tidTil = fields[3].substring(1);
        String aktivitet = fields[6].substring(1);
        String navn = fields[8].substring(1);
        String beskrivelse = fields[10];
        String sted = fields[9].substring(1);
        
        if(navn.equals("Lab") || navn.equals("Øving")) {
            beskrivelse = fields[11];
        }else {
            beskrivelse += ", " + fields[11];
        }

        if(fields[12].matches("^\\s[A-Z].*")) {
            if(fields[11].charAt(0) == '\"') {
                beskrivelse += ", " + fields[12];
            }
            if(fields[12].matches("^\\s[A-Z].*")) {
                beskrivelse += ", " + fields[12];
            }
            if(fields[13].matches("^\\s[A-Z].*")) {
                beskrivelse += ", " + fields[13];
            }
        }
        if(beskrivelse.contains("\"")) {
            beskrivelse = beskrivelse.replace('"', ' ').substring(1);
        }
        if(beskrivelse.charAt(0) == ' ') {
            beskrivelse = beskrivelse.substring(1);
        }
        if(sted.contains("\"")) {
        	sted = sted.replace('"', ' ').substring(1);
        }
        Timestamp til = null;
        Timestamp fra = null;
		try {
			fra = DatoUtil.formaterDatoTilStamp(dato, tidFra);
			til = DatoUtil.formaterDatoTilStamp(dato, tidTil);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		EventSetup e = new EventSetup(dato, fra, til, navn, beskrivelse, sted, aktivitet);
		return e;
	}

}
