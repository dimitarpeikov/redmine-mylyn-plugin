package net.sf.redmine_mylyn.api.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="customValue")
public class CustomValue implements IModel {
	
	private static final long serialVersionUID = 1L;

	@XmlAttribute
	private int id;
	
	@XmlAttribute
	private int customFieldId;
	
	@XmlValue
	private String value;

	public int getCustomFieldId() {
		return customFieldId;
	}

	public void setCustomFieldId(int customFieldId) {
		this.customFieldId = customFieldId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getId() {
		return id;
	}
	
}
