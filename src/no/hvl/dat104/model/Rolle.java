package no.hvl.dat104.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "RolleEntity")
@Table(name = "rolle", schema = "db")
public class Rolle {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	Integer id;
	String type;
	
	/**
	 Tom konstruktor for Rolle
	 */
	public Rolle() {
		this("");
	}
	
	/**
	 * Hovedkonstruktor for Rolle
	 * @param id
	 * 		Id til rollen
	 * @param type
	 * 		Typen rolle
	 */
	public Rolle(String type) {
		this.type = type;
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
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	
	

}
