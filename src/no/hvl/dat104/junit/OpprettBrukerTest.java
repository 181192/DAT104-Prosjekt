package no.hvl.dat104.junit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import no.hvl.dat104.controller.styrer.bruker.BrukerValidator;
import no.hvl.dat104.model.Bruker;
import no.hvl.dat104.model.Rolle;

public class OpprettBrukerTest {
	
	private String fornavn = "Ola";
	private String etternavn = "Dunk";
	private String mail = "ola@gruppe3.no";
	private String passord = "passord";
	private BrukerValidator bv;
	//Funker ikke å hente rolle fra DB
	private Rolle rolle;
	private Bruker b;
	
	@Before
	public void setUp() throws Exception {
		rolle = new Rolle("Tester");
		b = new Bruker();
		b.setFornavn(fornavn);
		b.setEtternavn(etternavn);
		b.setMail(mail);
		b.setPassord(passord);
		b.setIdRolle(rolle);
		b.setSalt("salt");
		bv = new BrukerValidator(b);
	}
	
	@Test
	public void test() {
		assertTrue(bv.erAlleDataGyldig());
		assertTrue(b.getFornavn().equals(fornavn));
		assertTrue(b.getEtternavn().equals(etternavn));
		assertTrue(b.getMail().equals(mail));
		assertTrue(b.getPassord().equals(passord));
	}

}
