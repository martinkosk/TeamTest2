package ee.itcollege.borderproject.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@NamedQueries({ 
	@NamedQuery(name = "Guard.findAll", 
				query = "SELECT g FROM Guard g"), 
	@NamedQuery(name = "Guard.findByName", 
				query = "SELECT g FROM Guard g WHERE g.name = :name"), 
	@NamedQuery(name = "Guard.findByAge", 
				query = "SELECT g FROM Guard g WHERE g.age = :age"), 
	@NamedQuery(name = "Guard.findByNameAndAge", 
				query = "SELECT g FROM Guard g WHERE g.name = :name AND g.age = :age") })
@Entity
public class Guard implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Min(value = 2)
	@Max(value = 20)
	private String name;
	private int age;
	private static final long serialVersionUID = 1L;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
}
