package net.sf.redmine_mylyn.api.model;

import java.io.Serializable;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="settings")
public class Settings implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private boolean useIssueDoneRatio = true;
	
	private int maxPerPage = 25;

	public boolean isUseIssueDoneRatio() {
		return useIssueDoneRatio;
	}

	public int getMaxPerPage() {
		return maxPerPage;
	}
	
	
	
}
