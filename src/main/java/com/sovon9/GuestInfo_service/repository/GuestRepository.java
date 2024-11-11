package com.sovon9.GuestInfo_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sovon9.GuestInfo_service.model.Guest;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long>,GuestRepositoryCustom{

}
