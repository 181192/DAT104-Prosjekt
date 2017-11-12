package no.hvl.dat104.util;

import java.util.regex.Pattern;

public class ValidatorUtil {
	/**
     * Validerer strengen om det er riktig, tillatter alle
     * unicode bokstaver fra alle språk samt -, ' og whitespaces
     * @param s Strengen som skal valideres
     * @return True eller false
     */
    public static boolean isValidString(String s) {
        return s != null && s.matches("^[\\p{L}\\-\'\\s]+$");
    }
    
    public static boolean isValidNumber(String n) {
        return n != null && n.matches("^[\\d]+$");
    }
    /**
     * Renser strengen for <, >, &, ' med html encoding
     * @param s Strengen som skal renses
     * @return Renset streng eller null
     */
    public static String escapeHtml(String s) {
        if (s != null) {
            return s
                    .replaceAll("&", "&amp;")
                    .replaceAll("<", "&lt;")
                    .replaceAll(">", "&gt;")
                    .replaceAll("\"", "&quot;");
        }
        return null;
    }
    /**
     * Returnerer sann om streng er ulik null og ""
     * @param s
     * @return
     */
    public static boolean isNotNull0(String s) {
        return s != null && s != "";
    }
    /**
     * Fornavn skal være 2-20 tegn og kan inneholde bokstaver (inkl. æøåÆØÅ),
     * @param fornavn
     * @return
     */
    public static boolean isValidfornavn(String fornavn) {
        return fornavn.matches("^[A-ZØÆÅ][A-ZÆØÅa-zæøå\\-\\ \\']{1,19}$");
    }
    public static boolean isValidetternavn(String etternavn) {
        return etternavn.matches("^[A-ZØÆÅ][A-ZÆØÅa-zæøå\\-\\']{1,19}$");
    }
    
    public static boolean isValidTimeFormat(String tid) {
    	return tid.matches("^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$")&&(isNotNull0(tid));
    }
    public static boolean isValidDateFormat(String tid) {
    	return tid.matches("^(0[1-9]|[12][0-9]|3[01])[- \\.](0[1-9]|1[012])[- \\.](19|20)\\d\\d$");
    }
    
    //Må fylles inn
    public static boolean isValidMail(String mail) {
    	return Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\\\""
    			+ "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\\\")@"
    			+ "(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}"
    			+ "(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])").matcher(mail).matches();
    }
    public static String formaterText(String navn) {
        final int LENGDE = 8;
        String nyttNavn = "";
        if(navn.length() > LENGDE) {
            for(int i = 0; i < LENGDE; i ++) {
                nyttNavn += navn.charAt(i);
            }
            return nyttNavn += "..";
        }
        return navn;
    }

}
