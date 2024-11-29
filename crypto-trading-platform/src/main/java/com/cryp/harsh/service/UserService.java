package com.cryp.harsh.service;

import com.cryp.harsh.domain.VerificationType;
import com.cryp.harsh.model.User;

public interface UserService {
	
	public User findUserProfileByJwt(String jwt) throws Exception;
	public User findUserByEmail(String email) throws Exception;
	public User findUserById(Long userId) throws Exception;
	
	public User enableTwoFactorAuthentication(VerificationType verificationTypeUser, String sendTo, User user);
	
	User updatePassword(User user, String newPassword);

}
