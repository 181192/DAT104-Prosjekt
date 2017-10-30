package no.hvl.dat104.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Modellrepresentasjon av Rettigheter
 * 
 * @author Kristoffer-Andre Kalliainen
 *
 */
@Entity(name = "Rettigheter")
@Table(name = "rettigheter", schema = "db")
public class Rettigheter {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "godkjenne_bruker")
	private Boolean godkjenneBruker;
	@Column(name = "slette_bruker")
	private Boolean sletteBruker;
	@Column(name = "opprette_bruker")
	private Boolean oppretteBruker;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_rolle", referencedColumnName = "id")
	private Rolle idRolle;

	/**
	 * Tom konstruktor for Rettigheter
	 */
	public Rettigheter() {
		this(null, null, null, null);
	}

	/**
	 * Konstrutor med parametre for Rettigheter
	 * 
	 * @param godkjenneBruker
	 *            Rettighet for å godkjenne brukere
	 * @param sletteBruker
	 *            Rettighet for å slette brukere
	 * @param oppretteBruker
	 *            Rettighet for å opprette brukere
	 * @param idRolle
	 *            Id for rollen som bruker rettighetene
	 */
	public Rettigheter(Boolean godkjenneBruker, Boolean sletteBruker, Boolean oppretteBruker, Rolle idRolle) {
		this.godkjenneBruker = godkjenneBruker;
		this.sletteBruker = sletteBruker;
		this.oppretteBruker = oppretteBruker;
		this.idRolle = idRolle;
	}

	/**
	 * @return the godkjenneBruker
	 */
	public Boolean getGodkjenneBruker() {
		return godkjenneBruker;
	}

	/**
	 * @param godkjenneBruker
	 *            the godkjenneBruker to set
	 */
	public void setGodkjenneBruker(Boolean godkjenneBruker) {
		this.godkjenneBruker = godkjenneBruker;
	}

	/**
	 * @return the sletteBruker
	 */
	public Boolean getSletteBruker() {
		return sletteBruker;
	}

	/**
	 * @param sletteBruker
	 *            the sletteBruker to set
	 */
	public void setSletteBruker(Boolean sletteBruker) {
		this.sletteBruker = sletteBruker;
	}

	/**
	 * @return the oppretteBruker
	 */
	public Boolean getOppretteBruker() {
		return oppretteBruker;
	}

	/**
	 * @param oppretteBruker
	 *            the oppretteBruker to set
	 */
	public void setOppretteBruker(Boolean oppretteBruker) {
		this.oppretteBruker = oppretteBruker;
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
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
}
