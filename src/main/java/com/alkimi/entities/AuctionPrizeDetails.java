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

import com.alkimi.vo.AuctionPrizeDetailsVO;
import com.alkimi.vo.AuctionRefVO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "AUCTION_PRIZE_DETAILS")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AuctionPrizeDetails {

	@Id
	@Column(name = "AUCTION_PRIZE_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int auctionPrizeId;

	@ManyToOne
	@JoinColumn(name = "AUCTION_ID_FK", referencedColumnName = "AUCTION_ID", nullable = false)
	private Auction auctionId;

	@ManyToOne
	@JoinColumn(name = "PRODUCT_ID_FK", referencedColumnName = "PRODUCT_ID", nullable = false)
	private Product productId;

	@ManyToOne
	@JoinColumn(name = "WINNER_ID_FK", referencedColumnName = "USER_ID", nullable = true)
	private User winnerId;

	@Column(name = "PRIZE_POSITION")
	private int prizePosition;
	
	@Column(name = "PRIZE_INDEX")
	private int prizeIndex;
	
	@Column(name="TICKET_ID")
	private int ticketId;

	@Column(name = "CREATED_ON")
	protected LocalDate createdOn;

	@Column(name = "CREATED_BY")
	protected String createdBy;

	@Column(name = "UPDATED_ON")
	protected LocalDate updatedOn;

	@Column(name = "UPDATED_BY")
	protected String updatedBy;

	public AuctionPrizeDetails(AuctionPrizeDetailsVO vo) {
		Auction auction = new Auction(vo.getAuctionId().getAuctionId());
		this.auctionId = auction;
		// User user = new
		// User(vo.getWinnerId().getUserId(),vo.getWinnerId().getUserName());
		// this.winnerId = user;
		Product product = new Product(vo.getProductId().getProductId());
		this.productId = product;
		this.prizePosition = vo.getPrizePosition();
		this.prizeIndex = vo.getPrizeIndex();
		this.createdOn = vo.getCreatedOn();
		this.createdBy = vo.getCreatedBy();
		this.updatedOn = vo.getUpdatedOn();
		this.updatedBy = vo.getUpdatedBy();

	}

	public AuctionPrizeDetailsVO getAuctionPrizeDetailsVO() {
		AuctionPrizeDetailsVO vo = new AuctionPrizeDetailsVO();
		vo.setAuctionPrizeId(auctionPrizeId);
		AuctionRefVO auctionVO = new AuctionRefVO(this.getAuctionId().getAuctionId(),
				this.getAuctionId().getAuctionName());
		vo.setAuctionId(auctionVO);
		// UserRefVO userVO = new
		// UserRefVO(this.getWinnerId().getUserId(),this.getWinnerId().getUserName());
		// vo.setWinnerId(userVO);
		
		vo.setPrizePosition(prizePosition);
		vo.setPrizeIndex(prizeIndex);

		vo.setCreatedOn(createdOn);
		vo.setCreatedBy(createdBy);
		vo.setUpdatedOn(updatedOn);
		vo.setUpdatedBy(updatedBy);

		return vo;
	}

}
