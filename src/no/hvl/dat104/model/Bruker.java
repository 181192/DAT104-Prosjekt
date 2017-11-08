package no.hvl.dat104.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 
 * Modellrepresentasjon av Bruker
 * 
 * @author BMO 2.0
 *
 */
@Entity(name = "Bruker")
@Table(schema = "db", name = "bruker")
public class Bruker {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String mail;
	private String fornavn;
	private String etternavn;
	private String passord;
	private String salt;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_rolle", referencedColumnName = "id")
	private Rolle idRolle;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idBruker")
	private List<Aktivitet> aktiviteter;

	/**
	 * Tom konstruktor for Bruker
	 */
	public Bruker() {
		this("", "", "", "", "", null);
		aktiviteter = new ArrayList<>();
	}

	/**
	 * Hovedknstruktor for Bruker
	 * 
	 * @param mail
	 *            Mail til bruker
	 * @param fornavn
	 *            Fornavnet til brukeren
	 * @param etternavn
	 *            Etternavnet til brukeren
	 * @param passord
	 *            Passordet til brukeren
	 * @param salt
	 *            Salt for passord til brukeren
	 * @param idRolle
	 *            Id for rollen til brukeren
	 */
	public Bruker(String mail, String fornavn, String etternavn, String passord, String salt, Rolle idRolle) {
		this.mail = mail;
		this.fornavn = fornavn;
		this.etternavn = etternavn;
		this.passord = passord;
		this.salt = salt;
		this.idRolle = idRolle;
		aktiviteter = new ArrayList<>();
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * @param mail
	 *            the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * @return the fornavn
	 */
	public String getFornavn() {
		return fornavn;
	}

	/**
	 * @param fornavn
	 *            the fornavn to set
	 */
	public void setFornavn(String fornavn) {
		this.fornavn = fornavn;
	}

	/**
	 * @return the etternavn
	 */
	public String getEtternavn() {
		return etternavn;
	}

	/**
	 * @param etternavn
	 *            the etternavn to set
	 */
	public void setEtternavn(String etternavn) {
		this.etternavn = etternavn;
	}

	/**
	 * @return the passord
	 */
	public String getPassord() {
		return passord;
	}

	/**
	 * @param passord
	 *            the passord to set
	 */
	public void setPassord(String passord) {
		this.passord = passord;
	}

	/**
	 * @return the salt
	 */
	public String getSalt() {
		return salt;
	}

	/**
	 * @param salt
	 *            the salt to set
	 */
	public void setSalt(String salt) {
		this.salt = salt;
	}

	/**
	 * @return the idRolle
	 */
	public Rolle getIdRolle() {
		return idRolle;
	}

	/**
	 * @param idRolle
	 *            the idRolle to set
	 */
	public void setIdRolle(Rolle idRolle) {
		this.idRolle = idRolle;
	}

	/**
	 * @return the aktiviteter
	 */
	public List<Aktivitet> getAktiviteter() {
		return aktiviteter;
	}

	/**
	 * @param aktiviteter
	 *            the aktiviteter to set
	 */
	public void setAktiviteter(List<Aktivitet> aktiviteter) {
		this.aktiviteter = aktiviteter;
	}

}
