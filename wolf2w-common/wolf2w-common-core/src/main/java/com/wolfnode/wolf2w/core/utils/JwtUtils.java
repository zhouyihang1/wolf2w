package com.wolfnode.wolf2w.core.utils;

import com.wolfnode.wolf2w.core.exception.BussinessException;
import io.jsonwebtoken.*;

import java.util.Map;

/**
 * @author zhouyihang
 * @version 1.0
 * @description: TODO
 * @date 2023/9/16 16:47
 */
public class JwtUtils {

    private static final String SECRET_KEY = "idiaindfaoaliieapplcc.";

    public static String getToken(Map<String, Object> payload) {
        return Jwts.builder()
                .addClaims(payload)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public static Claims getClaim(String token) {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
        return claimsJws.getBody();
    }

    public static void verifyToken(String token){
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
        } catch (Exception e) {
            throw new BussinessException("token已失效！");
        }
    }
}
