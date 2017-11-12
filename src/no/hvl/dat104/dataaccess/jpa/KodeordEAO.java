package no.hvl.dat104.dataaccess.jpa;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import no.hvl.dat104.dataaccess.IKodeordEAO;
import no.hvl.dat104.model.Event;
import no.hvl.dat104.model.Kodeord;

@Stateless
public class KodeordEAO implements IKodeordEAO {
	@PersistenceContext(name = "g03PersistenceUnit")
	private EntityManager em;

	@Override
	public void leggTilKodeord(Kodeord k) {
		em.persist(k);
	}

	@Override
	public Kodeord finnKodeord(Integer id) {
		return em.find(Kodeord.class, id);
	}

	@Override
	public void oppdaterKodeord(Kodeord k) {
		em.merge(k);

	}

	@Override
	public void slettKodeord(Kodeord k) {
		em.remove(em.find(Kodeord.class, k.getId()));

	}
	
	@Override
	public boolean slettKodeordTilEvent(Event e) {
		
		Query query = em.createQuery("DELETE FROM Kodeord k WHERE k.idEvent.id = :id").setParameter("id", e.getId());
		return query.executeUpdate() == 1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Kodeord> alleKodeord() {
		List<Kodeord> kodeord = em.createQuery("SELECT k FROM kodeord k ORDER BY ASC").getResultList();
		return kodeord;
	}

	@Override
	public void endreKodePaaKodeord(Integer id, Integer kode) {
		Kodeord k = finnKodeord(id);
		k.setKode(kode);
		em.merge(k);
	}

	@Override
	public Boolean sjekkOmKodeordErUnik(Kodeord kode) {
		String sql = "SELECT COUNT(k.id) FROM Kodeord k WHERE k.kode = " + kode.getKode();
		Long q = (Long) em.createQuery(sql).getSingleResult();
		return q < 1;
	}

	@Override
	public Kodeord finnKodeordTilEvent(Event event) {
		try {
			Query query = em.createQuery("Select k from Kodeord k where k.idEvent.id = :id").setParameter("id",
					event.getId());
			return (Kodeord) query.getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Kodeord> finnKodeordBasertPaaKode(Integer kode) {
		return (List<Kodeord>) em.createQuery("SELECT k FROM Kodeord k WHERE k.kode =" + kode).getResultList();
	}

}
