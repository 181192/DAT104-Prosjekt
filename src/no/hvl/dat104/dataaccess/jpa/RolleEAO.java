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
		// TODO Auto-generated method stub

	}

	@Override
	public Rolle finnRolle(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void oppdaterRolle(Rolle r) {
		// TODO Auto-generated method stub

	}

	@Override
	public void slettRolle(Rolle r) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Rolle> alleRollene() {
		// TODO Auto-generated method stub
		return null;
	}

}
