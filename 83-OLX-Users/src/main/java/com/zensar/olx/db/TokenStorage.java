package com.zensar.olx.db;

import java.util.HashMap;
import java.util.Map;

public class TokenStorage {
	private static Map<String, String> tokenCache;
	
	static
	{
		tokenCache = new HashMap<>();
	}
	
	//This method is responsible for storing the token
    public static void storeToken(String key,String token)
    {
    	tokenCache.put(key,token);
    }
    public static  String removeToken(String key)
    {
    	return tokenCache.remove(key);
    }
    //This method is responsible for getting the from cache
    //This is written to check if token is present in cache or not?
    public static String getToken(String key)
    {
    	return tokenCache.get(key);
    }
}
