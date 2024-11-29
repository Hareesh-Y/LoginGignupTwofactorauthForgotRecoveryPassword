package com.cryp.harsh.model;

import com.cryp.harsh.domain.VerificationType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class ForgotPasswordToken {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	
	@OneToOne
	private User user;
	
	private String otp;
	
	private VerificationType verificationType;
	
	private String sendTo;

	public String getId() {
		return id;
	}

	public void setId(String id2) {
		this.id = id2;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public VerificationType getVerificationType() {
		return verificationType;
	}

	public void setVerificationType(VerificationType verificationType) {
		this.verificationType = verificationType;
	}

	public String getSendTo() {
		return sendTo;
	}

	public void setSendTo(String sendTo) {
		this.sendTo = sendTo;
	}

	public ForgotPasswordToken(String id, User user, String otp, VerificationType verificationType, String sendTo) {
		super();
		this.id = id;
		this.user = user;
		this.otp = otp;
		this.verificationType = verificationType;
		this.sendTo = sendTo;
	}
	
	public ForgotPasswordToken() {}
	
}
