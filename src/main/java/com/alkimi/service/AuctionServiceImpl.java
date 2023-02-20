package com.alkimi.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkimi.dao.AuctionPrizeDetailsRepository;
import com.alkimi.dao.AuctionRepository;
import com.alkimi.dao.AuctionTicketRepository;
import com.alkimi.entities.Auction;
import com.alkimi.entities.AuctionPrizeDetails;
import com.alkimi.entities.AuctionTicket;
import com.alkimi.entities.Product;
import com.alkimi.entities.User;
import com.alkimi.exceptions.ClientErrorException;
import com.alkimi.vo.AuctionPrizeDetailsVO;
import com.alkimi.vo.AuctionVO;

@Service
public class AuctionServiceImpl implements AuctionService{

	@Autowired
	AuctionRepository repository;
	
	@Autowired
	AuctionTicketRepository auctionTicketRepository;
	
	@Autowired
	AuctionPrizeDetailsRepository auctionPrizeDetailsRepository;
	
	@Override
	public List<AuctionVO> getAllAuction() {
		List<Auction> auctions = repository.findAll();
    	List<AuctionVO> auctionVOList = auctions.stream().map(AuctionVO::new).collect(Collectors.toList());
    	return auctionVOList;
	}

	@Override
	public AuctionVO createAuction(AuctionVO vo) {
		Auction auction = repository.save(vo.getAuction());
		
		List<AuctionPrizeDetailsVO> prizeDetails =  vo.getPrizes();
		List<AuctionPrizeDetails> createPrizeDetails =  prizeDetails.stream().map( e -> {
			AuctionPrizeDetails prizeDetail = new AuctionPrizeDetails();
			prizeDetail.setAuctionId(auction);
			prizeDetail.setProductId(new Product(e.getProductId().getProductId()));
			prizeDetail.setPrizePosition(e.getPrizePosition());
			prizeDetail.setPrizeIndex(e.getPrizeIndex());
			prizeDetail.setCreatedOn(LocalDate.now());
			return prizeDetail;
		}).collect(Collectors.toList());
		
		createPrizeDetails = auctionPrizeDetailsRepository.saveAll(createPrizeDetails);
		
		prizeDetails = createPrizeDetails.stream().map(AuctionPrizeDetailsVO::new).collect(Collectors.toList());
		
		AuctionVO response = auction.getAuctionVO();
		response.setPrizes(prizeDetails);
		
		return response;
	}

	@Override
	public AuctionVO getAuctionById(int auctionId) {
		Optional<Auction> auction = repository.findById(auctionId);
		return auction.get().getAuctionVO();
	}

	@Override
	public AuctionVO updateAuction(int auctionId,AuctionVO auctionDetails) {
		Optional<Auction> auction = repository.findById(auctionId);
		Auction modifiedAuction = null;
		if(auction.get() != null) {
			modifiedAuction = auction.get();
			updateAuction(modifiedAuction,auctionDetails);
			modifiedAuction.setUpdatedOn(LocalDate.now());
	    	modifiedAuction = repository.save(modifiedAuction);
	    }
		return modifiedAuction.getAuctionVO();
	}

	
	@Override
	public AuctionVO deleteAuction(int auctionId) {
		Optional<Auction> auction = repository.findById(auctionId);
		if(auction.get() != null) {
			repository.delete(auction.get());
	    }
		return auction.get().getAuctionVO();
	}
	
	private void updateAuction(Auction modifiedAuction, AuctionVO auctionDetails ) {
		updateAuctionName(modifiedAuction,auctionDetails);
		updatePrizeCount(modifiedAuction,auctionDetails);
		updateAuctionStartDetails(modifiedAuction,auctionDetails);
		updateAuctionEndDetails(modifiedAuction,auctionDetails);
		updateAuctionDrawingDetails(modifiedAuction,auctionDetails);
		updateMixMaxTickets(modifiedAuction,auctionDetails);
	}

	private void updatePrizeCount(Auction modifiedAuction, AuctionVO auctionDetails) {
		if(auctionDetails.getPrizeCount()!=0) {
			modifiedAuction.setPrizeCount(auctionDetails.getPrizeCount());
		}
	}

