package com.alkimi.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alkimi.entities.AuctionTicket;

@Repository
public interface AuctionTicketRepository extends JpaRepository<AuctionTicket, Integer> {

	List<AuctionTicket> findByAuctionIdAuctionId(int auctionId);
	Optional<AuctionTicket> findByAuctionIdAuctionIdAndTicketId(int auctionId, int ticketId);
}
