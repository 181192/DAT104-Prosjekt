package no.hvl.dat104.dataaccess.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import no.hvl.dat104.dataaccess.IEventEAO;
import no.hvl.dat104.model.Event;
import no.hvl.dat104.model.Tilbakemelding;

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
	public List<Tilbakemelding> finnAlleTilbakemeldingerTilEvent(Integer id) {
		List<Tilbakemelding> t = new ArrayList<>();
		t.addAll(em.find(Event.class, id).getTilbakemeldinger());
		return t;
	}
	
	@Override
	public void endreStatusPaaEvent(Integer id, String status) {
		Event e = finnEvent(id);
		e.setStatus(status);
		em.merge(e);
	}
} 