	private void updateMixMaxTickets(Auction modifiedAuction, AuctionVO auctionDetails) {
		if(auctionDetails.getMinTickets()!=0) {
			modifiedAuction.setMinTickets(auctionDetails.getMinTickets());
		}
		
		if(auctionDetails.getMaxTickets()!=0) {
			modifiedAuction.setMaxTickets(auctionDetails.getMaxTickets());
		}
		
	}

	private void updateAuctionDrawingDetails(Auction modifiedAuction, AuctionVO auctionDetails) {
		
		if(auctionDetails.getDrawingDate()!=null) {
			modifiedAuction.setDrawingDate(auctionDetails.getDrawingDate());
		}
		
		if(auctionDetails.getDrawingTime()!=null) {
			modifiedAuction.setDrawingTime(auctionDetails.getDrawingTime());
		}
	}

	private void updateAuctionEndDetails(Auction modifiedAuction, AuctionVO auctionDetails) {
		
		if(auctionDetails.getEndDate()!=null) {
			modifiedAuction.setEndDate(auctionDetails.getEndDate());
		}
		
		if(auctionDetails.getEndTime()!=null) {
			modifiedAuction.setEndTime(auctionDetails.getEndTime());
		}
	}

	private void updateAuctionStartDetails(Auction modifiedAuction, AuctionVO auctionDetails) {
		
		if(auctionDetails.getStartDate()!=null) {
			modifiedAuction.setStartDate(auctionDetails.getStartDate());
		}
		
		if(auctionDetails.getEndTime()!=null) {
			modifiedAuction.setEndTime(auctionDetails.getEndTime());
		}
		
	}

	private void updateAuctionName(Auction modifiedAuction, AuctionVO auctionDetails) {
		if(auctionDetails.getAuctionName()!=null) {
			modifiedAuction.setAuctionName(auctionDetails.getAuctionName());
		}
	}

	@Override
	public AuctionPrizeDetailsVO createLuckyDrawWinner(int auctionId, int prizePositionId,int prizeIndexId) {
		System.out.println("Auction ID is " + auctionId  + " prizePositionId" + prizePositionId);
		Optional<Auction> auction = repository.findById(auctionId);
		int maxTickets = 0;
		if(auction.get()!=null) {
			maxTickets = auction.get().getMaxTickets();
		}
		System.out.println("Number of availability Tickets " + maxTickets);
		
		int ticketId = 0;
		Random random = new Random();
		List<Integer> luckyTickets = new ArrayList<>();
		Optional<List<Integer>> optionalLuckyTickets = auctionPrizeDetailsRepository.getTicketNumbersByAuctionId(auction.get());
		if(optionalLuckyTickets.get()!=null) {
			luckyTickets = optionalLuckyTickets.get();
		}
		
		do {
			ticketId = random.nextInt((maxTickets - 1) + 1) + 1;
			System.out.println("Generated Random Ticket is " + ticketId);
		} while(luckyTickets.contains(ticketId));
		
		
		Optional<AuctionTicket> auctionTicket = auctionTicketRepository.findByAuctionIdAuctionIdAndTicketId(auctionId,ticketId);
		
		User winnerId = null;
		if(auctionTicket.get()!=null) {
			winnerId = auctionTicket.get().getUserId();
			System.out.println("Winner is " + winnerId);
		}
		
		AuctionPrizeDetailsVO winnerAuction = null;
		//List<AuctionPrizeDetails> prizeDetails = auctionPrizeDetailsRepository.findByAuctionIdAuctionIdAndPrizeType(auctionId, prizePositionId);
		Optional<AuctionPrizeDetails> prizeDetail = auctionPrizeDetailsRepository.findByAuctionIdAndPrizePositionAndPrizeIndex(auction.get(),prizePositionId,prizeIndexId);
		
		AuctionPrizeDetails detail;
		if(prizeDetail.get()!=null) {
			detail = prizeDetail.get();
			if(detail.getWinnerId()!=null) {
				throw new ClientErrorException(String.format("Lucky draw is already done for this auction Id %d prize %d", auctionId, prizePositionId));
			}
			detail.setWinnerId(winnerId);
			winnerAuction = new AuctionPrizeDetailsVO(detail,winnerId);
			detail.setTicketId(ticketId);
			auctionPrizeDetailsRepository.save(detail);
		}
		winnerAuction.setWinnerTicketId(ticketId);
		
		return winnerAuction;
	}
	
	
}
