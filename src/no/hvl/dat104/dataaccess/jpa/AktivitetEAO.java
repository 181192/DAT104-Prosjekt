package no.hvl.dat104.dataaccess.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import no.hvl.dat104.dataaccess.IAktivitetEAO;
import no.hvl.dat104.model.Aktivitet;
import no.hvl.dat104.model.Bruker;
import no.hvl.dat104.model.Event;

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

	@Override
	public void endreNavnPaaAktivitet(Integer id, String navn) {
		Aktivitet a = finnAktivitet(id);
		a.setNavn(navn);
		em.merge(a);

	}

	@Override
	public void endreStatusPaaAktivitet(Integer id, String status) {
		Aktivitet a = finnAktivitet(id);
		a.setStatus(status);
		em.merge(a);
	}

	@Override
	public void flyttAktivitetenTilNyBruker(Integer id, Bruker bruker) {
		Aktivitet a = finnAktivitet(id);
		a.setIdBruker(bruker);
		em.merge(a);
	}
	
	@Override
	public List<Event> finnAlleEventerTilAktivitet(Integer id) {
		List<Event> e = new ArrayList<>();
		e.addAll(em.find(Aktivitet.class, id).getEventer());
		return e;
	}

}
