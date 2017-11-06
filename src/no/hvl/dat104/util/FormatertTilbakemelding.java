package no.hvl.dat104.util;

import java.sql.Timestamp;
import java.time.Instant;

public class FormatertTilbakemelding {
	private Timestamp tid;
	private Integer fornoyd;
	private Integer noytral;
	private Integer misfornoyd;

	public FormatertTilbakemelding() {
		this.tid = Timestamp.from(Instant.now());
		this.fornoyd = 0;
		this.noytral = 0;
		this.misfornoyd = 0;
	}

	public FormatertTilbakemelding(Integer fornoyd, Integer noytral, Integer misfornoyd) {
		super();
		this.tid = Timestamp.from(Instant.now());
		this.fornoyd = fornoyd;
		this.noytral = noytral;
		this.misfornoyd = misfornoyd;
	}

	public void incrementFornoyd() {
		fornoyd++;
	}

	public void incrementNoytral() {
		noytral++;
	}

	public void incrementMisfornoyd() {
		misfornoyd++;
	}

	public Timestamp getTid() {
		return tid;
	}

	public void setTid(Timestamp tid) {
		this.tid = tid;
	}

	public Integer getFornoyd() {
		return fornoyd;
	}

	public void setFornoyd(Integer fornoyd) {
		this.fornoyd = fornoyd;
	}

	public Integer getNoytral() {
		return noytral;
	}

	public void setNoytral(Integer noytral) {
		this.noytral = noytral;
	}

	public Integer getMisfornoyd() {
		return misfornoyd;
	}

	public void setMisfornoyd(Integer misfornoyd) {
		this.misfornoyd = misfornoyd;
	}
	
	public String toString() {
		return ("Date: " + this.tid.toString() + " Fornøyd: " + this.fornoyd + " Nøytral: " + this.noytral + " Misfornøyd" + this.misfornoyd);
	}
}
