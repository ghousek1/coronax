package com.ghouse.coronax;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class CoronaxApplicationTests {

	@Test
	void contextLoads() {
		log.info("Testing coronax Applicatoin");
		assertEquals(true,true);
	}
	

}
