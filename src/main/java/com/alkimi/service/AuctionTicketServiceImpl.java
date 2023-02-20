package com.alkimi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.alkimi.dao.AuctionRepository;
import com.alkimi.dao.AuctionTicketRepository;
import com.alkimi.entities.Auction;
import com.alkimi.entities.AuctionTicket;
import com.alkimi.exceptions.ClientErrorException;
import com.alkimi.vo.AuctionTicketVO;
import com.alkimi.vo.TicketVO;

@Service
public class AuctionTicketServiceImpl implements AuctionTicketService{

	@Autowired
	AuctionTicketRepository repository;
	
	@Autowired
	AuctionRepository auctionRepository;

	@Override
	public List<AuctionTicketVO> createAuctionTicket(AuctionTicketVO vo) {
		System.out.println(vo);
		Optional<Auction> auction = auctionRepository.findById(vo.getAuctionId().getAuctionId());
		int maxTickets = 0;
		if(auction.get()!=null) {
			maxTickets = auction.get().getMaxTickets();
		}
		List<AuctionTicket> auctionTickets = new ArrayList<>();
		try {
			
			for(TicketVO ticket: vo.getTickets()) {
				AuctionTicket auctionTicket = vo.getAuctionTicket();
				if( ticket.getTicketId() > maxTickets ) {
					throw new ClientErrorException("TicketId is greater than maximum ticket Id");
				}
				auctionTicket.setTicketId(ticket.getTicketId());
				auctionTickets.add(auctionTicket);
			}
			System.out.println(auctionTickets);
			auctionTickets = repository.saveAll(auctionTickets);
			System.out.println(auctionTickets);
		} catch(DataIntegrityViolationException ex) {
			System.out.println("===========================================");
			System.out.println(ex.getMessage());
			System.out.println(ex.getRootCause().getMessage());
			String rootCause = ex.getRootCause().getMessage();
			if(rootCause.contains("USER_ID_FK") || rootCause.contains("user_id_fk")) {
				throw new ClientErrorException(String.format("UserId %d does not exist", vo.getUserId().getUserId()));
			} else if(rootCause.contains("Duplicate")) {
				throw new ClientErrorException(String.format("Ticket already sold for auction %d", vo.getAuctionId().getAuctionId()));
			} else {
				throw new ClientErrorException(ex.getMessage());
			}

			
		}
		
		List<AuctionTicketVO> auctionTicketsVO = new ArrayList<>();
		auctionTicketsVO = auctionTickets.stream().map(AuctionTicketVO::new).collect(Collectors.toList());
		return auctionTicketsVO;
	}

	@Override
	public List<String> getSoldTicketListByActionId(int auctionId) {
		List<AuctionTicket> auctionTickets = repository.findByAuctionIdAuctionId(auctionId);
		System.out.println("Sise is " + auctionTickets.size());
		List<String> ticketList = auctionTickets.stream().map( e -> String.valueOf(e.getTicketId())).collect(Collectors.toList());
		return ticketList;
	}
	
	public List<String> getUnSoldTicketListByActionId(int auctionId){
		Optional<Auction> auction = auctionRepository.findById(auctionId);
		int maxTickets = 0;
		if(auction.get()!=null) {
			maxTickets = auction.get().getMaxTickets();
		}
		
		List<Integer> allIntTcikets = IntStream.range(1, maxTickets + 1)
                .boxed()
                .collect(Collectors.toList());
		
		List<String> allTickets = allIntTcikets.stream().map(i -> i.toString()).collect(Collectors.toList());
		
		List<AuctionTicket> soldAuctionTickets = repository.findByAuctionIdAuctionId(auctionId);
		List<String> soldTickets = soldAuctionTickets.stream().map( e -> String.valueOf(e.getTicketId())).collect(Collectors.toList());
		
		allTickets.removeAll(soldTickets);
		
		return allTickets;
	}



	
	
}
