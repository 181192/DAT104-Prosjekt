package no.hvl.dat104.dataaccess.jpa;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import no.hvl.dat104.dataaccess.IRettigheterEAO;
import no.hvl.dat104.model.Rettigheter;

@Stateless
public class RettigheterEAO implements IRettigheterEAO {
	@PersistenceContext(name = "g03PersistenceUnit")
	private EntityManager em;

	@Override
	public void leggTilRettigheter(Rettigheter r) {
		em.persist(r);

	}

	@Override
	public Rettigheter finnRettigheter(Integer id) {
		return em.find(Rettigheter.class, id);
	}

	@Override
	public void oppdaterRettigheter(Rettigheter r) {
		em.merge(r);

	}

	@Override
	public void slettRettigheter(Rettigheter r) {
		em.remove(em.find(Rettigheter.class, r.getId()));

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Rettigheter> alleRettigheter() {
		List<Rettigheter> rettigheter = em.createQuery("SELECT r FROM Rettigheter r").getResultList();
		return rettigheter;
	}

}
