package net.sf.redmine_mylyn.api.model.container;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

import net.sf.redmine_mylyn.api.model.Journal;

@XmlType(name="journals")
@XmlAccessorType(XmlAccessType.NONE)
public class Journals extends AbstractTypedContainer<Journal> {

	private List<Journal> journals;
	
	@Override
	@XmlElement(name="journal")
	protected List<Journal> getModifiableList() {
		if(journals==null) {
			journals = new ArrayList<Journal>();
		}
		return journals;
	}

}
