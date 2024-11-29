package com.cryp.harsh.request;

public class ResetPasswordRequest {
	
	private String otp;
	private String password;
	
	
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public ResetPasswordRequest(String otp, String password) {
		super();
		this.otp = otp;
		this.password = password;
	}
	
	public ResetPasswordRequest() {}
	

}
