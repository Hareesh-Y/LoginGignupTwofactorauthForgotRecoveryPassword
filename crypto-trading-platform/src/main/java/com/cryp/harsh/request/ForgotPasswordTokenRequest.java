package com.cryp.harsh.request;

import com.cryp.harsh.domain.VerificationType;

public class ForgotPasswordTokenRequest {
	
	private String sendTo;
	private VerificationType verificationType;
	public String getSendTo() {
		return sendTo;
	}
	public void setSendTo(String sendTo) {
		this.sendTo = sendTo;
	}
	public VerificationType getVerificationType() {
		return verificationType;
	}
	public void setVerificationType(VerificationType verificationType) {
		this.verificationType = verificationType;
	}
	public ForgotPasswordTokenRequest(String sendTo, VerificationType verificationType) {
		super();
		this.sendTo = sendTo;
		this.verificationType = verificationType;
	}
	
	public ForgotPasswordTokenRequest() {}
	
	
}
