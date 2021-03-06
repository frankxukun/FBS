package sg.com.fbs.services.security.external.crypto.provider;

/**
 * @Author Frank Xu $
 * @Created 9:45:48 am 11 Aug, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public interface CryptoServicesClientIF {
	
	String getTransportRSAKeyModulus();
	
	String getTransportRSAKeyExponent();
	
	String decryptPassword(String password);
	
}
