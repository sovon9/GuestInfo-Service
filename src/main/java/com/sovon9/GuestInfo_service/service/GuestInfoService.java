package com.sovon9.GuestInfo_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sovon9.GuestInfo_service.dto.GuestInfoSearchCriteria;
import com.sovon9.GuestInfo_service.model.Guest;
import com.sovon9.GuestInfo_service.repository.GuestRepository;

import jakarta.transaction.Transactional;

/**
 * 
 * @author Sovon Singha
 *
 */
@Service
public class GuestInfoService {

	@Autowired
	private GuestRepository guestRepository;
	
	@Transactional
	public Guest saveGuestInfoData(Guest resVO)
	{
		return guestRepository.save(resVO);
	}
	
	public Optional<Guest> fetchGuestInfoData(Long guestID)
	{
		return guestRepository.findById(guestID);
	}

	public List<Guest> seatchGuestInfoDataBasedOnCriteria(GuestInfoSearchCriteria searchCriteria)
	{
		return guestRepository.seatchGuestInfoDataBasedOnCriteria(searchCriteria);
	}
	
}
