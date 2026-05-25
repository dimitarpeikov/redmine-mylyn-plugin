package net.sf.redmine_mylyn.api.model.container;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import net.sf.redmine_mylyn.api.model.IssuePriority;

@XmlRootElement(name="issuePriorities")
@XmlAccessorType(XmlAccessType.NONE)
public class IssuePriorities extends AbstractSortedPropertyContainer<IssuePriority> {

	private static final long serialVersionUID = 1L;

	protected List<IssuePriority> issuePriorities;
	
	protected IssuePriority defaultPriority;
	
	@Override
	@XmlElement(name="issuePriority")
	protected List<IssuePriority> getModifiableList() {
		if(issuePriorities==null) {
			issuePriorities = new ArrayList<IssuePriority>() {

				private static final long serialVersionUID = -690772074652255323L;

				@Override
				public boolean add(IssuePriority e) {
					if(defaultPriority==null || e.isDefault()) {
						defaultPriority = e;
					}
					return super.add(e);
				}
			};
		}
		return issuePriorities;
	}

	public IssuePriority getDefault() {
		return defaultPriority;
	}
}
