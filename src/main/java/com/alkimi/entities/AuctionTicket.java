package com.alkimi.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.alkimi.vo.AuctionRefVO;
import com.alkimi.vo.AuctionTicketVO;
import com.alkimi.vo.AuctionVO;
import com.alkimi.vo.UserRefVO;
import com.alkimi.vo.UserVO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="AUCTION_TICKETS")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AuctionTicket {

	@Id
	@Column(name="AUCTION_TICKETS_ID")
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int auctionTicketId;
	
	@ManyToOne
	@JoinColumn(name = "USER_ID_FK", referencedColumnName="USER_ID", nullable = false)
	private User userId;
	
	@ManyToOne
	@JoinColumn(name = "AUCTION_ID_FK", referencedColumnName="AUCTION_ID", nullable = false)
	private Auction auctionId;
	
	@Column(name="CREATED_ON")
	private LocalDate createdOn;

	
	@Column(name="TICKET_ID")
	private int ticketId;
	

	public AuctionTicket(AuctionTicketVO vo) {
		Auction auction = new Auction(vo.getAuctionId().getAuctionId());
		this.auctionId = auction;
		User user = new User(vo.getUserId().getUserId());
		this.userId = user;
		this.createdOn = vo.getCreatedOn();
		//this.ticketId = vo.getTicketId();
	}
	

	public AuctionTicketVO getAuctionTicketVO() {
		AuctionTicketVO vo = new AuctionTicketVO();
		vo.setAuctionTicketId(auctionTicketId);
		AuctionRefVO auctionVO = new AuctionRefVO(this.getAuctionId().getAuctionId(),this.getAuctionId().getAuctionName());
		vo.setAuctionId(auctionVO);
		UserRefVO userVO = new UserRefVO(this.getUserId().getUserId());
		vo.setUserId(userVO);
		vo.setCreatedOn(createdOn);
		//vo.setTicketId(ticketId);
		return vo;
	}
	
	
}
	
	

