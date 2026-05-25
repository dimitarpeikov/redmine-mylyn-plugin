package net.sf.redmine_mylyn.api.model.container;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import net.sf.redmine_mylyn.api.model.Project;

@XmlRootElement(name="projects")
@XmlAccessorType(XmlAccessType.NONE)
public class Projects extends AbstractPropertyContainer<Project> {

	private static final long serialVersionUID = 1L;

	protected List<Project> projects;
	
	protected List<Project> newAllowed = new ArrayList<Project>();

	protected List<Project> moveAllowed = new ArrayList<Project>();
	
	@Override
	@XmlElement(name="project")
	protected List<Project> getModifiableList() {
		if(projects==null) {
			projects = new ArrayList<Project>() {

				private static final long serialVersionUID = 1L;
				
				public boolean add(Project e) {
					boolean result = super.add(e);
					if(result && e.isNewIssueAllowed()) {
						newAllowed.add(e);
					}
					if(result && e.isMoveIssueAllowed()) {
						moveAllowed.add(e);
					}
					return result;
				};
				
			};
		}
		return projects;
	}
	
	public List<Project> getNewAllowed() {
		return Collections.unmodifiableList(newAllowed);
	}

	public List<Project> getMoveAllowed() {
		return Collections.unmodifiableList(moveAllowed);
	}

	public List<Project> getMoveAllowed(Project project) {
		if(moveAllowed.contains(project)) {
			return getMoveAllowed();
		}
		
		List<Project> lst = new ArrayList<Project>(moveAllowed);
		lst.add(0, project);
		return Collections.unmodifiableList(lst);
	}
	
}
