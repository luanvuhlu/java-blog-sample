package com.luanvv.spring.like.likequery.repositories;

import com.luanvv.spring.like.likequery.LikeQueryApplication;
import com.luanvv.spring.like.likequery.entities.Voucher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
		Arrays.stream(result).map(obj -> obj[1]).forEach(log::info);
		assertEquals(2, result.length);
	}

	@Test
	public void testNativeQuery2() {
		var result = repository.findByTitleContainsNative("15%");
		Arrays.stream(result).map(obj -> obj[1]).forEach(log::info);
		assertEquals(2, result.length);
	}

	@Test
	public void testNativeQuery3() {
		var result = repository.findByTitleContainsNative("%");
		Arrays.stream(result).map(obj -> obj[1]).forEach(log::info);
		assertEquals(3, result.length);
	}

	@Test
	public void testQuery() {
		var result = repository.findByTitleContainsQuery("80");
		assertEquals(2, result.length);
	}

	@Test
	public void testQuery2() {
		var result = repository.findByTitleContainsQuery("15%");
		assertEquals(2, result.length);
	}

	@Test
	public void testQuery3() {
		var result = repository.findByTitleContainsQuery("_");
		assertEquals(3, result.length);
	}

	@Test
	public void testMethod() {
		var result = repository.findByTitleContains("Udemy");
		assertEquals(3, result.size());
	}

	@Test
	public void testMethod2() {
		var result = repository.findByTitleContains("15%");
		assertEquals(1, result.size());
	}

	@Test
	public void testMethod3() {
		var result = repository.findByTitleContains("\\_");
		assertEquals(0, result.size());
	}

	@Test
	public void testJpql() {
		var result = repository.findByTitleContainsEscaped("Udemy");
		assertEquals(3, result.size());
	}

	@Test
	public void testJpql2() {
		var result = repository.findByTitleContainsEscaped("15%");
		assertEquals(1, result.size());
	}

	@Test
	public void testJpql3() {
		var result = repository.findByTitleContainsEscaped("\\_");
		assertEquals(0, result.size());
	}

	@BeforeEach
	public void setup() {
		if (repository.count() == 0) {
			repository.saveAll(
					List.of(
							new Voucher(1, "Udemy 80% Discount + Extra 15% Off"),
							new Voucher(2, "Udemy $15.99 Off"),
							new Voucher(4, "Udemy Coupon | $80 + Extra 55% Discount")
					)
			);
			repository.findAll().stream().map(Voucher::getTitle).forEach(log::info);
		}
	}
}
