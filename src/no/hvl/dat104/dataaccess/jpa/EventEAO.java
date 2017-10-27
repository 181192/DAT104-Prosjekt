package no.hvl.dat104.dataaccess.jpa;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import no.hvl.dat104.dataaccess.IEventEAO;
import no.hvl.dat104.model.Aktivitet;
import no.hvl.dat104.model.Event;

@Stateless
public class EventEAO implements IEventEAO {
	@PersistenceContext(name = "g03PersistenceUnit")
	private EntityManager em;

	@Override
	public void leggTilEvent(Event e) {
		em.persist(e);

	}

	@Override
	public Event finnEvent(Integer id) {
		return em.find(Event.class, id);
	}

	@Override
	public void oppdaterEvent(Event e) {
		em.merge(e);

	}

	@Override
	public void slettEvent(Event e) {
		em.remove(em.find(Event.class, e.getId()));

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Event> alleEventer() {
		List<Event> eventer = em.createQuery("SELECT e FROM Event e").getResultList();
		return eventer;
	}

	@Override
	public List<Event> finnAlleEventerTilAktivitet(Aktivitet id) {
		Query a = em.createQuery("SELECT e FROM Event e WHERE e.idAktivitet =:id").setParameter("id", id);
		return a.getResultList();
	}

}
