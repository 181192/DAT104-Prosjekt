package no.hvl.dat104.dataaccess.jpa;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import no.hvl.dat104.dataaccess.IBrukerEAO;
import no.hvl.dat104.model.Bruker;
import no.hvl.dat104.model.Rolle;

@Stateless
public class BrukerEAO implements IBrukerEAO {
	@PersistenceContext(name = "g03PersistenceUnit")
	private EntityManager em;

	@Override
	public void leggTilBruker(Bruker b) {
		em.persist(b);
	}

	@Override
	public Bruker finnBruker(Integer id) {
		return em.find(Bruker.class, id);
	}

	@Override
	public void oppdaterBruker(Bruker b) {
		em.merge(b);
	}

	@Override
	public void slettBruker(Bruker b) {
		em.remove(em.find(Bruker.class, b.getId()));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Bruker> alleBrukerne() {
		List<Bruker> brukere = em.createQuery("SELECT b FROM Bruker b").getResultList();
		return brukere;
	}

	@Override
	public void endreMailPaaBruker(Bruker b, String mail) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endreFornavnPaaBruker(Bruker b, String fornavn) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endreEtternavnPaaBruker(Bruker b, String etternavn) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endrePassordPaaBruker(Bruker b, String passord) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endreSaltPaaBruker(Bruker b, String salt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endreRollePaaBruker(Bruker b, Rolle r) {
		// TODO Auto-generated method stub
		
	}

}
