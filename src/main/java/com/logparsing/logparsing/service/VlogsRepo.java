package com.logparsing.logparsing.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface VlogsRepo extends JpaRepository<Vlogs, Integer> {

	Vlogs findByGatewayTraceId(String gatewayTraceId);

	@Modifying
	@Transactional
	@Query("update Vlogs v set v.detokenizationTimeTaken = ?1  where v.gatewayTraceId = ?2")
	void updateDetokenizationTimeTakenByGatewayTraceId(String DetokenizationTimeTaken, String gatewayTraceId);

	@Modifying
	@Transactional
	@Query("update Vlogs v set v.validationTimeTaken = ?1  where v.gatewayTraceId = ?2")
	void updateValidationTimeTakenByGatewayTraceId(String validationTimeTaken, String gatewayTraceId);

	@Modifying
	@Transactional
	@Query("update Vlogs v set v.dimeboxCallTIMETAKEN = ?1  where v.gatewayTraceId = ?2")
	void updatedimeboxCallTIMETAKENByGatewayTraceId(String dimeboxCallTIMETAKEN, String gatewayTraceId);
}
