package com.cryp.harsh.service;

import com.cryp.harsh.domain.VerificationType;
import com.cryp.harsh.model.User;
import com.cryp.harsh.model.VerificationCode;

public interface VerificationCodeService {
	
	VerificationCode sendVerificationCode(User user, VerificationType verificationTyoe);
	
	VerificationCode getVerificationCodeById(Long id) throws Exception;
	
	VerificationCode getVerificationCodeByUser(User user);
	
	void deleteVerificationCodeById(VerificationCode verificationCode);
	
	
}
