package com.luanvv.spring.like.likequery.repositories;

import com.luanvv.spring.like.likequery.entities.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VoucherRepository extends JpaRepository<Voucher, Integer>{

	@Query(value = "SELECT id, title FROM voucher WHERE title LIKE %?1% ", nativeQuery = true)
	Object[][] findByTitleContainsNative(String title);

	@Query(value = "SELECT id, title FROM Voucher WHERE title LIKE %?1% ")
	Object[][] findByTitleContainsQuery(String title);

	List<Voucher> findByTitleContains(String title);

	@Query("FROM Voucher WHERE title LIKE %?#{escape([0])}% escape ?#{escapeCharacter()}")
	List<Voucher> findByTitleContainsEscaped(String title);
}
