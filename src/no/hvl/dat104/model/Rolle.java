package no.hvl.dat104.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 
 * Modellrepresentasjon av Rolle
 * 
 * @author BMO 2.0
 *
 */
@Entity(name = "RolleEntity")
@Table(name = "rolle", schema = "db")
public class Rolle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String type;

	@Transient
	private List<Bruker> brukere;
	@Transient
	private List<Rettigheter> rettigheter;

	/**
	 * Tom konstruktor for Rolle
	 */
	public Rolle() {
		this("");
		brukere = new ArrayList<>();
		rettigheter = new ArrayList<>();
	}

	/**
	 * Hovedkonstruktor for Rolle
	 * 
	 * @param id
	 *            Id til rollen
	 * @param type
	 *            Typen rolle
	 */
	public Rolle(String type) {
		this.type = type;
		brukere = new ArrayList<>();
		rettigheter = new ArrayList<>();
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the brukere
	 */
	public List<Bruker> getBrukere() {
		return brukere;
	}

	/**
	 * @param brukere
	 *            the brukere to set
	 */
	public void setBrukere(List<Bruker> brukere) {
		this.brukere = brukere;
	}

	/**
	 * @return the rettigheter
	 */
	public List<Rettigheter> getRettigheter() {
		return rettigheter;
	}

	/**
	 * @param rettigheter
	 *            the rettigheter to set
	 */
	public void setRettigheter(List<Rettigheter> rettigheter) {
		this.rettigheter = rettigheter;
	}

}
