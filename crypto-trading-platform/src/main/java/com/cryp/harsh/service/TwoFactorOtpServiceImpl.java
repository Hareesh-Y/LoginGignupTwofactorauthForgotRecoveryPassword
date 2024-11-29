package com.cryp.harsh.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cryp.harsh.model.TwoFactorOtp;
import com.cryp.harsh.model.User;
import com.cryp.harsh.repository.TwoFactorOtpRepository;

@Service
public class TwoFactorOtpServiceImpl implements TwoFactorOtpService{

	
	@Autowired
	private TwoFactorOtpRepository twoFactorOtpRepository;
	
	@Override
	public TwoFactorOtp createTwoFactorOtp(User user, String otp, String jwt) {
		
		UUID uuid = UUID.randomUUID();
		
		String id = uuid.toString();
		
		TwoFactorOtp twoFactorOtp = new TwoFactorOtp();
		
		twoFactorOtp.setId(id);
		twoFactorOtp.setUser(user);
		twoFactorOtp.setOtp(otp);
		twoFactorOtp.setJwt(jwt);
		
		return twoFactorOtpRepository.save(twoFactorOtp);
		 
	}

	@Override
	public TwoFactorOtp findByUser(Long userId) {
		return twoFactorOtpRepository.findByUserId(userId);
	}

	@Override
	public TwoFactorOtp findById(String id) {
		Optional<TwoFactorOtp> opt = twoFactorOtpRepository.findById(id);
		return opt.orElse(null);
	}

	@Override
	public boolean verifyTwoFactorOtp(TwoFactorOtp twoFactorOtp, String otp) {
		return twoFactorOtp.getOtp().equals(otp);
	}

	@Override
	public void deleteTwoFactorOtp(TwoFactorOtp twoFactorOtp) {
		twoFactorOtpRepository.delete(twoFactorOtp);
	}

}
