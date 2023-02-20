package com.alkimi.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alkimi.vo.AuctionVO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="AUCTION")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Auction {
	
	public Auction(int auctionId) {
		this.auctionId = auctionId;
	}
	
	public Auction(int auctionId,String auctionName) {
		this.auctionId = auctionId;
		this.auctionName = auctionName;
	}

	@Id
	@Column(name="AUCTION_ID")
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int auctionId;
	
	@Column(name="AUCTION_NAME")
	private String auctionName;
	
	@Column(name="PRIZE_COUNT")
	private int prizeCount;
	
	@Column(name="START_DATE")
	private LocalDate startDate;
	
	@Column(name="START_TIME")
	private LocalTime startTime;
	
	@Column(name="END_DATE")
	private LocalDate endDate;
	
	@Column(name="END_TIME")
	private LocalTime endTime;
	
	@Column(name="DRAWING_DATE")
	private LocalDate drawingDate;
	
	@Column(name="DRAWING_TIME")
	private LocalTime drawingTime;
	
	@Column(name="MIN_TICKETS")
	private int minTickets;
	
	@Column(name="MAX_TICKETS")
	private int maxTickets;
	
	@Column(name="CREATED_ON")
	protected LocalDate createdOn;
	
	@Column(name="CREATED_BY")
	protected String createdBy;
	
	@Column(name="UPDATED_ON")
	protected LocalDate updatedOn;
	
	@Column(name="UPDATED_BY")
	protected String updatedBy;
	

	public Auction(AuctionVO vo) {
		this.auctionId = vo.getAuctionId();
		this.auctionName = vo.getAuctionName();
		this.prizeCount = vo.getPrizeCount();
		this.startDate = vo.getStartDate();
		this.startTime = vo.getStartTime();
		this.endDate = vo.getEndDate();
		this.endTime = vo.getEndTime();
		this.drawingDate = vo.getDrawingDate();
		this.drawingTime = vo.getDrawingTime();
		this.minTickets = vo.getMinTickets();
		this.maxTickets = vo.getMaxTickets();
		this.createdOn = vo.getCreatedOn();
		this.createdBy = vo.getCreatedBy();
		this.updatedOn = vo.getUpdatedOn();
		this.updatedBy = vo.getUpdatedBy();
		
	}
	

	public AuctionVO getAuctionVO() {
		AuctionVO vo = new AuctionVO();
		vo.setAuctionId(auctionId);
		vo.setAuctionName(auctionName);
		vo.setPrizeCount(prizeCount);
		vo.setStartDate(startDate);
		vo.setStartTime(startTime);
		vo.setEndDate(endDate);
		vo.setEndTime(endTime);
		vo.setDrawingDate(drawingDate);
		vo.setDrawingTime(drawingTime);
		vo.setMinTickets(minTickets);
		vo.setMaxTickets(maxTickets);
		
		vo.setCreatedOn(createdOn);
		vo.setCreatedBy(createdBy);
		vo.setUpdatedOn(updatedOn);
		vo.setUpdatedBy(updatedBy);
		return vo;
	}
	
	
}
	
	

