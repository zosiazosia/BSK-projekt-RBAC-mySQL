package com.jwt.hibernate.controller;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class Encryptor {
	String stringKey;
	public Encryptor(){
		this.stringKey = "BSKkeySTRING1280";
	}

	public String encrypt(String string) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
		
		byte[] keyBytes = stringKey.getBytes();
	    Key key = new SecretKeySpec(keyBytes, "AES");
	    Cipher c = Cipher.getInstance("AES");
	    c.init(Cipher.ENCRYPT_MODE, key);
	    return new String(c.doFinal(string.getBytes()));
		
	}

	public String decrypt(String string) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
		
		byte[] keyBytes = stringKey.getBytes();
	    Key key = new SecretKeySpec(keyBytes, "AES");
	    Cipher c = Cipher.getInstance("AES");
	    c.init(Cipher.DECRYPT_MODE, key);
	    byte[] decValue = c.doFinal(string.getBytes());
	    String decryptedValue = new String(decValue);
	    return decryptedValue;
	}
}
