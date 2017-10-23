package no.hvl.dat104.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Modellrepresentasjon av Event
 * 
 * @author Kristoffer-Andre Kalliainen
 *
 */
@Entity(name = "Event")
@Table(name = "Event", schema = "db")
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String navn;

	@Column(name = "tid_fra")
	private Date tidFra;
	@Column(name = "tid_til")
	private Date tidTil;
	@Column(name = "faktisk_start")
	private Date faktiskStart;
	@Column(name = "faktisk_slutt")
	private Date faktiskSlutt;
	private String status;
	private String sted;
	@Column(name = "id_Aktivitet")
	private String idAktivitet;

	/**
	 * Tom konstruktor til Event
	 */
	public Event() {
		this("", null, null, null, null, "", "", null);
	}

	/**
	 * Kontruktor med parameter for Event
	 * 
	 * @param navn
	 *            Navnet paa eventen
	 * @param tidFra
	 *            Planlagt tid naar eventen begynner
	 * @param tidTil
	 *            Planlagt tid naar eventen slutter
	 * @param faktiskStart
	 *            Faktisk start paa event
	 * @param faktiskSlutt
	 *            Faktisk slutt paa event
	 * @param status
	 *            Status paa eventen
	 * @param sted
	 *            Stedet til eventen
	 * @param idAktivitet
	 *            Id paa aktiviteten eventen hoerer til
	 */
	public Event(String navn, Date tidFra, Date tidTil, Date faktiskStart, Date faktiskSlutt, String status,
			String sted, String idAktivitet) {
		this.navn = navn;
		this.tidFra = tidFra;
		this.tidTil = tidTil;
		this.faktiskStart = faktiskStart;
		this.faktiskSlutt = faktiskSlutt;
		this.status = status;
		this.sted = sted;
		this.idAktivitet = idAktivitet;
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
	 * @return the tidFra
	 */
	public Date getTidFra() {
		return tidFra;
	}

	/**
	 * @param tidFra
	 *            the tidFra to set
	 */
	public void setTidFra(Date tidFra) {
		this.tidFra = tidFra;
	}

	/**
	 * @return the tidTil
	 */
	public Date getTidTil() {
		return tidTil;
	}

	/**
	 * @param tidTil
	 *            the tidTil to set
	 */
	public void setTidTil(Date tidTil) {
		this.tidTil = tidTil;
	}

	/**
	 * @return the faktiskStart
	 */
	public Date getFaktiskStart() {
		return faktiskStart;
	}

	/**
	 * @param faktiskStart
	 *            the faktiskStart to set
	 */
	public void setFaktiskStart(Date faktiskStart) {
		this.faktiskStart = faktiskStart;
	}

	/**
	 * @return the faktiskSlutt
	 */
	public Date getFaktiskSlutt() {
		return faktiskSlutt;
	}

	/**
	 * @param faktiskSlutt
	 *            the faktiskSlutt to set
	 */
	public void setFaktiskSlutt(Date faktiskSlutt) {
		this.faktiskSlutt = faktiskSlutt;
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
	 * @return the sted
	 */
	public String getSted() {
		return sted;
	}

	/**
	 * @param sted
	 *            the sted to set
	 */
	public void setSted(String sted) {
		this.sted = sted;
	}

	/**
	 * @return the idAktivitet
	 */
	public String getIdAktivitet() {
		return idAktivitet;
	}

	/**
	 * @param idAktivitet
	 *            the idAktivitet to set
	 */
	public void setIdAktivitet(String idAktivitet) {
		this.idAktivitet = idAktivitet;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

}
