package com.life.utils;

import javax.mail.internet.InternetAddress;
import javax.xml.bind.DatatypeConverter;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class StringUtils {
	
	private static final Logger LOGGER = Logger.getLogger(StringUtils.class.getName());
	
	/**
	 *	STRING TO INTEGER
	 */
	public static Integer intValueOf(String value) {
		Integer number = null;
		if(value != null && !value.trim().isEmpty()) {
			try {
				number = new BigDecimal(value).intValue();
			}
			catch (Exception ex) {
				LOGGER.log(Level.WARNING, ex.getMessage());
				LOGGER.log(Level.WARNING, ex.getStackTrace().toString());
			}
		}
		return number;
	}
	
	/**
	 *	STRING TO LONG
	 */
	public static Long longValueOf(String value) {
		Long number = null;
		if(value != null && !value.trim().isEmpty()) {
			try {
				number = new BigDecimal(value).longValue();
			}
			catch (Exception ex) {
				LOGGER.log(Level.WARNING, ex.getMessage());
				LOGGER.log(Level.WARNING, ex.getStackTrace().toString());
			}
		}
		return number;
	}
	
	/**
	 *	STRING TO BIG DECIMAL
	 */
	public static BigDecimal bigDecimalValueOf(String value) {
		BigDecimal bigDecimal = null;
		if(value != null && !value.trim().isEmpty()) {
			try {
				bigDecimal = new BigDecimal(value);
			}
			catch (Exception ex) {
				LOGGER.log(Level.WARNING, ex.getMessage());
				LOGGER.log(Level.WARNING, ex.getStackTrace().toString());
			}
		}
		return bigDecimal;
	}
	
	/**
	 *	CHECK CHUỖI KÝ TỰ SỐ
	 */
	public static boolean isNumber(String input) {
		boolean flag = false;
		try {
			Long.valueOf(input);
			flag = true;
		}
		catch(Exception ex) {
			LOGGER.log(Level.WARNING, ex.getMessage());
			LOGGER.log(Level.WARNING, ex.getStackTrace().toString());
		}
		return flag;
	}
	
	/**
	 *	MÃ HÓA MD5
	 */
	public static String hashCodeMD5(String origin) {
		String hashCode = origin;
		try {
			hashCode = DatatypeConverter.printHexBinary(MessageDigest.getInstance("MD5").digest(origin.getBytes("UTF8")));
		}
		catch(Exception ex) {
			LOGGER.log(Level.WARNING, ex.getMessage());
			LOGGER.log(Level.WARNING, ex.getStackTrace().toString());
		}
		return hashCode;
	}
	
	/**
	 *	CHUẨN HÓA DANH TỪ RIÊNG VD: cHU    VĂn   hà => Chu Văn Hà
	 */
	public static String formatNoun(String noun) {
		noun = noun.trim().replaceAll("\\s+", " ");
		String tokens[] = noun.split(" ");
		String result = "";
		for (int i = 0, length = tokens.length; i < length; i++) {
			result += String.valueOf(tokens[i].charAt(0)).toUpperCase() + tokens[i].substring(1).toLowerCase();
			if (i < length - 1)
				result += " ";
		}
		return result;
	}

	/**
	 *	CHUYỂN TIẾNG VIỆT CÓ DẤU THÀNH KHÔNG DẤU VD: Chu Văn Hà => Chu Van Ha
	 */
	public static String convertUnicodeToASCII(String inputText) {
		String result = null;
		try {
			result = Normalizer.normalize(inputText, Normalizer.Form.NFD);
			Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
			result = pattern.matcher(result)
							.replaceAll("")
							.replaceAll("đ", "d")
							.replaceAll("Đ", "D");
		}
		catch (Exception ex) {
			result = inputText;
			LOGGER.log(Level.WARNING, ex.getMessage());
			LOGGER.log(Level.WARNING, ex.getStackTrace().toString());
		}
		return result;
	}
	
	/**
	 *	CHECK EMAIL ĐÚNG ĐỊNH DẠNG
	 */
	public static boolean validateEmail(String inputText) {
		boolean flag = false;
		try {
			InternetAddress internetAddress = new InternetAddress();
			internetAddress.setAddress(inputText);
			internetAddress.validate();
			flag = true;
		}
		catch (Exception ex) {
			LOGGER.log(Level.WARNING, ex.getMessage());
			LOGGER.log(Level.WARNING, ex.getStackTrace().toString());
		}
		return flag;
	}
	
	/**
	 *	BIG DECIMAL TO CURRENCY
	 */
	public static String bigDecimalToCurrency(BigDecimal bigDecimal) {
		String result = bigDecimal!= null? NumberFormat.getCurrencyInstance().format(bigDecimal).replace("$", "") : "";
		return result;
	}
	
	/**
	 *	LONG TO CURRENCY
	 */
	public static String longToCurrency(Long longValue) {
		String result = longValue!= null? NumberFormat.getCurrencyInstance().format(longValue).replace("$", "") : "";
		return result;
	}

}