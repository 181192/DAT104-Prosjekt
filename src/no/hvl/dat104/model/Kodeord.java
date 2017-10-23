package no.hvl.dat104.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "KodeordEntity")
@Table(name = "kodeord", schema = "db")
public class Kodeord {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	Integer id;
	String kode;
	
	@Column (name="id_event")
	Integer idEvent;

	
	/**
	 Tom konstruktor for kodeord
	 */
	public Kodeord() {
		this("", null);
	}


	/**
	 * Hovedkonstroktor for kodeord
	 * @param kode 
	 * 		Kodeord for eventet
	 * @param idEvent
	 * 		Id for eventet
	 */
	public Kodeord(String kode, Integer idEvent) {
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
	public String getKode() {
		return kode;
	}


	/**
	 * @param kode the kode to set
	 */
	public void setKode(String kode) {
		this.kode = kode;
	}


	/**
	 * @return the idEvent
	 */
	public Integer getIdEvent() {
		return idEvent;
	}


	/**
	 * @param idEvent the idEvent to set
	 */
	public void setIdEvent(Integer idEvent) {
		this.idEvent = idEvent;
	}
	
}
