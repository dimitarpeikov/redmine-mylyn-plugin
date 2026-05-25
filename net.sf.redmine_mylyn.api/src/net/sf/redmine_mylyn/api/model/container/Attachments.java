package net.sf.redmine_mylyn.api.model.container;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

import net.sf.redmine_mylyn.api.model.Attachment;

@XmlType(name="attachments")
@XmlAccessorType(XmlAccessType.NONE)
public class Attachments extends AbstractTypedContainer<Attachment> {

	private List<Attachment> attachments;
	
	@Override
	@XmlElement(name="attachment")
	protected List<Attachment> getModifiableList() {
		if(attachments==null) {
			attachments = new ArrayList<Attachment>();
		}
		return attachments;
	}

}
