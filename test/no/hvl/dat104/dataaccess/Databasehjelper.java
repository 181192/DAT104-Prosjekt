package no.hvl.dat104.dataaccess;

import java.sql.Timestamp;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.resource.cci.ResultSet;

import com.sun.jndi.ldap.Connection;

import no.hvl.dat104.model.Aktivitet;
import no.hvl.dat104.model.Bruker;
import no.hvl.dat104.model.Event;
import no.hvl.dat104.model.Rolle;
import no.hvl.dat104.model.Tilbakemelding;
import no.hvl.dat104.util.DatoUtil;
import no.hvl.dat104.model.Rettigheter;

@Stateless
public class Databasehjelper {

	// test verdier fra seedDB.sql

	Rolle a = new Rolle("Admin"); // 1
	Rolle b = new Rolle("Aktivitetsstyrer");// 2
	Rolle bb = new Rolle("Ikke godkjent");// 3

	Rettigheter c = new Rettigheter(true, true, false, a);
	Rettigheter d = new Rettigheter(false, false, true, b);
	Rettigheter dd = new Rettigheter(false, false, false, bb);

	Bruker e = new Bruker("admin@gruppe3.no", "Ola", "Olsen", "foobar", "dioawd633a", a);
	Bruker f = new Bruker("kari@gruppe3.no", "Kari", "Pettersen", "foobar", "afa7fa9dwa", b);
	Bruker g = new Bruker("per@gruppe3.no", "Per", "Hansen", "foobar", "njnpojniu2", b);

