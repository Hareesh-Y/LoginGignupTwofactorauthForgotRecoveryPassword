package com.cryp.harsh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cryp.harsh.model.TwoFactorOtp;

public interface TwoFactorOtpRepository extends JpaRepository<TwoFactorOtp , String>{
	TwoFactorOtp findByUserId(Long userId);
}
