package com.oddlycodes.byteprocessor.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class WatcherController {

    private Logger logger = LoggerFactory.getLogger(WatcherController.class);

    @GetMapping("/live")
    public ResponseEntity<String> ping() {
        logger.info("live from watchController");
        return ResponseEntity.ok("live");

    }



}
