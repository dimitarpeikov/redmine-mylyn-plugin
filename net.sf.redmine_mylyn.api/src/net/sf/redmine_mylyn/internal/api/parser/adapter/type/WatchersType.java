package net.sf.redmine_mylyn.internal.api.parser.adapter.type;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlList;
import jakarta.xml.bind.annotation.XmlValue;

public class WatchersType {

	@XmlAttribute
	public boolean viewAllowed;

	@XmlAttribute
	public boolean addAllowed;
	
	@XmlAttribute
	public boolean deleteAllowed;

	@XmlValue
	@XmlList
	public int[] watchers;

}
