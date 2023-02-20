package com.alkimi.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.alkimi.entities.Auction;
import com.alkimi.entities.AuctionPrizeDetails;


@Repository
public interface AuctionPrizeDetailsRepository extends JpaRepository<AuctionPrizeDetails, Integer> {

	List<AuctionPrizeDetails> findByAuctionIdAuctionId(int auctionId);
	List<AuctionPrizeDetails> findByAuctionId(Auction auctionId);
	Optional<AuctionPrizeDetails> findByAuctionIdAndPrizePositionAndPrizeIndex(Auction auctionId,int prizePosition,int prizeIndex);
	
	@Query("select t.ticketId from AuctionPrizeDetails t where t.auctionId = ?1")
    Optional<List<Integer>> getTicketNumbersByAuctionId(Auction auctionId);
}
