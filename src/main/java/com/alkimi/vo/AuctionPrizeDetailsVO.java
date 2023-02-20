package com.alkimi.vo;

import java.time.LocalDate;

import com.alkimi.entities.Auction;
import com.alkimi.entities.AuctionPrizeDetails;
import com.alkimi.entities.Product;
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
public class AuctionPrizeDetailsVO {

	private int auctionPrizeId;

	private AuctionRefVO auctionId;
	
	private ProductRefVO productId;
	
	private UserRefVO winnerId;
	
	private int prizePosition; 

	private int prizeIndex;
	
	protected LocalDate createdOn;
	
	protected String createdBy;
	
	protected LocalDate updatedOn;
	
	protected String updatedBy;
	
	private int winnerTicketId;
	
	public AuctionPrizeDetailsVO(AuctionPrizeDetails auctionPrizeDetails) {
		this.auctionPrizeId = auctionPrizeDetails.getAuctionPrizeId();
		AuctionRefVO auctionVO = new AuctionRefVO(auctionPrizeDetails.getAuctionId().getAuctionId());
		this.auctionId = auctionVO;
		//UserRefVO user = new UserRefVO(auctionPrizeDetails.getWinnerId().getUserId());
		//this.winnerId = user;
		ProductRefVO productVO = new ProductRefVO(auctionPrizeDetails.getProductId().getProductId());
		this.productId = productVO;
		this.prizePosition = auctionPrizeDetails.getPrizePosition();
		this.prizeIndex = auctionPrizeDetails.getPrizeIndex();
		this.createdOn = auctionPrizeDetails.getCreatedOn();
		this.createdBy = auctionPrizeDetails.getCreatedBy();
		this.updatedOn = auctionPrizeDetails.getUpdatedOn();
		this.updatedBy = auctionPrizeDetails.getUpdatedBy();

	}
	
	public AuctionPrizeDetailsVO(AuctionPrizeDetails auctionPrizeDetails, User userDetail) {
		this.auctionPrizeId = auctionPrizeDetails.getAuctionPrizeId();
		AuctionRefVO auctionVO = new AuctionRefVO(auctionPrizeDetails.getAuctionId().getAuctionId());
		this.auctionId = auctionVO;
		UserRefVO user = new UserRefVO(userDetail.getUserId(),userDetail.getUserName());
		this.winnerId = user;
		ProductRefVO productVO = new ProductRefVO(auctionPrizeDetails.getProductId().getProductId());
		this.productId = productVO;
		this.prizePosition = auctionPrizeDetails.getPrizePosition();
		this.prizeIndex = auctionPrizeDetails.getPrizeIndex();
		this.createdOn = auctionPrizeDetails.getCreatedOn();
		this.createdBy = auctionPrizeDetails.getCreatedBy();
		this.updatedOn = auctionPrizeDetails.getUpdatedOn();
		this.updatedBy = auctionPrizeDetails.getUpdatedBy();

	}
	
	
	@JsonIgnore
	public AuctionPrizeDetails getAuctionPrizeDetails() {
		AuctionPrizeDetails auctionPrizeDetails = new AuctionPrizeDetails();
		Auction auction = new Auction(this.getAuctionId().getAuctionId(),this.getAuctionId().getAuctionName());
		auctionPrizeDetails.setAuctionId(auction);
		//User user = new User(this.getWinnerId().getUserId());
		//auctionPrizeDetails.setWinnerId(user);
		Product product = new Product(this.getProductId().getProductId());
		auctionPrizeDetails.setProductId(product);
		auctionPrizeDetails.setPrizePosition(prizePosition);
		auctionPrizeDetails.setPrizeIndex(prizeIndex);
		auctionPrizeDetails.setCreatedOn(createdOn);
		auctionPrizeDetails.setCreatedBy(createdBy);
		auctionPrizeDetails.setUpdatedOn(updatedOn);
		auctionPrizeDetails.setUpdatedBy(updatedBy);

		return auctionPrizeDetails;
	}
	


	
	
}
	
	

