package no.hvl.dat104.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Modellrepresentasjon av Aktivitet
 * 
 * @author Kristoffer-Andre Kalliainen
 *
 */
@Entity(name = "Aktivitet")
@Table(name = "Aktivitet", schema = "db")
public class Aktivitet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String navn;
	private String status;
	@Column(name = "id_Bruker")
	private Integer idBruker;

	/**
	 * Tom kontruktor for Aktivitet
	 */
	public Aktivitet() {
		this("", "", null);
	}

	/**
	 * Kontruktor for Aktivitet
	 * 
	 * @param navn
	 *            Navnet på aktivitet
	 * @param status
	 *            Statusen på aktiviteten
	 * @param idBruker
	 *            Id til bruker som opprettet aktiviteten
	 */
	public Aktivitet(String navn, String status, Integer idBruker) {
		this.navn = navn;
		this.status = status;
		this.idBruker = idBruker;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @return the navn
	 */
	public String getNavn() {
		return navn;
	}

	/**
	 * @param navn
	 *            the navn to set
	 */
	public void setNavn(String navn) {
		this.navn = navn;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the idBruker
	 */
	public Integer getIdBruker() {
		return idBruker;
	}

	/**
	 * @param idBruker
	 *            the idBruker to set
	 */
	public void setIdBruker(Integer idBruker) {
		this.idBruker = idBruker;
	}

}
