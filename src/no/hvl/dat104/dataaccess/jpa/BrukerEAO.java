package no.hvl.dat104.dataaccess.jpa;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import no.hvl.dat104.dataaccess.IBrukerEAO;
import no.hvl.dat104.model.Bruker;

@Stateless
public class BrukerEAO implements IBrukerEAO {
	@PersistenceContext(name = "g03PersistenceUnit")
	private EntityManager em;

	@Override
	public void leggTilBruker(Bruker b) {
		// TODO Auto-generated method stub

	}

	@Override
	public Bruker finnBruker(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void oppdaterBruker(Bruker b) {
		// TODO Auto-generated method stub

	}

	@Override
	public void slettBruker(Bruker b) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Bruker> alleBrukerne() {
		// TODO Auto-generated method stub
		return null;
	}

}
