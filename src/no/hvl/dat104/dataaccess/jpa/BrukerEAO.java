package no.hvl.dat104.dataaccess.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import no.hvl.dat104.dataaccess.IBrukerEAO;
import no.hvl.dat104.model.Aktivitet;
import no.hvl.dat104.model.Bruker;
import no.hvl.dat104.model.Event;
import no.hvl.dat104.model.Rolle;

@Stateless
public class BrukerEAO implements IBrukerEAO {
	@PersistenceContext(name = "g03PersistenceUnit")
	private EntityManager em;

	@Override
	public void leggTilBruker(Bruker b) {
		em.persist(b);
	}

	@Override
	public Bruker finnBruker(Integer id) {
		return em.find(Bruker.class, id);
	}

	@Override
	public void oppdaterBruker(Bruker b) {
		em.merge(b);
	}

	@Override
	public void slettBruker(Bruker b) {
		em.remove(em.find(Bruker.class, b.getId()));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Bruker> alleBrukerne() {
		List<Bruker> brukere = em.createQuery("SELECT b FROM Bruker b").getResultList();
		return brukere;
	}

	@Override
	public void endreMailPaaBruker(Integer id, String mail) {
		Bruker b = finnBruker(id);
		b.setMail(mail);
		em.merge(b);

	}

	@Override
	public void endreFornavnPaaBruker(Integer id, String fornavn) {
		Bruker b = finnBruker(id);
		b.setFornavn(fornavn);
		em.merge(b);

	}

	@Override
	public void endreEtternavnPaaBruker(Integer id, String etternavn) {
		Bruker b = finnBruker(id);
		b.setEtternavn(etternavn);
		em.merge(b);

	}

	@Override
	public void endrePassordPaaBruker(Integer id, String passord) {
		Bruker b = finnBruker(id);
		b.setPassord(passord);
		em.merge(b);

	}

	@Override
	public void endreSaltPaaBruker(Integer id, String salt) {
		Bruker b = finnBruker(id);
		b.setSalt(salt);
		em.merge(b);

	}

	@Override
	public void endreRollePaaBruker(Integer id, Rolle r) {
		Bruker b = finnBruker(id);
		b.setIdRolle(r);
		em.merge(b);
	}

	@Override
	public List<Event> finnAlleEventerTilBruker(Integer id) {
		List<Event> eventer = new ArrayList<>();
		List<Aktivitet> b = em.find(Bruker.class, id).getAktiviteter();
		for (int i = 0; i < b.size(); i++) {
			List<Event> e = b.get(i).getEventer();
			for (int j = 0; j < e.size(); j++) {
				eventer.add(e.get(j));
			}
		}
		return eventer;
	}

	@Override
	public List<Aktivitet> finnAlleAktiviteterTilBruker(Integer id) {
		List<Aktivitet> a = new ArrayList<>();
		a.addAll(em.find(Bruker.class, id).getAktiviteter());
		return a;
	}

}
