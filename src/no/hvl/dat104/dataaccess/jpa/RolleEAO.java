package no.hvl.dat104.dataaccess.jpa;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import no.hvl.dat104.dataaccess.IRolleEAO;
import no.hvl.dat104.model.Rolle;

@Stateless
public class RolleEAO implements IRolleEAO {
	@PersistenceContext(name = "g03PersistenceUnit")
	private EntityManager em;

	@Override
	public void leggTilRolle(Rolle r) {
		em.persist(r);

	}

	@Override
	public Rolle finnRolle(Integer id) {
		return em.find(Rolle.class, id);
	}

	@Override
	public void oppdaterRolle(Rolle r) {
		em.merge(r);

	}

	@Override
	public void slettRolle(Rolle r) {
		em.remove(em.find(Rolle.class, r.getId()));

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Rolle> alleRollene() {
		List<Rolle> rollene = em.createQuery("SELECT r FROM Rolle r").getResultList();
		return rollene;
	}

}
