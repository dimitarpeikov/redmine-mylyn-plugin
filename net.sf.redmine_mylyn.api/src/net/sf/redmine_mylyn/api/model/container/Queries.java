package net.sf.redmine_mylyn.api.model.container;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import net.sf.redmine_mylyn.api.model.Query;

@XmlRootElement(name="queries")
@XmlAccessorType(XmlAccessType.NONE)
public class Queries extends AbstractPropertyContainer<Query> {

	private static final long serialVersionUID = 1L;

	protected List<Query> queries;
	
	@Override
	@XmlElement(name="query")
	protected List<Query> getModifiableList() {
		if(queries==null) {
			queries = new ArrayList<Query>();
		}
		return queries;
	}
	
}
