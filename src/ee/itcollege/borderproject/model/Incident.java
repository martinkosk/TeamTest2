package ee.itcollege.borderproject.model;

import java.io.Serializable;
import java.util.Date;

public class Incident implements Serializable {

	private static final long serialVersionUID = 1L;

	private String location;
	private String description;
	private Integer involvedGuardCount;
	private String status;
	private Date start;
	private Date end;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getInvolvedGuardCount() {
		return involvedGuardCount;
	}

	public void setInvolvedGuardCount(Integer involvedGuardCount) {
		this.involvedGuardCount = involvedGuardCount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

}
