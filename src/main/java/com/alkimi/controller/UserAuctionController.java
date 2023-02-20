package com.alkimi.controller;

import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkimi.service.UserAuctionService;
import com.alkimi.vo.UserAuctionVO;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/api")
@Validated
@Slf4j
public class UserAuctionController {
	
	@Autowired
	private UserAuctionService service;
	    
   
    
    @PostMapping("/userauctions")
    public ResponseEntity<UserAuctionVO> createAuction(@Valid @RequestBody UserAuctionVO userAuctionVO) {
    	log.info("createAuction begin");
    	userAuctionVO.setCreatedOn(LocalDate.now());
    	UserAuctionVO vo = service.createUserAuction(userAuctionVO);
    	HttpHeaders responseHeaders = new HttpHeaders();
   	    log.info("createAuction end");
	 	return new ResponseEntity<UserAuctionVO>(vo,responseHeaders,HttpStatus.CREATED);
	}
    
    
    
   
}
