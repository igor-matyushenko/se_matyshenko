package sef.module9.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Implementation of a Radar 
 * 
 *
 */
public class RadarImpl implements Radar{

	private List<RadarContact> radarContacts;
	
	/**
	 *  Constructs a new Radar 
	 */
	public RadarImpl(){
		radarContacts = new ArrayList<RadarContact>();
	}
	
	
	/* (non-Javadoc)
	 * @see sef.module8.activity.Radar#addContact(sef.module8.activity.RadarContact)
	 */
	public RadarContact addContact(RadarContact contact) {
		if(radarContacts.contains(contact)){
			int indexContact = radarContacts.indexOf(contact);
			radarContacts.set(indexContact,contact);
		} else {
			radarContacts.add(contact);
		}
		return contact;
	}

	/* (non-Javadoc)
	 * @see sef.module8.activity.Radar#getContact(java.lang.String)
	 */
	public RadarContact getContact(String id) {
		if (!"null".equals(id)) {
			for (RadarContact rd: radarContacts ) {
				if(id.equals(rd.getContactID())){
					return rd;
				}
			}
		} return null;
	}

	/* (non-Javadoc)
	 * @see sef.module8.activity.Radar#getContactCount()
	 */
	public int getContactCount() {
		int count = 0;
		for (RadarContact rd: radarContacts) {
			count++;
		}
		return count;
	}

	/* (non-Javadoc)
	 * @see sef.module8.activity.Radar#removeContact(java.lang.String)
	 */
	public RadarContact removeContact(String id) {
		if (!"null".equals(id)) {
			for (RadarContact rd: radarContacts ) {
				if(id.equals(rd.getContactID())){
					radarContacts.remove(rd);
					return rd;
				}
			}
		} return null;
	}

	/* (non-Javadoc)
	 * @see sef.module8.activity.Radar#returnContacts()
	 */
	public List<RadarContact> returnContacts() {

		return new ArrayList<>(radarContacts);
	}

	/* (non-Javadoc)
	 * @see sef.module8.activity.Radar#returnContacts(java.util.Comparator)
	 */
	public List<RadarContact> returnContacts(Comparator<RadarContact> comparator) {
		List<RadarContact> sortRadarContact = new ArrayList<>(radarContacts);
		comparator = (r1, r2) -> (int) (r1.getDistance() - r2.getDistance());
		Collections.sort(sortRadarContact, comparator);
		return sortRadarContact;
	}

	
}
