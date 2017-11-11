package no.hvl.dat104.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * Modellrepresentasjon av Kodeord
 * 
 * @author BMO 2.0
 *
 */
@Entity(name = "Kodeord")
@Table(name = "kodeord", schema = "db")
public class Kodeord {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer kode;

	@OneToOne
	@JoinColumn(name = "id_event", referencedColumnName = "id")
	private Event idEvent;

	/**
	 * Tom konstruktor for Kodeord
	 */
	public Kodeord() {
		this(null, null);
	}

	/**
	 * Hovedkonstroktor for Kodeord
	 * 
	 * @param kode
	 *            Kodeord for eventet
	 * @param idEvent
	 *            Id for eventet
	 */
	public Kodeord(Integer kode, Event idEvent) {
		this.kode = kode;
		this.idEvent = idEvent;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @return the kode
	 */
	public Integer getKode() {
		return kode;
	}

	/**
	 * @param kode
	 *            the kode to set
	 */
	public void setKode(Integer kode) {
		this.kode = kode;
	}

	/**
	 * @return the idEvent
	 */
	public Event getIdEvent() {
		return idEvent;
	}

	/**
	 * @param idEvent
	 *            the idEvent to set
	 */
	public void setIdEvent(Event idEvent) {
		this.idEvent = idEvent;
	}

}
