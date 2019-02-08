package recursos.autorizacion;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class TokenHelper {
	static long TIEMPO_TOKEN_MIN = 10;
	static Map<String,String> tokenMap = new HashMap<>(); 
	public static void setToken(String token,String user){
		tokenMap.put(token, user);
	}
	public static boolean isValidoToken(String token){
		if( tokenMap.containsKey(token)){
			String userName =  tokenMap.get(token);
			return  isTokenInTime( userName, token);
		}else
			return false;

	}
	static boolean isTokenInTime(String userName,String token){
		StandardPBEStringEncryptor jasypt = new StandardPBEStringEncryptor();
		jasypt.setPassword(userName);
		String decrypToken =jasypt.decrypt(token);
		String strMinutos = decrypToken.split("\\|")[2];
		long minutes = System.currentTimeMillis() / 1000 / 60;
		return ((minutes-Long.valueOf(strMinutos))< TokenHelper.TIEMPO_TOKEN_MIN);
	}
	public static String getUserName(String token){
		return tokenMap.get(token);
	}
	public static String removeToken(String token){
		return tokenMap.remove(token);
	}
	public static String generarToken(String userName){
		long minutes = System.currentTimeMillis() / 1000 / 60;
		String key = UUID.randomUUID().toString().toUpperCase() +
				"|" + userName +
				"|" + minutes;
		StandardPBEStringEncryptor jasypt = new StandardPBEStringEncryptor();
		jasypt.setPassword(userName);
		
		String authenticationToken = jasypt.encrypt(key);
		return authenticationToken;
	}
}
