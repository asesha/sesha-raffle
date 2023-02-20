package com.alkimi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alkimi.entities.UserAuction;

@Repository
public interface UserAuctionRepository extends JpaRepository<UserAuction, Integer> {

	
}
