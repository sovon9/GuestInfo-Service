package com.sovon9.GuestInfo_service.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sovon9.GuestInfo_service.dto.GuestInfoSearchCriteria;
import com.sovon9.GuestInfo_service.model.Guest;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class GuestRepositoryCustomImpl implements GuestRepositoryCustom
{
	@Autowired
	EntityManager entityManager;
	
	@Override
	public List<Guest> seatchGuestInfoDataBasedOnCriteria(GuestInfoSearchCriteria criteria)
	{
		CriteriaBuilder cb = entityManager.getCriteriaBuilder(); // craete criteria builder object
		CriteriaQuery<Guest> criteriaQuery = cb.createQuery(Guest.class);
		
		// select * from guest
		Root<Guest> root = criteriaQuery.from(Guest.class);
		//
		List<Predicate> predicates = new ArrayList<>();
		// guestID = ?
		if(null!=criteria.getGuestID())
		{
			Predicate guestID = cb.equal(root.get("guestID"), criteria.getGuestID());
			predicates.add(guestID);
		}
		if(null!=criteria.getFirstName() && !criteria.getFirstName().isEmpty())
		{
			Predicate firstName = cb.equal(root.get("firstName"), criteria.getFirstName());
			predicates.add(firstName);
		}
		if(null!=criteria.getLastName() && !criteria.getLastName().isEmpty())
		{
			Predicate lastName = cb.equal(root.get("lastName"), criteria.getLastName());
			predicates.add(lastName);
		}
		if(null!=criteria.getBirthDate())
		{
			Predicate birthDate = cb.equal(root.get("birthDate"), criteria.getBirthDate());
			predicates.add(birthDate);
		}
		if(null!=criteria.getPhno() && !criteria.getPhno().isEmpty())
		{
			Predicate phno = cb.equal(root.get("phno"), criteria.getPhno());
			predicates.add(phno);
		}
		//add where to query and add conditions in and clause
		criteriaQuery.where(predicates.toArray(Predicate[]::new));
		TypedQuery<Guest> typedQuery = entityManager.createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}

}
