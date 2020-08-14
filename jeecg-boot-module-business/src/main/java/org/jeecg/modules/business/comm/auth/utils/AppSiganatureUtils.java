package org.jeecg.modules.business.comm.auth.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AppSiganatureUtils {
	private static long EXPIRE_IN_SECONDS=30000;
	private static String SAPARATOR="@$@";
	public static String createSiganature(String data, String appid, String appSecret, long timestamp) {
        String plain=appid+SAPARATOR+appSecret+SAPARATOR+data+SAPARATOR+timestamp+SAPARATOR+appSecret+SAPARATOR+appid;
        String siganature=encrypt(plain,"SHA-512");
        return siganature;
	}


	public static boolean validateSiganature(String data, String appid, String appSecret, long timestamp, String siganature){
		try{
			String signNow=createSiganature(data, appid, appSecret, timestamp);
			System.out.println("signNow:"+signNow);
			if(signNow!=null)
			    return (System.currentTimeMillis()-timestamp)/1000<EXPIRE_IN_SECONDS&&signNow.equalsIgnoreCase(siganature);
			else
			    return false;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}

	}



	private static String encrypt(String strSrc, String encName) {
        MessageDigest md = null;
        String strDes = null;

        byte[] bt=null;
		try {
			bt = strSrc.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
        try {
            if (encName == null || encName.equals("")) {
                encName = "SHA-256";
            }
            md = MessageDigest.getInstance(encName);
            md.update(bt);
            strDes = bytes2Hex(md.digest()); // to HexString
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        return strDes;
    }

    private static String bytes2Hex(byte[] bts) {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }
}
