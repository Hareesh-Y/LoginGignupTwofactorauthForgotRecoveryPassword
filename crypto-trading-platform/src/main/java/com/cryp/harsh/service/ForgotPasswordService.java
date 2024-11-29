package com.cryp.harsh.service;

import com.cryp.harsh.domain.VerificationType;
import com.cryp.harsh.model.ForgotPasswordToken;
import com.cryp.harsh.model.User;

public interface ForgotPasswordService {
	
	ForgotPasswordToken createToken(User user,
							String id,
							String otp, 
							VerificationType verificationType,
							String sendTo);
	
	ForgotPasswordToken findById(String id);
	
	ForgotPasswordToken findByUser(Long userId);
	
	void deleteToken(ForgotPasswordToken token);
}
