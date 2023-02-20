package com.alkimi.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkimi.service.AuctionTicketService;
import com.alkimi.vo.AuctionTicketVO;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/api")
@Validated
@Slf4j
public class AuctionTickerController {
	
	@Autowired
	private AuctionTicketService service;
	    
   
    
    @PostMapping("/auctiontickets")
    public ResponseEntity<List<AuctionTicketVO>> createAuctionTicket(@Valid @RequestBody AuctionTicketVO auctionTicketVO) {
    	log.info("createAuctionTicket begin");
    	auctionTicketVO.setCreatedOn(LocalDate.now());
    	List<AuctionTicketVO> vo = service.createAuctionTicket(auctionTicketVO);
    	HttpHeaders responseHeaders = new HttpHeaders();
   	    log.info("createAuctionTicket end");
	 	return new ResponseEntity<List<AuctionTicketVO>>(vo,responseHeaders,HttpStatus.CREATED);
	}
    
    
    @GetMapping("/auctiontickets/{id}/sold")
    public List<String> getSoldTickets(@PathVariable("id") int auctionId) {
    	System.out.println("getSoldTickets begin ");

    	List<String> tickets = service.getSoldTicketListByActionId(auctionId);
    	
    	System.out.println("getSoldTickets end");
    	return tickets;
    }
    
    @GetMapping("/auctiontickets/{id}/unsold")
    public List<String> getUnSoldTickets(@PathVariable("id") int auctionId) {
    	System.out.println("getUnSoldTickets begin ");

    	List<String> tickets = service.getUnSoldTicketListByActionId(auctionId);
    	
    	System.out.println("getUnSoldTickets end");
    	return tickets;
    }
    
    
   
}
