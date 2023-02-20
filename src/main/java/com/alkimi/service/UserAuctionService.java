package com.alkimi.service;

import java.util.List;

import com.alkimi.vo.UserAuctionVO;

public interface UserAuctionService {
	public UserAuctionVO createUserAuction(UserAuctionVO userAuction);
	public List<UserAuctionVO> getUserListByActionId(int auctionId);
	public List<UserAuctionVO> getAuctionListUserId(int userId);

}
