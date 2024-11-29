package com.cryp.harsh.model;

import com.cryp.harsh.domain.VerificationType;

public class TwoFactorAuth {
	
	private boolean isEnabled = false ;
	private VerificationType sendTo;
	
	public boolean isEnabled() {
		return isEnabled;
	}
	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	public VerificationType getSendTo() {
		return sendTo;
	}
	public void setSendTo(VerificationType sendTo) {
		this.sendTo = sendTo;
	}
	
	public TwoFactorAuth(boolean isEnabled, VerificationType sendTo) {
		super();
		this.isEnabled = isEnabled;
		this.sendTo = sendTo;
	}
	
	public TwoFactorAuth() {}

}
