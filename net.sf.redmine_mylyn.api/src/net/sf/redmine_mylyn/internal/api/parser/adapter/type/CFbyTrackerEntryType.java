package net.sf.redmine_mylyn.internal.api.parser.adapter.type;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlValue;

public class CFbyTrackerEntryType {
	
	@XmlAttribute
	public int trackerId;
	
	@XmlValue
	public int[] idList;

}
