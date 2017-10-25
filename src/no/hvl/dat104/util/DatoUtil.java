package no.hvl.dat104.util;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatoUtil {
	public static Timestamp formaterDatoTilStamp(String dato, String klokkeslett) throws ParseException {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        java.util.Date startDato= df.parse(dato + " " + klokkeslett);
        java.sql.Timestamp sq = new java.sql.Timestamp(startDato.getTime());
        return sq;
    }
    public static Date formaterStampTilDato(Timestamp timestamp) {
        Date date = new Date(timestamp.getTime());
        return date;
    }
    public static String fintFormatertDato(String dato, String klokkeslett) throws ParseException {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        java.util.Date startDato = df.parse(dato + " " + klokkeslett);
        String newStartDateString = df.format(startDato);
        return newStartDateString;
    }

}
