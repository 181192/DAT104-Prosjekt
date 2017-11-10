package no.hvl.dat104.dataaccess.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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

	@SuppressWarnings("unchecked")
	@Override
	public Bruker finnBrukerPaaEmail(String mail) {
		List<Bruker> bruker = em.createQuery("SELECT b FROM Bruker b WHERE b.mail = :mail").setParameter("mail", mail)
				.getResultList();
		if (bruker.isEmpty()) {
			return null;
		}
		return bruker.get(0);
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
		Query b = em.createQuery("SELECT b FROM Bruker b ORDER BY b.idRolle DESC, b.etternavn");
		return b.getResultList();
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

	@Override
	public void finnBrukerLeggTilEvent(Integer id, Event e, Integer aktivitetId) {
		Bruker b = finnBruker(id);
		List<Aktivitet> a = b.getAktiviteter();
		Aktivitet aktivitet = null;
		for (Aktivitet k : a) {
			if (k != null) {
				if (k.getId().equals(aktivitetId)) {
					aktivitet = k;
				}
			}
		}
		aktivitet.getEventer().add(e);
		e.setIdAktivitet(aktivitet);
		em.persist(e);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Aktivitet> alleAktiviteterIJPQL(Integer id) {
		List<Aktivitet> aktiviteter = em
				.createQuery("SELECT a FROM Aktivitet a WHERE a.idBruker.id = :id ORDER BY a.id").setParameter("id", id)
				.getResultList();
		return aktiviteter;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean eierBrukerAktivitet(Integer id, Integer idAktivitet) {
		List<Aktivitet> aktiviteter = em.createQuery(
				"SELECT a FROM Aktivitet a, Bruker b WHERE a.idBruker = b AND a.id = :akId AND b.id = :brukerId")
				.setParameter("akId", idAktivitet).setParameter("brukerId", id).getResultList();
		if (aktiviteter.isEmpty()) {
			return false;
		}
		return true;
	}

}
