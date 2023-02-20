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
import com.alkimi.vo.UserAuctionVO;
import com.alkimi.vo.UserRefVO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="USER_AUCTION")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserAuction {

	@Id
	@Column(name="USER_ACTION_ID")
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int userAuctionId;
	
	@ManyToOne
	@JoinColumn(name = "USER_ID_FK", referencedColumnName="USER_ID", nullable = false)
	private User userId;
	
	@ManyToOne
	@JoinColumn(name = "AUCTION_ID_FK", referencedColumnName="AUCTION_ID", nullable = false)
	private Auction auctionId;
	
	@Column(name="CREATED_ON")
	private LocalDate createdOn;

	
	@Column(name="STATUS")
	private String status;
	

	public UserAuction(UserAuctionVO vo) {
		Auction auction = new Auction(vo.getAuctionId().getAuctionId(),vo.getAuctionId().getAuctionName());
		this.auctionId = auction;
		User user = new User(vo.getUserId().getUserId());
		this.userId = user;
		this.createdOn = vo.getCreatedOn();
		this.status = vo.getStatus();
	}
	

	public UserAuctionVO getUserAuctionVO() {
		UserAuctionVO vo = new UserAuctionVO();
		vo.setUserAuctionId(userAuctionId);
		AuctionRefVO auctionVO = new AuctionRefVO(this.getAuctionId().getAuctionId(),this.getAuctionId().getAuctionName());
		vo.setAuctionId(auctionVO);
		UserRefVO userVO = new UserRefVO(this.getUserId().getUserId());
		vo.setUserId(userVO);
		vo.setCreatedOn(createdOn);
		vo.setStatus(status);
		return vo;
	}
	
	
}
	
	

