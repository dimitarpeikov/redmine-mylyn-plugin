package net.sf.redmine_mylyn.internal.api.parser.adapter.type;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="errors")
@XmlAccessorType(XmlAccessType.NONE)
public class SubmitError {

	@XmlElement(name="error")
	public List<String> errors = new ArrayList<String>();

}
