package com.endurance.shield.xmen.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by kai on 16/6/17.
 */
public class KeyManager {
    public static String encrypt(String key,Map<String,String> content)
    {
        return Jwts.builder()
                .setSubject(new JSONObject(content).toString())
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }
    public static Map<String,String> decrypt(String key,String encrypted)
    {
        String content = Jwts.parser().setSigningKey(key).parseClaimsJws(encrypted).getBody().getSubject();
        return jsonToMap(content);
    }

    public static HashMap<String, String> jsonToMap(String t)  {

        HashMap<String, String> map = new HashMap<String, String>();
        JSONObject jObject = new JSONObject(t);
        Iterator<?> keys = jObject.keys();

        while( keys.hasNext() ){
            String key = (String)keys.next();
            String value = jObject.getString(key);
            map.put(key, value);

        }
        return map;
    }
}
