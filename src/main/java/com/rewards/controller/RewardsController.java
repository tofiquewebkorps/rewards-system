package com.rewards.controller;

import com.rewards.reponse.ResponseHandler;
import com.rewards.service.RewardsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class  RewardsController {

    @Autowired
    private RewardsService rewardsService;

    @GetMapping("rewards/{id}")
    public ResponseEntity<Object> getRewardsDetails(@PathVariable Long id){

        return ResponseHandler.generateResponse("success", HttpStatus.OK,rewardsService.getRewardsMonthWise(id));
    }


}
