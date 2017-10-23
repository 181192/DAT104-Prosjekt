package no.hvl.dat104.dataaccess.jpa;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import no.hvl.dat104.dataaccess.IKodeordEAO;
import no.hvl.dat104.model.Kodeord;

@Stateless
public class KodeordEAO implements IKodeordEAO {
	@PersistenceContext(name = "g03PersistenceUnit")
	private EntityManager em;

	@Override
	public void leggTilKodeord(Kodeord k) {
		em.persist(k);

	}

	@Override
	public Kodeord finnKodeord(Integer id) {
		return em.find(Kodeord.class, id);
	}

	@Override
	public void oppdaterKodeord(Kodeord k) {
		em.merge(k);

	}

	@Override
	public void slettKodeord(Kodeord k) {
		em.remove(em.find(Kodeord.class, k.getId()));

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Kodeord> alleKodeord() {
		List<Kodeord> kodeord = em.createQuery("SELECT k FROM Kodeord k").getResultList();
		return kodeord;
	}

}
