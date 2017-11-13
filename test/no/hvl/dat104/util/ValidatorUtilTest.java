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
		// TODO
	}

	@Test
	public void isValidDateFormat() {
		// TODO
	}

	@Test
	public void isValidMail() {
		// TODO

	}

}
