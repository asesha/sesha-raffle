package com.alkimi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alkimi.entities.Auction;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, Integer> {

	
}
