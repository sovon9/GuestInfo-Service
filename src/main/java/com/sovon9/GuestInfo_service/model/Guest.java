package com.sovon9.GuestInfo_service.model;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="guest")
public class Guest {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ghID")
	private Long guestID;
	@Column
	private String firstName;
	@Column
	private String lastName;
	@Column
	private LocalDate birthDate;
	@Column
	private LocalDate lastStay;// can be created as a seperate class to store last stay details
	@Column
	private String phno;
	@Column
	private String email;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="address_id")
	private Address address;
	@Column
	private LocalDate createDate;
	@Column
	private LocalDate purgeDate;
	public Guest() {
		super();
	}
	public Long getGuestID() {
		return guestID;
	}
	public void setGuestID(Long guestID) {
		this.guestID = guestID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getPhno() {
		return phno;
	}
	public void setPhno(String phno) {
		this.phno = phno;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public LocalDate getBirthDate()
	{
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate)
	{
		this.birthDate = birthDate;
	}
	public LocalDate getLastStay()
	{
		return lastStay;
	}
	public void setLastStay(LocalDate lastStay)
	{
		this.lastStay = lastStay;
	}
	public LocalDate getCreateDate()
	{
		return createDate;
	}
	public void setCreateDate(LocalDate createDate)
	{
		this.createDate = createDate;
	}
	public LocalDate getPurgeDate()
	{
		return purgeDate;
	}
	public void setPurgeDate(LocalDate purgeDate)
	{
		this.purgeDate = purgeDate;
	}
	
}
