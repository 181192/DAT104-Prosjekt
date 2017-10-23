package no.hvl.dat104.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "BrukerEntity")
@Table(name = "bruker", schema = "db")
public class Bruker {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	Integer id;
	String mail;
	String fornavn;
	String etternavn;
	String passord;
	String salt;
	
	@Column(name = "id_rolle")
	Integer idRolle;

	
	/**
	 Tom konstruktor for bruker
	 */
	public Bruker() {
		this("", "", "", "", "", null);
	}
	/**
	 * Hovedknstruktor for bruker
	 * @param mail 
	 * 		Mail til bruker
	 * @param fornavn 
	 * 		Fornavnet til brukeren
	 * @param etternavn 
	 * 		Etternavnet til brukeren
	 * @param passord 
	 * 		Passordet til brukeren
	 * @param salt 
	 * 		Salt for passord til brukeren
	 * @param idRolle 
	 * 		Id for rollen til brukeren
	 */
	public Bruker(String mail, String fornavn, String etternavn, String passord, String salt, Integer idRolle) {
		this.mail = mail;
		this.fornavn = fornavn;
		this.etternavn = etternavn;
		this.passord = passord;
		this.salt = salt;
		this.idRolle = idRolle;
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
	 * @param mail the mail to set
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
	 * @param fornavn the fornavn to set
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
	 * @param etternavn the etternavn to set
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
	 * @param passord the passord to set
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
	 * @param salt the salt to set
	 */
	public void setSalt(String salt) {
		this.salt = salt;
	}
	/**
	 * @return the idRolle
	 */
	public Integer getIdRolle() {
		return idRolle;
	}
	/**
	 * @param idRolle the idRolle to set
	 */
	public void setIdRolle(Integer idRolle) {
		this.idRolle = idRolle;
	}

	
	
	
	

}
