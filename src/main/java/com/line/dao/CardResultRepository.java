package com.line.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.line.entity.CardResult;

@Component
public interface CardResultRepository extends JpaRepository<CardResult, String> {

	@Query(value = "SELECT DATE_FORMAT(c.date,'%Y%m%d') days , count(*) as a, sum(c.pass) as b from card_result as c GROUP BY days;", nativeQuery = true)
	List<Object[]> findbyDay();

	@Query(value = "SELECT DATE_FORMAT(c.date,'%Y%u') week , count(*) as a, sum(c.pass) as b from card_result as c GROUP BY week;", nativeQuery = true)
	List<Object[]> findbyWeek();

	@Query(value = "SELECT DATE_FORMAT(c.date,'%Y%m') month , count(*) as a, sum(c.pass) as b from card_result as c GROUP BY month;", nativeQuery = true)
	List<Object[]> findbyMonth();

	@Query(value = "SELECT point, count(*) as a, sum(c.pass) as b,sum(c.error),sum(c.miss),sum(c.contrary),sum(c.more) from card_result as c GROUP BY point", nativeQuery = true)
	List<Object[]> findByPoint();

}
