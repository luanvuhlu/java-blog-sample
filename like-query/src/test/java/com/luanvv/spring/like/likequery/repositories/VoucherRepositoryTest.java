package com.luanvv.spring.like.likequery.repositories;

import com.luanvv.spring.like.likequery.LikeQueryApplication;
import com.luanvv.spring.like.likequery.entities.Voucher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = LikeQueryApplication.class)
public class VoucherRepositoryTest {

	private static final Logger log = LogManager.getLogger(VoucherRepositoryTest.class);

	@Autowired
	private VoucherRepository repository;
	
	@Test
	public void testNativeQuery() {
		var result = repository.findByTitleContainsNative("80");
		assertEquals(2, result.length);
	}

	@Test
	public void testNativeQuery2() {
		var result = repository.findByTitleContainsNative("15%");
		assertEquals(2, result.length);
	}

	@Test
	public void testNativeQuery3() {
		var result = repository.findByTitleContainsNative("%");
		assertEquals(3, result.length);
	}

	@Test
	public void testQuery() {
		var result = repository.findByTitleContains("80");
		assertEquals(2, result.length);
	}

	@Test
	public void testQuery2() {
		var result = repository.findByTitleContains("15%");
		assertEquals(2, result.length);
	}

	@Test
	public void testQuery3() {
		var result = repository.findByTitleContains("%");
		assertEquals(3, result.length);
	}

	@BeforeEach
	public void setup() {
		if (repository.count() == 0) {
			repository.saveAll(
					List.of(
							new Voucher(1, "80% + Extra 15% Off"),
							new Voucher(2, "$15.99 Only"),
							new Voucher(4, "Coupon | $80 + Extra 55% Discount")
					)
			);
			repository.findAll().stream().map(Voucher::getTitle).forEach(log::info);
		}
	}
}
