package no.hvl.dat104.dataaccess;

import java.sql.Timestamp;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import no.hvl.dat104.model.Aktivitet;
import no.hvl.dat104.model.Bruker;
import no.hvl.dat104.model.Event;
import no.hvl.dat104.model.Rolle;
import no.hvl.dat104.model.Tilbakemelding;
import no.hvl.dat104.model.Rettigheter;

@Stateless
public class Databasehjelper {
	
	
	//idk ka eg helle på meg, send hjelp
	
	Rolle a = new Rolle ("Admin");
	Rolle b = new Rolle("Aktivitetsstyrer");
	
	Rettigheter c = new Rettigheter(true, true, false, a);
	Rettigheter d = new Rettigheter(false, false, true, b);
			
	Bruker e = new Bruker("admin@gruppe3.no", "Ola", "Olsen", "foobar", "dioawd633a", a);
	Bruker f = new Bruker("kari@gruppe3.no", "Kari", "Pettersen", "foobar", "afa7fa9dwa", b);
	Bruker g = new Bruker("per@gruppe3.no", "Per", "Hansen", "foobar", "njnpojniu2", b);

	Aktivitet h = new Aktivitet("DAT100", "avsluttet", e);
	Aktivitet i = new Aktivitet("MAT101", "pagaende", f);
	Aktivitet j = new Aktivitet("DAT102", "planlagt", g);
	
	
	Event k = new Event();
    	k.setNavn("DAT100"); 
    	k.setTidFra(2017-10-20 12:00:00);
    	k.getTidTil(2017-10-20 14:00:00');
    	k.getStatus("avsluttet");
    	k.getSted("F115");
    	k.getIdAktivitet(1);
    	
    Event l = new Event();
    l.setNavn("MAT101");  
	l.setTidFra(2017-10-26 10:00:00);
	l.getTidTil(2017-10-26 22:00:00);
	l.getStatus("pagaende");
	l.getSted("F110");
	l.getIdAktivitet(2);
	
    Event m = new Event();
    m.setNavn("DAT102");  
	m.setTidFra(2017-12-20 09:00:00);
	m.getTidTil(2017-12-20 10:30:00);
	m.getStatus("planlagt");
	m.getSted("E403");
	m.getIdAktivitet(3);
	
    	
	
	Timestamp en = new Timestamp (2017, 10, 20, 14, 01, 00);
	
	java.sql.Timestamp ts = java.sql.Timestamp.valueOf(2017-10-20 12:01:00);
	

      //Timestamp(int year, int month, int date, int hour, int minute, int second, int nano)

			Tilbakemelding n = new Tilbakemelding("0", k, "2017-10-20 14:01:00");
			Tilbakemelding o = new Tilabkemelding("1", k, '2017-10-20 14:01:00');
			Tilbakemelding p = new Tilbakemelding("2", k, '2017-10-20 14:01:00');
			Tilbakemelding q = new Tilbakemelding("0", k, '2017-10-20 14:02:00');
			Tilbakemelding r = new Tilbakemelding("0", k, '2017-10-20 14:02:00');
			Tilbakemelding s = new Tilbakemelding("0", k, '2017-10-20 14:02:00');
			Tilbakemelding t = new Tilbakemelding("1", k, '2017-10-20 14:03:00');
			Tilbakemelding u = new Tilbakemelding("1", k, '2017-10-20 14:03:00');
			Tilbakemelding v = new Tilbakemelding("2", k, '2017-10-20 14:03:00');
			Tilbakemelding w = new Tilbakemelding("2", k, '2017-10-20 14:14:00');
			Tilbakemelding x = new Tilbakemelding("2", k, '2017-10-20 14:14:00');
			Tilbakemelding z = new Tilbakemelding("2", k, '2017-10-20 14:14:00');
    	
	//public Tilbakemelding(String stemme, Event idEvent, Timestamp tid) {

	//INSERT INTO Tilbakemelding (stemme, tid, "id_event") VALUES ();

	
	
	
	// Ditta skal ein ann plass?
	@EJB
	private IRolleEAO rolleEAO;

	@PersistenceContext(name = "studentPersistenceUnit")
	private EntityManager em;

	public void JUnitTestBruker() {

		// fjerne alle brukere
		em.createQuery("DELETE FROM Bruker").executeUpdate();

		// legge inn nye brukere
		Bruker a = new Bruker("admin@gruppe3.no", "Ola", "Olsen", "foobar", "dioawd633a", rolleEAO.finnRolle(1));
		Bruker b = new Bruker("kari@gruppe3.no", "Kari", "Pettersen", "foobar", "afa7fa9dwa", rolleEAO.finnRolle(2));
		Bruker c = new Bruker("per@gruppe3.no", "Per", "Hansen", "foobar", "njnpojniu2", rolleEAO.finnRolle(2));

		em.persist(a);
		em.persist(b);
		em.persist(c);

		leggTilBruker(a);
		leggTilBruker(b);
		leggTilBruker(c);

		// sjekke at det er rett
		assertTrue(bv.erAlleDataGyldig());
		assertTrue(b.getFornavn().equals(fornavn));
		assertTrue(b.getEtternavn().equals(etternavn));
		assertTrue(b.getMail().equals(mail));
		assertTrue(b.getPassord().equals(passord));

		// id serial NOT NULL,
		// mail varchar(100) NOT NULL,
		// fornavn varchar(50) NOT NULL,
		// etternavn varchar(70) NOT NULL,
		// passord varchar NOT NULL,
		// salt varchar NOT NULL,
		// id_rolle integer NOT NULL,

		// fjerne alle brukere?

	}

	public void JUnitTestRolle() {

		// lage ein admin og ein aktivitetsstyrer
		Bruker a = new Bruker("admin@gruppe3.no", "Ola", "Olsen", "foobar", "dioawd633a", rolleEAO.finnRolle(1));
		em.persist(a);
		leggTilBruker(a);

		Bruker b = new Bruker("kari@gruppe3.no", "Kari", "Pettersen", "foobar", "afa7fa9dwa", rolleEAO.finnRolle(2));
		em.persist(b);
		leggTilBruker(b);

		// sjekk at id ikkje er nulll
		// sjekk at type text ikkje er null

		assertTrue(a.getIdRolle().equals(rolleEAO.finnRolle(1)));
		assertTrue(b.getIdRolle().equals("Aktivitetsstyrer"));

		// fjern brukere?

	}
	
	public void JUnitTestRettigheter() {
		
		
//		Lage ein admin og sjekk at han kan gjere admin ting
//		lag ein aktivitetsstyert og sjekk at han kna gjere aktivitetsstyrer ting, og ikkje det andre?
				
		Bruker a = new Bruker("admin@gruppe3.no", "Ola", "Olsen", "foobar", "dioawd633a", rolleEAO.finnRolle(1));
		em.persist(a);
		leggTilBruker(a);

		Bruker b = new Bruker("kari@gruppe3.no", "Kari", "Pettersen", "foobar", "afa7fa9dwa", rolleEAO.finnRolle(2));
		em.persist(b);
		leggTilBruker(b);
		
		
//		id serial NOT NULL,
//		godkjenne_bruker bool NOT NULL,
//		slette_bruker bool NOT NULL,
//		opprette_aktivitet bool NOT NULL,
//		id_rolle integer NOT NULL,
//		
		
		
	}

}
