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
		// TODO Auto-generated method stub

	}

	@Override
	public Kodeord finnKodeord(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void oppdaterKodeord(Kodeord k) {
		// TODO Auto-generated method stub

	}

	@Override
	public void slettKodeord(Kodeord k) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Kodeord> alleKodeord() {
		// TODO Auto-generated method stub
		return null;
	}

}
