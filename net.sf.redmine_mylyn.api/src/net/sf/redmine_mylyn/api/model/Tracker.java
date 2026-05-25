package net.sf.redmine_mylyn.api.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlList;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="tracker")
public class Tracker extends SortedProperty {

	private static final long serialVersionUID = 1L;
	
	@XmlList
	@XmlElement(name="issueCustomFields")
	private int []issueCustomFields;

	public int[] getIssueCustomFields() {
		if(issueCustomFields==null) {
			issueCustomFields = new int[0];
		}
		return issueCustomFields;
	}
	
	

}
