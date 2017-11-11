/**
 * 
 */
package no.hvl.dat104.dataaccess.jpa;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import no.hvl.dat104.dataaccess.ILiveTilbakemeldingEAO;
import no.hvl.dat104.model.Event;
import no.hvl.dat104.model.LiveTilbakemelding;

/**
 * @author krist
 *
 */
@Stateless
public class LiveTilbakemeldingEOA implements ILiveTilbakemeldingEAO {
	@PersistenceContext(name = "g03PersistenceUnit")
	private EntityManager em;

	@Override
	public void leggTilLiveTilbakemelding(LiveTilbakemelding t) {
		em.persist(t);
	}

	@Override
	public LiveTilbakemelding finnLiveTilbakemelding(Integer id) {
		return em.find(LiveTilbakemelding.class, id);
	}

	@Override
	public void oppdaterLiveTilbakemelding(LiveTilbakemelding t) {
		em.merge(t);

	}

	@Override
	public void slettLiveTilbakemelding(LiveTilbakemelding t) {
		try {
			em.remove(em.find(LiveTilbakemelding.class, t.getId()));
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
	
	@Override
	public boolean slettAlleLiveTilbakemeldingTilEvent(Event e) {
		try {
			Query query = em.createQuery("DELETE FROM LiveTilbakemelding t WHERE t.idEvent.id = :id").setParameter("id", e.getId());
			return query.executeUpdate() == 1;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LiveTilbakemelding> alleLiveTilbakemeldingene() {
		List<LiveTilbakemelding> tilbakemeldinger = em.createQuery("SELECT t FROM LiveTilbakemelding t")
				.getResultList();
		return tilbakemeldinger;
	}

}
