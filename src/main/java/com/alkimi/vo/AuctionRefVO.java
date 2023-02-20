package com.alkimi.vo;

import com.alkimi.entities.Auction;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AuctionRefVO {
	
	private int auctionId;
	
	private String auctionName;
	
	public AuctionRefVO(int auctionId) {
		this.auctionId = auctionId;
	}
	
	
	public AuctionRefVO(Auction auction) {
		this.auctionId = auction.getAuctionId();
	}

}