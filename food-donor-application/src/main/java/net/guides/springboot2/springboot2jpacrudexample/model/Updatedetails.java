package net.guides.springboot2.springboot2jpacrudexample.model;

public class Updatedetails {
	private Integer id;
	private String status;

	@Override
	public String toString() {
		return "Updatedetails [id=" + id + ", status=" + status + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
