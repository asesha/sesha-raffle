package com.alkimi.service;

import java.util.List;

import com.alkimi.vo.AuctionPrizeDetailsVO;
import com.alkimi.vo.AuctionVO;
import com.alkimi.vo.UserAuctionVO;

public interface AuctionService {
	public List<AuctionVO> getAllAuction();
	public AuctionVO createAuction(AuctionVO auction);
	public AuctionVO getAuctionById(int auctionId);
	public AuctionVO updateAuction(int auctionId,AuctionVO auctionDetails);
	public AuctionVO deleteAuction(int auctionId);
	
	
	public AuctionPrizeDetailsVO createLuckyDrawWinner(int auctionId,int prizePositionId,int prizeIndexId);
}
