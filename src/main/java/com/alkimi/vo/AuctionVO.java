package com.alkimi.vo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.alkimi.entities.Auction;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

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
public class AuctionVO {
	
	public AuctionVO(int auctionId,String auctionName) {
		this.auctionId = auctionId;
		this.auctionName = auctionName;
	}
	
	private int auctionId;
	
	@NotEmpty(message ="Enter Auction Name")
	private String auctionName;
	
	@NotNull(message ="Enter Prize Count")
	private int prizeCount;
	
	private List<AuctionPrizeDetailsVO> prizes;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate startDate;
	
	private LocalTime startTime;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate endDate;
	
	private LocalTime endTime;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate drawingDate;
	
	private LocalTime drawingTime;
	
	@NotNull(message ="Enter minimum Tickets")
	private int minTickets;
	
	@NotNull(message ="Enter maximum Tickets")
	private int maxTickets;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate createdOn;
	
	private String createdBy;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate updatedOn;
	
	private String updatedBy;
	
	public AuctionVO(Auction auction) {
		this.auctionId = auction.getAuctionId();
		this.auctionName = auction.getAuctionName();
		this.prizeCount = auction.getPrizeCount();
		this.startDate = auction.getStartDate();
		this.startTime = auction.getStartTime();
		this.endDate = auction.getEndDate();
		this.endTime = auction.getEndTime();
		this.drawingDate = auction.getDrawingDate();
		this.drawingTime = auction.getDrawingTime();
		this.minTickets = auction.getMinTickets();
		this.maxTickets = auction.getMaxTickets();
		this.createdOn = auction.getCreatedOn();
		this.createdBy = auction.getCreatedBy();
		this.updatedOn = auction.getUpdatedOn();
		this.updatedBy = auction.getUpdatedBy();
	}
	
	
	@JsonIgnore
	public Auction getAuction() {
		Auction auction = new Auction();
		auction.setAuctionId(auctionId);
		auction.setAuctionName(auctionName);
		auction.setPrizeCount(prizeCount);
		auction.setStartDate(startDate);
		auction.setStartTime(startTime);
		auction.setEndDate(endDate);
		auction.setEndTime(endTime);
		auction.setDrawingDate(drawingDate);
		auction.setDrawingTime(drawingTime);
		auction.setMinTickets(minTickets);
		auction.setMaxTickets(maxTickets);
		
		auction.setCreatedOn(createdOn);
		auction.setCreatedBy(createdBy);
		auction.setUpdatedOn(updatedOn);
		auction.setUpdatedBy(updatedBy);
		return auction;
	}
	
}