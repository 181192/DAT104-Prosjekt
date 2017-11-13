package no.hvl.dat104.util;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.ejb.EJB;

import no.hvl.dat104.dataaccess.IEventEAO;
import no.hvl.dat104.model.Event;
import no.hvl.dat104.model.Tilbakemelding;

public class FormaterTilbakemeldingUtil {

	/**
	 * En liste med formaterte tilbakemeldinger, blir returnert etter konvertering
	 */
	private List<FormatertTilbakemelding> formaterteTilbakemeldinger = new ArrayList<FormatertTilbakemelding>();
	private static final Integer FIVE_MINUTES = 1000 * 60 * 2 + (30*1000);
	private static final Integer NEG_FIVE_MINUTES = -1000 * 60 * 2 + (30*1000);

	/**
	 * Tar inn liste med tilbakemeldinger som argument, konverterer til en liste som
	 * er enklere å arbeide med
	 * 
	 * @param liste
	 * @return
	 */

	public AktivitetTilbakemelding formaterTilbakemeldingerForAktivitetsResultatVisning(String navn, List<Tilbakemelding> tbliste) {
		AktivitetTilbakemelding aktivitetsTilbakemeldinger = gjorTilFormatTb(navn, tbliste);
		return aktivitetsTilbakemeldinger;

	}

	private AktivitetTilbakemelding gjorTilFormatTb(String navn, List<Tilbakemelding> tbliste) {
		AktivitetTilbakemelding ftb = new AktivitetTilbakemelding();
		ftb.setNavn(navn);
		
		for(Tilbakemelding t:tbliste) {
			switch (Integer.valueOf(t.getStemme())) {
			case 0:
				ftb.incrementFornoyd();
				break;
			case 1:
				ftb.incrementNoytral();
				break;
			case 2:
				ftb.incrementMisfornoyd();
				break;
			}
		}
		return ftb;
	}

	public List<FormatertTilbakemelding> formaterTilbakemeldinger(List<Tilbakemelding> liste) {
		if (!liste.isEmpty()) {
			//sorter
			Collections.sort(liste, new Comparator<Tilbakemelding>() {
				@Override
				public int compare(final Tilbakemelding ftb1, Tilbakemelding ftb2) {
					return ftb1.getTid().compareTo(ftb2.getTid());
				}
			});
			for (Tilbakemelding tilbakemelding : liste) {
				if (!tilbakemeldingMedSammeTidsStempelEksisterer(tilbakemelding.getTid())) {
					FormatertTilbakemelding tilbakemeldingFormatert = new FormatertTilbakemelding();
					konverterStemmeOgInkrementerTilbakemelding(tilbakemeldingFormatert, tilbakemelding.getStemme());
					tilbakemeldingFormatert.setTid(tilbakemelding.getTid());
					formaterteTilbakemeldinger.add(tilbakemeldingFormatert);
					System.out.println("ingen andre innenfor 5 min");
				} else {
					finnTilbakemeldingMedSammeTidOgLeggTilStemme(tilbakemelding.getTid(), tilbakemelding.getStemme());
					System.out.println("Det er andre innen 5 min");
				}
			}
			sorterTilbakemeldingene();
			for(FormatertTilbakemelding t:formaterteTilbakemeldinger) {
				System.out.println(t.toString());
			}
			return formaterteTilbakemeldinger;
		} else {
			formaterteTilbakemeldinger = null;
			return formaterteTilbakemeldinger;
		}
	}


	/**
	 * Sorterer listen med formaterte tilbakemeldinger etter dato.
	 */
	private void sorterTilbakemeldingene() {

		Collections.sort(formaterteTilbakemeldinger, new Comparator<FormatertTilbakemelding>() {
			@Override
			public int compare(final FormatertTilbakemelding ftb1, FormatertTilbakemelding ftb2) {
				return ftb1.getTid().compareTo(ftb2.getTid());
			}
		});
	}

	/**
	 * Hjelpemetode, leter gjennom listen med konverterte tilbakemeldinger og finner
	 * en tilbakemelding med samme tid og øker stemme antallet
	 * 
	 * @param tid
	 * @param stemme
	 */
	private void finnTilbakemeldingMedSammeTidOgLeggTilStemme(Timestamp tid, String stemme) {
		Calendar cal = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		for (FormatertTilbakemelding formatertTilbakemelding : formaterteTilbakemeldinger) {
			cal.setTime(formatertTilbakemelding.getTid());
			cal.add(Calendar.MINUTE, FIVE_MINUTES);
			cal2.setTime(formatertTilbakemelding.getTid());
			cal2.add(Calendar.MINUTE, NEG_FIVE_MINUTES);
			
			if ((tid.before(formatertTilbakemelding.getTid()) && tid.after(cal2.getTime())) || (tid.after(formatertTilbakemelding.getTid()) && tid.before(cal.getTime()))) {
				switch (Integer.valueOf(stemme)) {
				case 0:
					formatertTilbakemelding.incrementFornoyd();
					break;
				case 1:
					formatertTilbakemelding.incrementNoytral();
					break;
				case 2:
					formatertTilbakemelding.incrementMisfornoyd();
					break;
				}
				;
			}
		}
	}

	/**
	 * Konverterer stemme fra string format til konvertert tilbakemelding format
	 * 
	 * @param formatertTilbakemelding
	 * @param stemme
	 */
	private void konverterStemmeOgInkrementerTilbakemelding(FormatertTilbakemelding formatertTilbakemelding,
			String stemme) {
		switch (Integer.valueOf(stemme)) {
		case 0:
			formatertTilbakemelding.incrementFornoyd();
			break;
		case 1:
			formatertTilbakemelding.incrementNoytral();
			break;
		case 2:
			formatertTilbakemelding.incrementMisfornoyd();
			break;
		}
		;
	}

	/**
	 * Sjekker om listen med formaterte tilbakemeldinger har en tilbakemelding med
	 * samme timestamp
	 * 
	 * @param tid
	 * @return
	 */
	private boolean tilbakemeldingMedSammeTidsStempelEksisterer(Timestamp tid) {
		Calendar cal = Calendar.getInstance();
		for (FormatertTilbakemelding ft : formaterteTilbakemeldinger) {
			cal.setTime(ft.getTid());
			cal.add(Calendar.MINUTE, FIVE_MINUTES);
			
			if (tid.after(ft.getTid()) && tid.before(cal.getTime())) {
				return true;
			}
		}
		return false;
	}
}