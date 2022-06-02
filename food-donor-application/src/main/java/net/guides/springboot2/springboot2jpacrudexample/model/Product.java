package net.guides.springboot2.springboot2jpacrudexample.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "productDetails", nullable = false)
	private String productDetails;

	@Column(name = "phNumber", nullable = false)
	private String phNumber;

	@Column(name = "address", nullable = false)
	private String address;

	@Column(name = "emailId", nullable = false)
	private String emailId;

	@Column(name = "status", nullable = false)
	private String status;

	@Override
	public String toString() {
		return "Employee [id=" + id + ", phNumber=" + phNumber + ", address=" + address + "," + " emailId=" + emailId
				+ ", status=" + status + ", productDetails=" + productDetails + "]";
	}

	public Product() {
		super();

	}

	public Product(String name, String productDetails, String phNumber, String address, String emailId, String status) {
		super();
		this.name = name;
		this.productDetails = productDetails;
		this.phNumber = phNumber;
		this.address = address;
		this.emailId = emailId;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(String productDetails) {
		this.productDetails = productDetails;
	}

	public String getPhNumber() {
		return phNumber;
	}

	public void setPhNumber(String phNumber) {
		this.phNumber = phNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
