package no.hvl.dat104.dataaccess.jpa;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import no.hvl.dat104.dataaccess.IAktivitetEAO;
import no.hvl.dat104.model.Aktivitet;

@Stateless
public class AktivitetEAO implements IAktivitetEAO {

	@PersistenceContext(name = "g03PersistenceUnit")
	private EntityManager em;

	@Override
	public void leggTilAktivitet(Aktivitet a) {
		em.persist(a);

	}

	@Override
	public Aktivitet finnAktivitet(Integer id) {
		return em.find(Aktivitet.class, id);
	}

	@Override
	public void oppdaterAktivitet(Aktivitet a) {
		em.merge(a);

	}

	@Override
	public void slettAktivitet(Aktivitet a) {
		em.remove(em.find(Aktivitet.class, a.getId()));

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Aktivitet> alleAktiviteter() {
		List<Aktivitet> aktiviteter = em.createQuery("SELECT a FROM Aktivitet a").getResultList();
		return aktiviteter;
	}

}
