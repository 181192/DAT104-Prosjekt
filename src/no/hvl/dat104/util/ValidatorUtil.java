package no.hvl.dat104.util;

public class ValidatorUtil {
	/**
     * Validerer strengen om det er riktig, tillatter alle
     * unicode bokstaver fra alle spr�k samt -, ' og whitespaces
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
     * Fornavn skal v�re 2-20 tegn og kan inneholde bokstaver (inkl. ������),
     * @param fornavn
     * @return
     */
    public static boolean isValidfornavn(String fornavn) {
        return fornavn.matches("^[A-Z���][A-Z���a-z���\\-\\ \\']{1,19}$");
    }
    public static boolean isValidetternavn(String etternavn) {
        return etternavn.matches("^[A-Z���][A-Z���a-z���\\-\\']{1,19}$");
    }

}
