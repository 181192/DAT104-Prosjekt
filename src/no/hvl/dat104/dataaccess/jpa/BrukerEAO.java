package no.hvl.dat104.dataaccess.jpa;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import no.hvl.dat104.dataaccess.IBrukerEAO;
import no.hvl.dat104.model.Bruker;

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

}
