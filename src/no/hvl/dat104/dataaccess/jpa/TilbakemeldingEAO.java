package no.hvl.dat104.dataaccess.jpa;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import no.hvl.dat104.dataaccess.ITilbakemeldingEAO;
import no.hvl.dat104.model.Event;
import no.hvl.dat104.model.Tilbakemelding;

@Stateless
public class TilbakemeldingEAO implements ITilbakemeldingEAO {
	@PersistenceContext(name = "g03PersistenceUnit")
	private EntityManager em;

	@Override
	public void leggTilTilbakemelding(Tilbakemelding t) {
		em.persist(t);

	}

	@Override
	public Tilbakemelding finnTilbakemelding(Integer id) {
		return em.find(Tilbakemelding.class, id);
	}

	@Override
	public void oppdaterTilbakemelding(Tilbakemelding t) {
		em.merge(t);

	}

	@Override
	public void slettTilbakemelding(Tilbakemelding t) {
		em.remove(em.find(Tilbakemelding.class, t.getId()));

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tilbakemelding> alleTilbakemeldingene() {
		List<Tilbakemelding> tilbakemeldinger = em.createQuery("SELECT t FROM Tilbakemelding t").getResultList();
		return tilbakemeldinger;
	}

	@Override
	public boolean slettAlleTilbakemeldingTilEvent(Event e) {
		try {
			Query query = em.createQuery("DELETE FROM Tilbakemelding t WHERE t.idEvent.id = :id").setParameter("id", e.getId());
			return query.executeUpdate() == 1;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}
}
