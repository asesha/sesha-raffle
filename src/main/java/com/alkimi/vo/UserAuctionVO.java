package com.alkimi.vo;

import java.time.LocalDate;

import com.alkimi.entities.Auction;
import com.alkimi.entities.User;
import com.alkimi.entities.UserAuction;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class UserAuctionVO {

	private int userAuctionId;
	
	private UserRefVO userId;
	
	private AuctionRefVO auctionId;
	
	private LocalDate createdOn;

	private String status;
	
	public UserAuctionVO(UserAuction userAuction) {
		AuctionRefVO auctionVO = new AuctionRefVO(userAuction.getAuctionId().getAuctionId(),userAuction.getAuctionId().getAuctionName());
		this.auctionId = auctionVO;
		UserRefVO user = new UserRefVO(userAuction.getUserId().getUserId());
		this.userId = user;
		this.createdOn = userAuction.getCreatedOn();
		this.status = userAuction.getStatus();
	}
	
	
	@JsonIgnore
	public UserAuction getUserAuction() {
		UserAuction userAuction = new UserAuction();
		Auction auction = new Auction(this.getAuctionId().getAuctionId(),this.getAuctionId().getAuctionName());
		userAuction.setAuctionId(auction);
		User user = new User(this.getUserId().getUserId());
		userAuction.setUserId(user);
		userAuction.setCreatedOn(createdOn);
		userAuction.setStatus(status);
		return userAuction;
	}
	


	
	
}
	
	

