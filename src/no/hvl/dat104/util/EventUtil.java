package no.hvl.dat104.util;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import no.hvl.dat104.model.Event;
import no.hvl.dat104.model.LiveTilbakemelding;

public class EventUtil {
	
	public static List<Event> sorterEventer(List<Event> eventListe){
		Collections.sort(eventListe, new Comparator<Event>() {
			public int compare(Event e1, Event e2) {
				return e1.getTidFra().compareTo(e2.getTidFra());
			}
		});
		
		return eventListe;
		
	}

	public static List<Integer> liveTilbakemeldingerSomInt(List<LiveTilbakemelding> list, Timestamp faktiskStart){
		//List<LiveTilbakemelding> ltbList;
		List<Integer> result = new ArrayList<>();
		
		for(LiveTilbakemelding lt : list) {
			Integer tilbakemelding = formaterTilbakemelding(
					faktiskStart, lt.getTid(), Integer.parseInt(lt.getStemme()));
			result.add(tilbakemelding);
		}
		
		return result;
		
	};
	
    public static Integer formaterTilbakemelding(Timestamp faktiskStart, 
            Timestamp tidTilbakemelding, Integer tilbakemelding){
        
        Long diffMillisekunder = tidTilbakemelding.getTime() - faktiskStart.getTime();
        Integer diffSekunder = Math.toIntExact(diffMillisekunder/1000);
        Integer minuttNummer = diffSekunder/60;
        System.out.println("minuttnummer: " + minuttNummer);
        
        Integer formatertTBM =  minuttNummer*3 + tilbakemelding;
        
        return formatertTBM;
    }
}
