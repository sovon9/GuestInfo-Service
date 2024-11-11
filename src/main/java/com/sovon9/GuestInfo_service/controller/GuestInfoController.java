package com.sovon9.GuestInfo_service.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sovon9.GuestInfo_service.Exception.RRMSException;
import com.sovon9.GuestInfo_service.dto.GuestCommInfo;
import com.sovon9.GuestInfo_service.dto.GuestInfoSearchCriteria;
import com.sovon9.GuestInfo_service.model.Guest;
import com.sovon9.GuestInfo_service.service.GuestInfoService;

@RestController
@RequestMapping("/guestinfo-service")
public class GuestInfoController {

	@Autowired
	private GuestInfoService service;
	
	@PostMapping("/guestinfo")
	public Guest saveGuestInfo(@RequestBody Guest guest)
	{
		return service.saveGuestInfoData(guest);
	}
	
	@GetMapping("/guestinfo/{guestID}")
	public Guest getGuestInfoByID(@PathVariable Long guestID)
	{
		Optional<Guest> fetchGuestInfoData = service.fetchGuestInfoData(guestID);
		if(fetchGuestInfoData.isEmpty())
		{
			throw new RRMSException("No Guest Found");
		}
		return fetchGuestInfoData.get();
	}
	
	@GetMapping("/guestinfo")
	public List<Guest> searchGuestInfo(@RequestParam(required = false) Long guestID, @RequestParam(required = false) String firstName,
			@RequestParam(required = false) String lastName, @RequestParam(required = false) LocalDate birthDate, @RequestParam(required = false) String phno)
	{
		GuestInfoSearchCriteria searchCriteria = new GuestInfoSearchCriteria(guestID, firstName, lastName, birthDate, phno);
		return service.seatchGuestInfoDataBasedOnCriteria(searchCriteria);
	}
	
	@GetMapping("/guestinfo/guestCommInfo/{guestID}")
	public GuestCommInfo getGuestEmailCommInfo(@PathVariable("guestID") Long guestID)
	{
		GuestCommInfo commInfo = new GuestCommInfo();
		Optional<Guest> fetchGuestInfoData = service.fetchGuestInfoData(guestID);
		
		// if guest data is found populate GuestCommInfo
		Guest guest = fetchGuestInfoData.orElse(null);
		if(null!=guest)
		{
			commInfo.setFirstName(guest.getFirstName());
			commInfo.setEmail(guest.getEmail());
			commInfo.setPhno(guest.getPhno());
		}
		return commInfo;
	}
	
}
