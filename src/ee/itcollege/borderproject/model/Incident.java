package ee.itcollege.borderproject.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;


@NamedQueries({ 
	@NamedQuery(name = "Incident.findAll", 
				query = "SELECT f FROM Incident f")  })
@Entity
public class Incident implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotNull
	@Size(min = 2, max = 40)
	private String location;
	
	@NotNull
	@Size(min = 2, max = 200)
	private String description;
	
	@Min(value = 1)
	private int involvedGuardCount;
	
	@NotNull
	@Size(min = 2, max = 40)
	private String status;
	
	@NotNull
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private Date start;
	
	@NotNull
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private Date ending;

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getEnding() {
		return ending;
	}

	public void setEnding(Date end) {
		this.ending = end;
	}

}
