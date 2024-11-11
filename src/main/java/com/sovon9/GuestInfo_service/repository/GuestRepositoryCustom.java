package com.sovon9.GuestInfo_service.repository;

import java.util.List;

import com.sovon9.GuestInfo_service.dto.GuestInfoSearchCriteria;
import com.sovon9.GuestInfo_service.model.Guest;

public interface GuestRepositoryCustom
{
	public List<Guest> seatchGuestInfoDataBasedOnCriteria(GuestInfoSearchCriteria criteria);
}
