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
		// TODO Auto-generated method stub

	}

	@Override
	public Rettigheter finnRettigheter(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void oppdaterRettigheter(Rettigheter r) {
		// TODO Auto-generated method stub

	}

	@Override
	public void slettRettigheter(Rettigheter r) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Rettigheter> alleRettigheter() {
		// TODO Auto-generated method stub
		return null;
	}

}
