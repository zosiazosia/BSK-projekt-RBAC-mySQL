package com.jwt.hibernate.controller;

import java.security.InvalidKeyException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
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
	    byte[] encrypted = c.doFinal(string.getBytes());
	    
	    byte[] encodedBytes = Base64.encodeBase64(encrypted);
	    String ret = new String (encodedBytes);
	    return ret;
		
	}

	public String decrypt(String string) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
		
		byte[] keyBytes = stringKey.getBytes();			
	    Key key = new SecretKeySpec(keyBytes, "AES");
	    Cipher c = Cipher.getInstance("AES");
	    c.init(Cipher.DECRYPT_MODE, key);
	    
	    byte[] stringBytes = Base64.decodeBase64(string.getBytes());
	    byte[] decValue = c.doFinal(stringBytes);
	//    byte[] decValue = c.doFinal(string.getBytes());
	    //return new String (decValue);
	    
	    String decryptedValue = new String(decValue);
	    System.out.println("decrypted " + decryptedValue);
	    
	    return decryptedValue;
	    
	    
	    //return string;
	}
	
}