	Aktivitet h = new Aktivitet("DAT100", "avsluttet", f);
	Aktivitet i = new Aktivitet("MAT101", "pagaende", f);
	Aktivitet j = new Aktivitet("DAT102", "planlagt", g);

//	Event k = new Event(1, "DAT100", "Beskrivelse DAT100", DatoUtil.formaterDatoTilStamp("20.10.2017", "12:00"),
//			DatoUtil.formaterDatoTilStamp("20.10.2017", "14:00"), DatoUtil.formaterDatoTilStamp("20.10.2017", "12:02"),
//			DatoUtil.formaterDatoTilStamp("20.10.2017", "14:01"), "avsluttet", "F115", h);
//	Event l = new Event(2, "MAT101", "Beskrivelse MAT101", DatoUtil.formaterDatoTilStamp("26.10.2017", "10:00"),
//			DatoUtil.formaterDatoTilStamp("26.10.2017", "22:00"), DatoUtil.formaterDatoTilStamp("20.10.2017", "09:57"),
//			DatoUtil.formaterDatoTilStamp("20.10.2017", "22:05"), "pagaende", "F110", i);
//	Event m = new Event(3, "DAT02", "Beskrivelse DAT102", DatoUtil.formaterDatoTilStamp("20.12.2017", "09:00"),
//			DatoUtil.formaterDatoTilStamp("20.12.2017", "10:30"), DatoUtil.formaterDatoTilStamp("20.12.2017", "09:00"),
//			DatoUtil.formaterDatoTilStamp("20.12.2017", "10:30"), "planlagt", "E403", j);
//
//	Tilbakemelding n = new Tilbakemelding("0", k, DatoUtil.formaterDatoTilStamp("20.10.2017", "14:01"));
//	Tilbakemelding o = new Tilbakemelding("1", k, DatoUtil.formaterDatoTilStamp("20.10.2017", "14:02"));
//	Tilbakemelding p = new Tilbakemelding("2", k, DatoUtil.formaterDatoTilStamp("20.10.2017", "14:03"));
//	Tilbakemelding q = new Tilbakemelding("0", k, DatoUtil.formaterDatoTilStamp("20.10.2017", "14:04"));
//	Tilbakemelding r = new Tilbakemelding("0", k, DatoUtil.formaterDatoTilStamp("20.10.2017", "14:05"));
//	Tilbakemelding s = new Tilbakemelding("0", k, DatoUtil.formaterDatoTilStamp("20.10.2017", "14:06"));
//	Tilbakemelding t = new Tilbakemelding("1", k, DatoUtil.formaterDatoTilStamp("20.10.2017", "14:07"));
//	Tilbakemelding u = new Tilbakemelding("1", k, DatoUtil.formaterDatoTilStamp("20.10.2017", "14:08"));
//	Tilbakemelding v = new Tilbakemelding("2", k, DatoUtil.formaterDatoTilStamp("20.10.2017", "14:09"));
//	Tilbakemelding w = new Tilbakemelding("2", k, DatoUtil.formaterDatoTilStamp("20.10.2017", "14:10"));
//	Tilbakemelding x = new Tilbakemelding("2", k, DatoUtil.formaterDatoTilStamp("20.10.2017", "14:11"));
//	Tilbakemelding z = new Tilbakemelding("2", k, DatoUtil.formaterDatoTilStamp("20.10.2017", "14:12"));

//	@PersistenceContext(name = "brukertPersistenceUnit")
//	private EntityManager em;
//
//	@Test
//	public void lagBruker() {
//		
//		Databasehjelper databasehjelper = new Databasehjelper();
//		 try(Connection connection = .){
//			 try( Statement stCheck = connection.createStatement)){
//				 connection.setAutoComit(false);
//				 
//				  // Initial cleanup:
//	                stCheck.executeUpdate("DELETE FROM bruker");
//	                stCheck.executeUpdate("DELETE FROM aktivitet");
//	                
//	                
//	               // Setting input parameters:
//	               //gjort oppe
//	                int cvr = 111;
//	                
//				 //// Do the call:
//	                Contractor contractor=databasehjelper.create(cvr);
//	                Contractor contractor=databasehjelper.create(f);
//	                Contractor contractor=databasehjelper.create(g);
//	                
//	                // Javabean Checks: Check the javabean contains the expected values:
//	               
//	                assertEquals(mail, contractor.getMail());
//	                assertEquals(fornavn, contractor.getFornavn());
//	                assertEquals(etternavn, contractor.getEtternanv());
//	                assertEquals(passord, contractor.getPassord());
//	                assertEquals(salt, contractor.getSalt()));
//	                assertEquals(idRolle, contractor.getIdRolle());
//
//	                // Database Checks:
//	                int brukerId;
//	                
//	                // Check the Person table contains one row with the expected values:
//	                try(ResultSet rs=stCheck.executeQuery("SELECT * FROM bruker"))
//	                {
//	                    assertTrue(rs.next());
//	                    brukerId=rs.getInt("id");
//	                    asssertEquals(mail, rs.getString("mail"));
//	                    asssertEquals(fornavn, rs.getString("fornavn"));
//	                    assertEquals(etternavn, rs.getString("etternavn"));
//	                    assertEquals(passord, rs.getString("passord"));
//	                    assertEquals(salt, rs.getString("salt"));
//	                    assertEquals(idRolle, rs.getString("idRolle"));
//	                    assertFalse(rs.next());
//	                }
//
//	                // Check the Contractor table contains one row with the expected values:
//	                try(ResultSet rs=stCheck.executeQuery("SELECT * FROM contractor WHERE bruker_id="+brukerId))
//	                {
//	                    assertTrue(rs.next());
//	                    asssertEquals(2666, rs.getInt("cvr"));
//	                    assertFalse(rs.next());
//	                }
//	            }
//	            finally
//	            {
//	                 // Undo the testing operations:
//	                 connection.rollback();
//	            }
//	        }
//	        catch (SQLException e)
//	        {
//	            fail(e.toString());
//	}
//	}
//	
//	
//    @Test
//	public void JUnitTestBruker() {
//
//		// fjerne alle brukere
//		em.getTransaction().begin();
//
//		em.createQuery("DELETE FROM Bruker").executeUpdate();
//		em.createQuery("DELETE FROM aktivitet").executeUpdate();
//
//		// legge inn nye brukere
//		em.persist(a);
//		em.persist(b);
//		em.persist(bb);
//
//		// sjekke at det er rett
//		assertTrue(a.getFornavn().equals(fornavn));
//		assertTrue(a.getEtternavn().equals(etternavn));
//		assertTrue(a.getMail().equals(mail));
//		assertTrue(a.getPassord().equals(passord));
//
//		// fjerne alle brukere?
//
//	}
//
//	public void JUnitTestRolle() {
//
//		// lage ein admin og ein aktivitetsstyrer
//		Bruker a = new Bruker("admin@gruppe3.no", "Ola", "Olsen", "foobar", "dioawd633a", rolleEAO.finnRolle(1));
//		em.persist(a);
//		leggTilBruker(a);
//
//		Bruker b = new Bruker("kari@gruppe3.no", "Kari", "Pettersen", "foobar", "afa7fa9dwa", rolleEAO.finnRolle(2));
//		em.persist(b);
//		leggTilBruker(b);
//		// sjekk at id ikkje er nulll
//		// sjekk at type text ikkje er null
//
//		assertTrue(a.getIdRolle().equals(rolleEAO.finnRolle(1)));
//		assertTrue(b.getIdRolle().equals("Aktivitetsstyrer"));
//		// fjern brukere?
//	}
//
//public void JUnitTestRettigheter() {
////		Lage ein admin og sjekk at han kan gjere admin ting
////		lag ein aktivitetsstyert og sjekk at han kna gjere aktivitetsstyrer ting, og ikkje det andre?
//				
//		Bruker a = new Bruker("admin@gruppe3.no", "Ola", "Olsen", "foobar", "dioawd633a", rolleEAO.finnRolle(1));
//		em.persist(a);
//		leggTilBruker(a);
//
//		Bruker b = new Bruker("kari@gruppe3.no", "Kari", "Pettersen", "foobar", "afa7fa9dwa", rolleEAO.finnRolle(2));
//		em.persist(b);
//		leggTilBruker(b);
}