package no.hvl.dat104.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

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
	private Timestamp tidFra;
	@Column(name = "tid_til")
	private Timestamp tidTil;
	@Column(name = "faktisk_start")
	private Timestamp faktiskStart;
	@Column(name = "faktisk_slutt")
	private Timestamp faktiskSlutt;
	private String status;
	private String sted;

	@ManyToOne
	@Column(name = "id_Aktivitet")
	private Aktivitet idAktivitet;

	@Transient
	private List<Tilbakemelding> tilbakemeldinger;

	@Transient
	private List<LiveTilbakemelding> liveTilbakemeldinger;

	/**
	 * Tom konstruktor til Event
	 */
	public Event() {
		this("", null, null, null, null, "", "", null);
		tilbakemeldinger = new ArrayList<>();
		liveTilbakemeldinger = new ArrayList<>();
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
	public Event(String navn, Timestamp tidFra, Timestamp tidTil, Timestamp faktiskStart, Timestamp faktiskSlutt,
			String status, String sted, Aktivitet idAktivitet) {
		this.navn = navn;
		this.tidFra = tidFra;
		this.tidTil = tidTil;
		this.faktiskStart = faktiskStart;
		this.faktiskSlutt = faktiskSlutt;
		this.status = status;
		this.sted = sted;
		this.idAktivitet = idAktivitet;
		tilbakemeldinger = new ArrayList<>();
		liveTilbakemeldinger = new ArrayList<>();
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
	public Timestamp getTidFra() {
		return tidFra;
	}

	/**
	 * @param tidFra
	 *            the tidFra to set
	 */
	public void setTidFra(Timestamp tidFra) {
		this.tidFra = tidFra;
	}

	/**
	 * @return the tidTil
	 */
	public Timestamp getTidTil() {
		return tidTil;
	}

	/**
	 * @param tidTil
	 *            the tidTil to set
	 */
	public void setTidTil(Timestamp tidTil) {
		this.tidTil = tidTil;
	}

	/**
	 * @return the faktiskStart
	 */
	public Timestamp getFaktiskStart() {
		return faktiskStart;
	}

	/**
	 * @param faktiskStart
	 *            the faktiskStart to set
	 */
	public void setFaktiskStart(Timestamp faktiskStart) {
		this.faktiskStart = faktiskStart;
	}

	/**
	 * @return the faktiskSlutt
	 */
	public Timestamp getFaktiskSlutt() {
		return faktiskSlutt;
	}

	/**
	 * @param faktiskSlutt
	 *            the faktiskSlutt to set
	 */
	public void setFaktiskSlutt(Timestamp faktiskSlutt) {
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
	public Aktivitet getIdAktivitet() {
		return idAktivitet;
	}

	/**
	 * @param idAktivitet
	 *            the idAktivitet to set
	 */
	public void setIdAktivitet(Aktivitet idAktivitet) {
		this.idAktivitet = idAktivitet;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @return the tilbakemeldinger
	 */
	public List<Tilbakemelding> getTilbakemeldinger() {
		return tilbakemeldinger;
	}

	/**
	 * @param tilbakemeldinger
	 *            the tilbakemeldinger to set
	 */
	public void setTilbakemeldinger(List<Tilbakemelding> tilbakemeldinger) {
		this.tilbakemeldinger = tilbakemeldinger;
	}

	/**
	 * @return the liveTilbakemeldinger
	 */
	public List<LiveTilbakemelding> getLiveTilbakemeldinger() {
		return liveTilbakemeldinger;
	}

	/**
	 * @param liveTilbakemeldinger
	 *            the liveTilbakemeldinger to set
	 */
	public void setLiveTilbakemeldinger(List<LiveTilbakemelding> liveTilbakemeldinger) {
		this.liveTilbakemeldinger = liveTilbakemeldinger;
	}

}
