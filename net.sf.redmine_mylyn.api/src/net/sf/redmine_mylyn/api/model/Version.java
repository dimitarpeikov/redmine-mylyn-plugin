package net.sf.redmine_mylyn.api.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="version")
public class Version extends Property {

	private static final long serialVersionUID = 1L;

	public enum Status {
		@XmlEnumValue("closed")
		CLOSED, 
		@XmlEnumValue("locked")
		LOCKED, 
		@XmlEnumValue("open")
		OPEN
	};
	
	private Status status;

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
}
