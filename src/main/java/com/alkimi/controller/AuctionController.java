package com.alkimi.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkimi.exceptions.ClientErrorException;
import com.alkimi.service.AuctionService;
import com.alkimi.vo.AuctionPrizeDetailsVO;
import com.alkimi.vo.AuctionVO;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/api")
@Validated
@Slf4j
public class AuctionController {
	
	@Autowired
	private AuctionService service;
	    
    @GetMapping("/auctions")
    public List<AuctionVO> getAllAuction() {
    	log.debug("getAllAuctions begin ");

    	List<AuctionVO> luckyDrawers = service.getAllAuction();
    	
    	log.debug("getAllAuctions end");
    	return luckyDrawers;
    }
    
    
    
    @GetMapping("/auctions/{id}")
    public ResponseEntity<?> getAuctionById(@Positive @PathVariable("id") int auctionId) {
    	log.info("getAuctionById begin");
    	AuctionVO vo = null;
  		try {
  			vo = service.getAuctionById(auctionId);
  			return new ResponseEntity<AuctionVO>(vo,HttpStatus.OK);
  		} catch(NoSuchElementException ex) {
  			log.error("Exception caught at getting the Auctions by Id {} ", auctionId);
  			throw new ClientErrorException("Auction Not Found For ID : " + auctionId);
  		}
  		
    }
    
    @GetMapping("/auctions/{id}/luckydraw/{prizePositionId}/{prizeIndexId}")
    public ResponseEntity<?> createLuckyDrawWinner(@Positive @PathVariable("id") int auctionId, @PathVariable("prizePositionId") int prizePositionId, @PathVariable("prizeIndexId") int prizeIndexId) {
    	log.info("createLuckyDrawWinner begin");
    	AuctionPrizeDetailsVO vo = null;
  		try {
  			vo = service.createLuckyDrawWinner(auctionId,prizePositionId,prizeIndexId);
  			return new ResponseEntity<AuctionPrizeDetailsVO>(vo,HttpStatus.OK);
  		} catch(NoSuchElementException ex) {
  			log.error("Exception caught at createLuckyDrawWinner by Id {} ", auctionId);
  			throw new ClientErrorException("Exception caught at createLuckyDrawWinner ID : " + auctionId);
  		}
  		
    }
   
    
    @PostMapping("/auctions")
    public ResponseEntity<AuctionVO> createAuction(@Valid @RequestBody AuctionVO auctionVO) {
    	System.out.println("createAuction begin");
    	auctionVO.setCreatedOn(LocalDate.now());
    	auctionVO.setUpdatedOn(LocalDate.now());
    	AuctionVO vo = service.createAuction(auctionVO);
    	HttpHeaders responseHeaders = new HttpHeaders();
   	    System.out.println("createAuction end");
	 	return new ResponseEntity<AuctionVO>(vo,responseHeaders,HttpStatus.CREATED);
	}
    
    
    @PutMapping("/auctions/{auctionId}")
    public AuctionVO updateAuction(@PathVariable int auctionId,@Valid @RequestBody AuctionVO auctionVO) {
    	AuctionVO vo =  service.updateAuction(auctionId, auctionVO);
		return vo;
	}
    
    @DeleteMapping("/auctions/{auctionId}")
    public ResponseEntity<AuctionVO> deleteAuction(@PathVariable int auctionId) {
    	AuctionVO vo = service.deleteAuction(auctionId);
    	return new ResponseEntity<AuctionVO>(vo,HttpStatus.NO_CONTENT);
    }
   
}
