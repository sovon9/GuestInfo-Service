package com.sovon9.GuestInfo_service.dto;

import java.time.LocalDate;

public class GuestInfoSearchCriteria
{
	private Long guestID;
	private String firstName;
	private String lastName;
	private LocalDate birthDate;
	private String phno;
	public GuestInfoSearchCriteria()
	{
		super();
	}
	public GuestInfoSearchCriteria(Long guestID, String firstName, String lastName, LocalDate birthDate, String phno)
	{
		super();
		this.guestID = guestID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.phno = phno;
	}
	public Long getGuestID()
	{
		return guestID;
	}
	public void setGuestID(Long guestID)
	{
		this.guestID = guestID;
	}
	public String getFirstName()
	{
		return firstName;
	}
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	public String getLastName()
	{
		return lastName;
	}
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	public LocalDate getBirthDate()
	{
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate)
	{
		this.birthDate = birthDate;
	}
	public String getPhno()
	{
		return phno;
	}
	public void setPhno(String phno)
	{
		this.phno = phno;
	}
	
}
