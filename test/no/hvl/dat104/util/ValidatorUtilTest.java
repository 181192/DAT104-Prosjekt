package no.hvl.dat104.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ValidatorUtilTest {

	@Test
	public void isValidString() {
		assertTrue(ValidatorUtil.isValidString("test"));
		assertTrue(ValidatorUtil.isValidString("test-streng-med-bindestrek"));
		assertTrue(ValidatorUtil.isValidString("test-streng-med-apostrof'"));
		assertTrue(ValidatorUtil.isValidString("test-streng med alt-'æøå"));
		assertFalse(ValidatorUtil.isValidString(""));
		assertFalse(ValidatorUtil.isValidString(null));
		assertFalse(ValidatorUtil.isValidString("Streng med ugyldige tegn %&<>"));
		assertFalse(ValidatorUtil.isValidString("Streng med tall1234"));
	}

	@Test
	public void isValidNumber() {
		assertTrue(ValidatorUtil.isValidNumber("0"));
		assertTrue(ValidatorUtil.isValidNumber("30"));
		assertTrue(ValidatorUtil.isValidNumber("924892"));
		assertFalse(ValidatorUtil.isValidNumber("-3"));
		assertFalse(ValidatorUtil.isValidNumber(""));
		assertFalse(ValidatorUtil.isValidNumber(null));
		assertFalse(ValidatorUtil.isValidNumber("&3"));

	}

	@Test
	public void escapeHtml() {
		assertEquals(ValidatorUtil.escapeHtml("Test<"), "Test&lt;");
		assertEquals(ValidatorUtil.escapeHtml("Test>"), "Test&gt;");
		assertEquals(ValidatorUtil.escapeHtml("Test&"), "Test&amp;");
		assertEquals(ValidatorUtil.escapeHtml("Test\""), "Test&quot;");
		assertEquals(ValidatorUtil.escapeHtml("Test\"&"), "Test&quot;&amp;");
		assertNotEquals(ValidatorUtil.escapeHtml("Test\""), "Test&amp;");
	}

	@Test
	public void isNotNull0() {
		assertTrue(ValidatorUtil.isNotNull0("Test"));
		assertFalse(ValidatorUtil.isNotNull0(null));
		assertFalse(ValidatorUtil.isNotNull0(""));
	}

	@Test
	public void isValidfornavn() {
		assertTrue(ValidatorUtil.isValidfornavn("Navn-med-bindestrek"));
		assertTrue(ValidatorUtil.isValidfornavn("Navn-med-nordiskeæøå"));
		assertFalse(ValidatorUtil.isValidfornavn("Navn-med-over-nitten-tegn-i"));
		assertFalse(ValidatorUtil.isValidfornavn(""));
		assertFalse(ValidatorUtil.isValidfornavn("A"));
		assertFalse(ValidatorUtil.isValidfornavn(null));
		assertFalse(ValidatorUtil.isValidfornavn("Navn med tall3"));
		assertFalse(ValidatorUtil.isValidfornavn("Navn med tegn &"));
	}

	@Test
	public void isValidetternavn() {
		assertTrue(ValidatorUtil.isValidetternavn("Navn-med-bindestrek"));
		assertTrue(ValidatorUtil.isValidetternavn("Navn-med-nordiskeæøå"));
		assertFalse(ValidatorUtil.isValidetternavn("Navn-med-over-nitten-tegn-i"));
		assertFalse(ValidatorUtil.isValidetternavn(""));
		assertFalse(ValidatorUtil.isValidetternavn("A"));
		assertFalse(ValidatorUtil.isValidetternavn(null));
		assertFalse(ValidatorUtil.isValidetternavn("Navn med tall3"));
		assertFalse(ValidatorUtil.isValidetternavn("Navn med tegn &"));
	}

	@Test
	public void isValidTimeformat() {
		assertTrue(ValidatorUtil.isValidTimeFormat("10:59"));
		assertTrue(ValidatorUtil.isValidTimeFormat("00:01"));
		assertTrue(ValidatorUtil.isValidTimeFormat("18:18"));
		assertFalse(ValidatorUtil.isValidTimeFormat("27:19"));
		assertFalse(ValidatorUtil.isValidTimeFormat("40:79"));
	}

	@Test
	public void isValidDateFormat() {
		assertTrue(ValidatorUtil.isValidDateFormat("10.10.2017"));
		assertTrue(ValidatorUtil.isValidDateFormat("07.01.2000"));
		assertTrue(ValidatorUtil.isValidDateFormat("07-01-2000"));
		assertFalse(ValidatorUtil.isValidDateFormat("07/01/2000"));
		assertFalse(ValidatorUtil.isValidDateFormat("39.01.2000"));
		assertFalse(ValidatorUtil.isValidDateFormat("07.01.200"));
		assertFalse(ValidatorUtil.isValidDateFormat("07.01.13"));
		assertFalse(ValidatorUtil.isValidDateFormat("07.29.2013"));
		assertFalse(ValidatorUtil.isValidDateFormat("207.01.2000"));
	}

	@Test
	public void isValidMail() {
		assertTrue(ValidatorUtil.isValidMail("mail@mail.com"));
		assertTrue(ValidatorUtil.isValidMail("mail-mail@mail.com"));
		assertTrue(ValidatorUtil.isValidMail("test123@mail.com"));
		assertTrue(ValidatorUtil.isValidMail("mail@mail.mail.com"));
		assertTrue(ValidatorUtil.isValidMail("mailmailmailmailmail@mail.com"));
		assertFalse(ValidatorUtil.isValidMail("mailmail.com"));
		assertFalse(ValidatorUtil.isValidMail("mail.mail.com"));
		assertFalse(ValidatorUtil.isValidMail("mail@mail."));
		assertFalse(ValidatorUtil.isValidMail("mail@mail"));
	}

}
