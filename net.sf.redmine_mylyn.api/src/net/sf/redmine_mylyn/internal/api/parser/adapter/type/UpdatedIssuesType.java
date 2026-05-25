package net.sf.redmine_mylyn.internal.api.parser.adapter.type;

import jakarta.xml.bind.annotation.XmlList;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlValue;

@XmlRootElement(name="updatedIssues")
public class UpdatedIssuesType {
	
	@XmlList
	@XmlValue
	public int[] updatedIssueIds;

}
