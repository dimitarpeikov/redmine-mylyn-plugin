package net.sf.redmine_mylyn.api.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="issueStatus")
public class IssueStatus extends SortedProperty {
	
	private static final long serialVersionUID = 1L;

	protected boolean isClosed;

	protected boolean isDefault;

	public boolean isClosed() {
		return isClosed;
	}

	public void setClosed(boolean closed) {
		this.isClosed = closed;
	}

	public boolean isDefault() {
		return isDefault;
	}

	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}

	
}
