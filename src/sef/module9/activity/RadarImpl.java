package sef.module9.activity;

import java.util.*;

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
		if (id != null) {
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
		 if (id != null) {
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
		radarContacts.sort(comparator);
		return new ArrayList<>(radarContacts);
	}

	
}
