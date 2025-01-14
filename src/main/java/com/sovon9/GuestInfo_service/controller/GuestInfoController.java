package com.sovon9.GuestInfo_service.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.KafkaException;
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
import com.sovon9.GuestInfo_service.service.KafkaProducerService;

@RestController
@RequestMapping("/guestinfo-service")
public class GuestInfoController {

	Logger LOGGER = LoggerFactory.getLogger(GuestInfoController.class);
	@Autowired
	private GuestInfoService service;
	@Autowired
	private KafkaProducerService producerService;
	
	@PostMapping("/guestinfo")
	public ResponseEntity<Guest> saveGuestInfo(@RequestBody Guest guest)
	{
		ResponseEntity<Guest> saveGuestInfoData = null;
		try
		{
			saveGuestInfoData = ResponseEntity.status(HttpStatus.CREATED).body(service.saveGuestInfoData(guest));
			GuestCommInfo guestCommInfo = getGuestEmailCommInfo(guest.getGuestID());
			if (null != guestCommInfo && null!=guestCommInfo.getEmail())
			{
				guestCommInfo.setAction("GUESTINFO");
				producerService.produce(guestCommInfo);
			}
		}
		catch(KafkaException e)
		{
			LOGGER.error("Kafka exception: {}", e.getMessage(), e);
		}
		catch(Exception e)
		{
			saveGuestInfoData = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return saveGuestInfoData;
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
		GuestCommInfo contactInfo = new GuestCommInfo();
		Optional<Guest> fetchGuestInfoData = service.fetchGuestInfoData(guestID);
		
		// if guest data is found populate GuestCommInfo
		Guest guest = fetchGuestInfoData.orElse(null);
		if(null!=guest)
		{
			contactInfo.setGuestID(guestID);
			contactInfo.setFirstName(guest.getFirstName());
			contactInfo.setEmail(guest.getEmail());
			contactInfo.setPhno(guest.getPhno());
		}
		return contactInfo;
	}
	
}
