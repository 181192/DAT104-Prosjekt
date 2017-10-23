package no.hvl.dat104.dataaccess.jpa;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import no.hvl.dat104.dataaccess.ITilbakemeldingEAO;
import no.hvl.dat104.model.Tilbakemelding;

@Stateless
public class TilbakemeldingEAO implements ITilbakemeldingEAO {
	@PersistenceContext(name = "g03PersistenceUnit")
	private EntityManager em;

	@Override
	public void leggTilTilbakemelding(Tilbakemelding t) {
		// TODO Auto-generated method stub

	}

	@Override
	public Tilbakemelding finnTilbakemelding(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void oppdaterTilbakemelding(Tilbakemelding t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void slettTilbakemelding(Tilbakemelding t) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Tilbakemelding> alleTilbakemeldingene() {
		// TODO Auto-generated method stub
		return null;
	}

}
