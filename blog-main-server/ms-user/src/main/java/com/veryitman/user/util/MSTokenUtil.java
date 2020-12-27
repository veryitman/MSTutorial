package com.veryitman.user.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class MSTokenUtil {
    private static final String SUBJECT = "admin";
    private static final long TOKEN_EXPIRE_TIME = 24 * 60 * 60 * 1000; //token 过期时间24小时
    private static final String TOKEN_SECRET = "token123";  //密钥盐

    public static String generateToken(String userName) {
        String token = "";

        Date date = new Date();
        Date expireDate = new Date(System.currentTimeMillis() + TOKEN_EXPIRE_TIME);

        Jwts.builder().setSubject(SUBJECT)
                .setIssuedAt(date)
                .setExpiration(expireDate)
                .claim("userName", userName)
                .signWith(SignatureAlgorithm.HS256, TOKEN_SECRET)
                .compact();

        return token;
    }

    public static boolean verifyToken(String token) {
        if (null == token || token.length() <= 0) {
            return false;
        }

        // 是否过期

        // 是否和服务器的一致

        return true;
    }
}
