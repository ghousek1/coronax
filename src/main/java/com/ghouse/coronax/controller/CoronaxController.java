package com.ghouse.coronax.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/coronax")
public class CoronaxController {
	@RequestMapping("/")
	public String indexPage() {
		log.info("Coronax HomePage Loaded In Browser");
		return "index";
	}

}
