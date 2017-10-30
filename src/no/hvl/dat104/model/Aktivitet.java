package no.hvl.dat104.model;

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
 * Modellrepresentasjon av Aktivitet
 * 
 * @author Kristoffer-Andre Kalliainen
 *
 */
@Entity(name = "Aktivitet")
@Table(name = "aktivitet", schema = "db")
public class Aktivitet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String navn;
	private String status;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_bruker", referencedColumnName = "id")
	private Bruker idBruker;

	@OneToMany(mappedBy = "idAktivitet")
	private List<Event> eventer;

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
	public Aktivitet(String navn, String status, Bruker idBruker) {
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
	public Bruker getIdBruker() {
		return idBruker;
	}

	/**
	 * @param idBruker
	 *            the idBruker to set
	 */
	public void setIdBruker(Bruker idBruker) {
		this.idBruker = idBruker;
	}

	/**
	 * @return the eventer
	 */
	public List<Event> getEventer() {
		return eventer;
	}

	/**
	 * @param eventer
	 *            the eventer to set
	 */
	public void setEventer(List<Event> eventer) {
		this.eventer = eventer;
	}

}
