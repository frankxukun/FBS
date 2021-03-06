package sg.com.fbs.services.security.password;

/**
 * @Author Frank Xu $
 * @Created 11:52:04 am 11 Aug, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public interface PasswordServices {

	/**
	 * Retrieve transport RSA key modulus from HSM via RESTful call for client-side data encryption 
	 * @return HEX encoded RSA key modulus
	 */
	String getTransportRSAKeyModulus();
	
	/**
	 * Retrieve transport RSA key exponent from HSM via RESTful call for client-side data encryption 
	 * @return HEX encoded RSA key exponent
	 */
	String getTransportRSAKeyExponent();
	
	String encodePasswordWithBCrypt(String rawPassword);
	
	/**
	 * Decrypt HEX encoded encrypted hashed password and re-encrypt using symmetric key with cryptographic services
	 * via RESTful call.
	 * 
	 * @param password HEX encoded hashed password string
	 * @return HEX encoded encrypted hashed password string
	 */
	String decryptPassword(String password);
}
