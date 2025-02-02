package com.cryp.harsh.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cryp.harsh.config.JwtProvider;
import com.cryp.harsh.domain.VerificationType;
import com.cryp.harsh.model.TwoFactorAuth;
import com.cryp.harsh.model.User;
import com.cryp.harsh.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User findUserProfileByJwt(String jwt) throws Exception {
		
		String email = JwtProvider.getEmailFromToken(jwt);
		
		User user = userRepository.findByEmail(email);
		
		if(user == null) {
			throw new Exception("user not found");
		}
		return user;
	}

	@Override
	public User findUserByEmail(String email) throws Exception {
		
		User user = userRepository.findByEmail(email);
		
		if(user == null) {
			throw new Exception("user not found");
		}
		return user;
	}

	@Override
	public User findUserById(Long userId) throws Exception {
		Optional<User> user = userRepository.findById(userId);
		
		if(user.isEmpty()) {
			throw new Exception("user not found");
		}
		return user.get();
	}

	@Override
	public User enableTwoFactorAuthentication(VerificationType verificationTypeUser, String sendTo, User user) {
		TwoFactorAuth twoFactorAuth = new TwoFactorAuth();
		twoFactorAuth.setEnabled(true);
		twoFactorAuth.setSendTo(verificationTypeUser);
		
		user.setTwoFactorAuth(twoFactorAuth);
		
		return userRepository.save(user);
	}

	@Override
	public User updatePassword(User user, String newPassword) {
		user.setPassword(newPassword);
		return userRepository.save(user);
	}

}
