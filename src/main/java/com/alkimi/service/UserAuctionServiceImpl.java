package com.alkimi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkimi.dao.UserAuctionRepository;
import com.alkimi.entities.UserAuction;
import com.alkimi.vo.UserAuctionVO;

@Service
public class UserAuctionServiceImpl implements UserAuctionService{

	@Autowired
	UserAuctionRepository repository;

	@Override
	public UserAuctionVO createUserAuction(UserAuctionVO vo) {
		UserAuction userAuction = repository.save(vo.getUserAuction());
		return userAuction.getUserAuctionVO();
	}

	@Override
	public List<UserAuctionVO> getUserListByActionId(int auctionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserAuctionVO> getAuctionListUserId(int userId) {
		// TODO Auto-generated method stub
		return null;
	}
	

	
	
}
