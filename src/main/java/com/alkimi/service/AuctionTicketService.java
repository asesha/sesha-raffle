package com.alkimi.service;

import java.util.List;

import com.alkimi.vo.AuctionTicketVO;

public interface AuctionTicketService {
	public List<AuctionTicketVO> createAuctionTicket(AuctionTicketVO vo);
	public List<String> getSoldTicketListByActionId(int auctionId);
	public List<String> getUnSoldTicketListByActionId(int auctionId);
}
