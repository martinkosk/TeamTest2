package ee.itcollege.borderproject.model;

import java.io.Serializable;

public class BorderStation implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name;
	private String address;
	private int guardCount;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public int getGuardCount() {
		return guardCount;
	}
	
	public void setGuardCount(int guardCount) {
		this.guardCount = guardCount;
	}	
}
