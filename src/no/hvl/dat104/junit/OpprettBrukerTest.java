package no.hvl.dat104.junit;

import static org.junit.Assert.*;

import javax.ejb.EJB;

import org.junit.Before;
import org.junit.Test;

import no.hvl.dat104.controller.styrer.bruker.BrukerValidator;
import no.hvl.dat104.dataaccess.IBrukerEAO;
import no.hvl.dat104.dataaccess.IRolleEAO;
import no.hvl.dat104.model.Bruker;

public class OpprettBrukerTest {

	@EJB
	IRolleEAO rolleEAO;
	@EJB
	IBrukerEAO brukerEAO;
	
	private String fornavn = "Ola";
	private String etternavn = "Dunk";
	private String mail = "ola@gruppe3.no";
	private String passord = "passord";
	private BrukerValidator bv;
	
	@Before
	public void setUp() throws Exception {
		Bruker b = new Bruker();
		b.setFornavn(fornavn);
		b.setEtternavn(etternavn);
		b.setMail(mail);
		b.setPassord(passord);
		b.setIdRolle(rolleEAO.finnRolle(2));
		b.setSalt("salt");
		bv = new BrukerValidator(b);
		brukerEAO.leggTilBruker(b);
	}
	
	@Test
	public void test() {
		assertTrue(bv.erAlleDataGyldig());
	}

}
