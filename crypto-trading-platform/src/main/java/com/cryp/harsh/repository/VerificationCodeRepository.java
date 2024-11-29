package com.cryp.harsh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cryp.harsh.model.VerificationCode;

public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Long>{
	
	public VerificationCode findByUserId(Long userId);
}
