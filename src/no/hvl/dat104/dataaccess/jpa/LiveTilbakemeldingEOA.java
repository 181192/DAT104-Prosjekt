/**
 * 
 */
package no.hvl.dat104.dataaccess.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import no.hvl.dat104.dataaccess.ILiveTilbakemeldingEAO;
import no.hvl.dat104.model.LiveTilbakemelding;

/**
 * @author krist
 *
 */
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
		em.remove(em.find(LiveTilbakemelding.class, t.getId()));

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LiveTilbakemelding> alleLiveTilbakemeldingene() {
		List<LiveTilbakemelding> tilbakemeldinger = em.createQuery("SELECT t FROM LiveTilbakemelding t")
				.getResultList();
		return tilbakemeldinger;
	}

}
