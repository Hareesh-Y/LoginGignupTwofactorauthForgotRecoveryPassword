package com.cryp.harsh.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cryp.harsh.domain.VerificationType;
import com.cryp.harsh.model.ForgotPasswordToken;
import com.cryp.harsh.model.User;
import com.cryp.harsh.model.VerificationCode;
import com.cryp.harsh.request.ForgotPasswordTokenRequest;
import com.cryp.harsh.request.ResetPasswordRequest;
import com.cryp.harsh.response.ApiResponse;
import com.cryp.harsh.response.AuthResponse;
import com.cryp.harsh.service.EmailService;
import com.cryp.harsh.service.ForgotPasswordService;
import com.cryp.harsh.service.UserService;
import com.cryp.harsh.service.VerificationCodeService;
import com.cryp.harsh.utils.OtpUtils;

@RestController

public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private VerificationCodeService verificationCodeService;

	@Autowired
	private ForgotPasswordService forgotPasswordService;
	
	private String jwt;
	
	
	@GetMapping("/api/users/profile")
	
	public ResponseEntity<User> getUserProfile(@RequestHeader("Autherization") String jwt) throws Exception{
	
	User user = userService.findUserProfileByJwt(jwt);
	
	return new ResponseEntity<User>(user, HttpStatus.OK);
	
	}
	
	
	@PostMapping("/api/users/verificationType/{send-otp}")
	public ResponseEntity<String> sendVerificationOtp(
		@RequestHeader("Autherization") String jwt, @PathVariable VerificationType verificationType) throws Exception{
	
	User user = userService.findUserProfileByJwt(jwt);
	
	VerificationCode verificationCode = verificationCodeService.
			getVerificationCodeByUser(user.getId());
	
	if(verificationCode==null) {
		verificationCode = verificationCodeService.sendVerificationCode(user, verificationType);
	}
	
	if(verificationType.equals(VerificationType.EMAIL)) {
		emailService.sendVerificationOtpEmail(user.getEmail(), verificationCode.getOtp());
	}
	
	return new ResponseEntity<>("verification otp successfully sent", HttpStatus.OK);
	
	}
	
	
	@PatchMapping("/api/users/enable-two-factor/verify-otp/{otp}")
	public ResponseEntity<User> enableTwoFactorAuthentication(
			@PathVariable String otp,
			@RequestHeader("Autherization") String jwt) throws Exception{
	
	User user = userService.findUserProfileByJwt(jwt);
	
	VerificationCode verificationCode = verificationCodeService.getVerificationCodeByUser(user.getId());
	
	String sendTo = verificationCode.getVerificationType().equals(VerificationType.EMAIL)?
			verificationCode.getEmail():verificationCode.getMobile();
	
	boolean isVerified = verificationCode.getOtp().equals(otp);
	
	if(isVerified) {
		User updatedUser = userService.enableTwoFactorAuthentication(verificationCode.getVerificationType() , sendTo, user);
		
		verificationCodeService.deleteVerificationCodeById(verificationCode);
		return new ResponseEntity<>(updatedUser, HttpStatus.OK);	
	}
	
	throw new Exception("wrong otp");	
	
	}
	
	
	@PostMapping("/auth/users/reset-password/send-otp")
	public ResponseEntity<AuthResponse> sendForgotPasswordOtp(
		@RequestBody ForgotPasswordTokenRequest req) throws Exception{
	
	User user = userService.findUserByEmail(req.getSendTo());
	String otp = OtpUtils.generateOTP();
	UUID uuid = UUID.randomUUID();
	String id = uuid.toString();
	
	ForgotPasswordToken token = forgotPasswordService.findByUser(user.getId());
	if(token == null) {
		token = forgotPasswordService.createToken(user, id, req.getVerificationType(),req.getSendTo());
	}
	
	if(req.getVerificationType().equals(VerificationType.EMAIL)) {
		emailService.sendVerificationOtpEmail(
				user.getEmail(), 
				token.getOtp());
	}
	
	AuthResponse response = new AuthResponse();
	response.setSession(token.getId());
	response.setMessage("password reset otp sent successfully");
	
	
	return new ResponseEntity<>(response, HttpStatus.OK);
	
	}
	
	@PatchMapping("/auth/users/reset-password/verify-otp")
	public ResponseEntity<ApiResponse> resetPassword(
			@RequestParam String id,
			@RequestBody ResetPasswordRequest req,
			@RequestHeader("Autherization") String jwt) throws Exception{

	
	ForgotPasswordToken forgotPasswordToken = forgotPasswordService.findById(id);
	
	boolean isVerified = forgotPasswordToken.getOtp().equals(req.getOtp());
	
	if(isVerified) {
		userService.updatePassword(forgotPasswordToken.getUser(), req.getPassword());
		ApiResponse res = new ApiResponse();
		res.setMessage("password updated successfully");
		return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
		}
	throw new Exception("wrong otp");
	}
	
}
