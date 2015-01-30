package com.unbank.tools;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class DESTools {

	Key key;

	public DESTools() {

	}

	public DESTools(String str) {
		setKey(str); // 生成密匙
	}

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	/**
	 * 根据参数生成 KEY
	 */
	public void setKey(String strKey) {
		try {
			KeyGenerator _generator = KeyGenerator.getInstance("DES");
			_generator.init(new SecureRandom(strKey.getBytes()));
			this.key = _generator.generateKey();
			_generator = null;
		} catch (Exception e) {
			throw new RuntimeException(
					"Error initializing SqlMap class. Cause: " + e);
		}
	}

	/**
	 * 加密 String 明文输入 ,String 密文输出
	 */
	public String encryptStr(String strMing) {
		byte[] byteMi = null;
		byte[] byteMing = null;
		String strMi = "";
		BASE64Encoder base64en = new BASE64Encoder();
		try {
			byteMing = strMing.getBytes("UTF8");
			byteMi = this.encryptByte(byteMing);
			strMi = base64en.encode(byteMi);
		} catch (Exception e) {
			throw new RuntimeException(
					"Error initializing SqlMap class. Cause: " + e);
		} finally {
			base64en = null;
			byteMing = null;
			byteMi = null;
		}
		return strMi;
	}

	/**
	 * 解密 以 String 密文输入 ,String 明文输出
	 * 
	 * @param strMi
	 * @return
	 */
	public String decryptStr(String strMi) {
		BASE64Decoder base64De = new BASE64Decoder();
		byte[] byteMing = null;
		byte[] byteMi = null;
		String strMing = "";
		try {
			byteMi = base64De.decodeBuffer(strMi);
			byteMing = this.decryptByte(byteMi);
			strMing = new String(byteMing, "UTF8");
		} catch (Exception e) {
			throw new RuntimeException(
					"Error initializing SqlMap class. Cause: " + e);
		} finally {
			base64De = null;
			byteMing = null;
			byteMi = null;
		}
		return strMing;
	}

	/**
	 * 加密以 byte[] 明文输入 ,byte[] 密文输出
	 * 
	 * @param byteS
	 * @return
	 */
	private byte[] encryptByte(byte[] byteS) {
		byte[] byteFina = null;
		Cipher cipher;
		try {
			cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byteFina = cipher.doFinal(byteS);
		} catch (Exception e) {
			throw new RuntimeException(
					"Error initializing SqlMap class. Cause: " + e);
		} finally {
			cipher = null;
		}
		return byteFina;
	}

	/**
	 * 解密以 byte[] 密文输入 , 以 byte[] 明文输出
	 * 
	 * @param byteD
	 * @return
	 */
	private byte[] decryptByte(byte[] byteD) {
		Cipher cipher;
		byte[] byteFina = null;
		try {
			cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.DECRYPT_MODE, key);
			byteFina = cipher.doFinal(byteD);
		} catch (Exception e) {
			throw new RuntimeException(
					"Error initializing SqlMap class. Cause: " + e);
		} finally {
			cipher = null;
		}
		return byteFina;
	}

	/**
	 * 文件 file 进行加密并保存目标文件 destFile 中
	 * 
	 * @param file
	 *            要加密的文件 如 c:/test/srcFile.txt
	 * @param destFile
	 *            加密后存放的文件名 如 c:/ 加密后文件 .txt
	 */
	public void encryptFile(String file, String destFile) throws Exception {
		Cipher cipher = Cipher.getInstance("DES");
		// cipher.init(Cipher.ENCRYPT_MODE, getKey());
		cipher.init(Cipher.ENCRYPT_MODE, this.key);
		InputStream is = new FileInputStream(file);
		OutputStream out = new FileOutputStream(destFile);
		CipherInputStream cis = new CipherInputStream(is, cipher);
		byte[] buffer = new byte[1024];
		int r;
		while ((r = cis.read(buffer)) > 0) {
			out.write(buffer, 0, r);
		}
		cis.close();
		is.close();
		out.close();
	}

	/**
	 * 文件采用 DES 算法解密文件
	 * 
	 * @param file
	 *            已加密的文件 如 c:/ 加密后文件 .txt *
	 * @param destFile
	 *            解密后存放的文件名 如 c:/ test/ 解密后文件 .txt
	 */
	public void decryptFile(String file, String dest) throws Exception {
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.DECRYPT_MODE, this.key);
		InputStream is = new FileInputStream(file);
		OutputStream out = new FileOutputStream(dest);
		CipherOutputStream cos = new CipherOutputStream(out, cipher);
		byte[] buffer = new byte[1024];
		int r;
		while ((r = is.read(buffer)) >= 0) {
			cos.write(buffer, 0, r);
		}
		cos.close();
		out.close();
		is.close();
	}

	public static void main(String[] args) throws Exception {
		DESTools des = new DESTools("1234567");
		// DES 加密文件
		// des.encryptFile("G:/test.doc", "G:/ 加密 test.doc");
		// DES 解密文件
		// des.decryptFile("G:/ 加密 test.doc", "G:/ 解密 test.doc");
		String str1 = "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 osEpZusiidhNG/hRlIcZP39fmMo=";
		// DES 加密字符串
//		String str2 = des.encryptStr(str1);
		// DES 解密字符串
		String deStr = des.decryptStr(str1);
		System.out.println(" 加密前： " + str1);
//		System.out.println(" 加密后： " + str2);
		System.out.println(" 解密后： " + deStr);
	}
}