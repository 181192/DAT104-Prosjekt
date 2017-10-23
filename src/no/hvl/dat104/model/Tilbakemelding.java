package no.hvl.dat104.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * Modellrepresentasjon av Tilbakemelding
 * @author BMO 2.0
 *
 */
@Entity(name = "TilbakemeldingEntity")
@Table(name = "Tilbakemelding", schema = "db")
public class Tilbakemelding {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	Integer id;
	String stemme;
	
	@Column(name="id_Event")
	Integer idEvent;

	/**
	 * Tom konstruktor for Tilbakemelding
	 */
	public Tilbakemelding() {
		this("", null);
	}

	/**
	 * Hovedkonstruktor for Tilbakemelding
	 * @param id
	 * 		Id for tilbakemelding
	 * @param stemme
	 * 		Stemme for tilbakemelding
	 * @param idEvent
	 * 		Id for event paa tilbakemelding
	 */
	public Tilbakemelding(String stemme, Integer idEvent) {
		this.stemme = stemme;
		this.idEvent = idEvent;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @return the stemme
	 */
	public String getStemme() {
		return stemme;
	}

	/**
	 * @param stemme the stemme to set
	 */
	public void setStemme(String stemme) {
		this.stemme = stemme;
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
