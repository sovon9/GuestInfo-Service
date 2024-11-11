package com.sovon9.GuestInfo_service.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;
	@Column
	private String address1;
	@Column
	private String address2;
	@Column
	private String postalcode;
	@Column
	private String city;
	@Column
	private String state;
	@OneToOne(mappedBy = "address", cascade = CascadeType.ALL)
	private Guest guest;
	
	public Address(String address1, String address2, String postalcode, String city, String state) {
		super();
		this.address1 = address1;
		this.address2 = address2;
		this.postalcode = postalcode;
		this.city = city;
		this.state = state;
	}
	public Address() {
		super();
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getPostalcode() {
		return postalcode;
	}
	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Address [address1=" + address1 + ", address2=" + address2 + ", postalcode=" + postalcode + ", city="
				+ city + ", state=" + state + "]";
	}
}
