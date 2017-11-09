package no.hvl.dat104.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Modellrepresentasjon av Event
 * 
 * @author Kristoffer-Andre Kalliainen
 *
 */
@Entity(name = "Event")
@Table(name = "event", schema = "db")
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String navn;
	private String beskrivelse;
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
	@JoinColumn(name = "id_aktivitet", referencedColumnName = "id")
	private Aktivitet idAktivitet;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idEvent")
	private List<Tilbakemelding> tilbakemeldinger;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idEvent")
	private List<LiveTilbakemelding> liveTilbakemeldinger;

	/**
	 * Tom konstruktor til Event
	 */
	public Event() {
		this(null, "", "", null, null, null, null, "", "", null);
		tilbakemeldinger = new ArrayList<>();
		liveTilbakemeldinger = new ArrayList<>();

	}

	/**
	 * Konstruktør for Event med parameter
	 * 
	 * @param id
	 *            ID til event
	 * @param navn
	 *            Navnet på event
	 * @param beskrivelse
	 *            Beskrivelse på event
	 * @param tidFra
	 *            Tid planlagt fra
	 * @param tidTil
	 *            Tid planlagt til
	 * @param faktiskStart
	 *            Faktisk start
	 * @param faktiskSlutt
	 *            Faktisk slutt
	 * @param status
	 *            Status på event
	 * @param sted
	 *            Stedet til event
	 * @param idAktivitet
	 *            Aktiviteten til eventet
	 * @param tilbakemeldinger
	 *            Tilbakemeldingene
	 * @param liveTilbakemeldinger
	 *            Live tilbakemeldingene
	 */
	public Event(Integer id, String navn, String beskrivelse, Timestamp tidFra, Timestamp tidTil,
			Timestamp faktiskStart, Timestamp faktiskSlutt, String status, String sted, Aktivitet idAktivitet) {
		this.id = id;
		this.navn = navn;
		this.beskrivelse = beskrivelse;
		this.tidFra = tidFra;
		this.tidTil = tidTil;
		this.faktiskStart = faktiskStart;
		this.faktiskSlutt = faktiskSlutt;
		this.status = status;
		this.sted = sted;
		this.idAktivitet = idAktivitet;
		this.tilbakemeldinger = new ArrayList<>();
		this.liveTilbakemeldinger = new ArrayList<>();
	}

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
	 * @return the beskrivelse
	 */
	public String getBeskrivelse() {
		return beskrivelse;
	}

	/**
	 * @param beskrivelse
	 *            the beskrivelse to set
	 */
	public void setBeskrivelse(String beskrivelse) {
		this.beskrivelse = beskrivelse;
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

	/**
	 * Custom toString metode, for å skrive ut innholdet av objektet (N.B. Sjekker
	 * ikke om verdier er null!)
	 */
	public String tilStreng() {
		return "Event [id=" + id + ", \nnavn=" + navn + ", \ntidFra=" + tidFra + ", \ntidTil=" + tidTil
				+ ", \nfaktiskStart=" + faktiskStart + ", \nfaktiskSlutt=" + faktiskSlutt + ", \nstatus=" + status
				+ ", \nsted=" + sted + ", \nidAktivitet=" + idAktivitet + ", \ntilbakemeldinger=" + tilbakemeldinger
				+ ", \nliveTilbakemeldinger=" + liveTilbakemeldinger + "]";
	}

}
