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
		// TODO Auto-generated method stub
		
	}

	@Override
	public Aktivitet finnAktivitet(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void oppdaterAktivitet(Aktivitet a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void slettAktivitet(Aktivitet a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Aktivitet> alleAktiviteter() {
		// TODO Auto-generated method stub
		return null;
	}

}
