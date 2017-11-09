package no.hvl.dat104.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import no.hvl.dat104.model.Event;

public class EventUtil {
	
	public static List<Event> sorterEventer(List<Event> eventListe){
		Collections.sort(eventListe, new Comparator<Event>() {
			public int compare(Event e1, Event e2) {
				return e1.getTidFra().compareTo(e2.getTidFra());
			}
		});
		
		return eventListe;
		
	}

}
