package com.alkimi.vo;

import java.time.LocalDate;
import java.util.List;

import com.alkimi.entities.Auction;
import com.alkimi.entities.AuctionTicket;
import com.alkimi.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class AuctionTicketVO {

	private int auctionTicketId;
	
	private UserRefVO userId;
	
	private AuctionRefVO auctionId;
	
	private LocalDate createdOn;

	private List<TicketVO> tickets;
	
	private int ticketId;
	
	public AuctionTicketVO(AuctionTicket auctionTicket) {
		this.auctionTicketId = auctionTicket.getAuctionTicketId();
		AuctionRefVO auctionVO = new AuctionRefVO(auctionTicket.getAuctionId().getAuctionId(),auctionTicket.getAuctionId().getAuctionName());
		this.auctionId = auctionVO;
		UserRefVO user = new UserRefVO(auctionTicket.getUserId().getUserId());
		this.userId = user;
		this.createdOn = auctionTicket.getCreatedOn();
		this.ticketId = auctionTicket.getTicketId();
	}
	
	
	@JsonIgnore
	public AuctionTicket getAuctionTicket() {
		AuctionTicket auctionTicket = new AuctionTicket();
		Auction auction = new Auction(this.getAuctionId().getAuctionId(),this.getAuctionId().getAuctionName());
		auctionTicket.setAuctionId(auction);
		User user = new User(this.getUserId().getUserId());
		auctionTicket.setUserId(user);
		auctionTicket.setCreatedOn(createdOn);
		//auctionTicket.setTicketId(ticketId);
		return auctionTicket;
	}
	


	
	
}
	
	

