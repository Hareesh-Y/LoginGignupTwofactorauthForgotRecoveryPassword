package com.cryp.harsh.service;

import com.cryp.harsh.model.TwoFactorOtp;
import com.cryp.harsh.model.User;

public interface TwoFactorOtpService {
	
	TwoFactorOtp createTwoFactorOtp(User user,String otp,String jwt);

	TwoFactorOtp findByUser(Long userId);
	
	TwoFactorOtp findById(String id);
	
	boolean verifyTwoFactorOtp(TwoFactorOtp twoFactorOtp, String otp);
	
	void deleteTwoFactorOtp(TwoFactorOtp twoFactorOtp);
}
