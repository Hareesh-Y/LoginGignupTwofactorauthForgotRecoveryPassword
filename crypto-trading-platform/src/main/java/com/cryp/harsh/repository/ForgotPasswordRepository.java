package com.cryp.harsh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cryp.harsh.model.ForgotPasswordToken;

public interface ForgotPasswordRepository extends JpaRepository<ForgotPasswordToken , String>{

	ForgotPasswordToken findByUserId(Long userId);
}
