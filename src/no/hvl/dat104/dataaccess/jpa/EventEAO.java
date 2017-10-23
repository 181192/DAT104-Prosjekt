package no.hvl.dat104.dataaccess.jpa;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import no.hvl.dat104.dataaccess.IEventEAO;
import no.hvl.dat104.model.Event;

@Stateless
public class EventEAO implements IEventEAO {
	@PersistenceContext(name = "g03PersistenceUnit")
	private EntityManager em;

	@Override
	public void leggTilEvent(Event e) {
		// TODO Auto-generated method stub

	}

	@Override
	public Event finnEvent(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void oppdaterEvent(Event e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void slettEvent(Event e) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Event> alleEventer() {
		// TODO Auto-generated method stub
		return null;
	}

}
