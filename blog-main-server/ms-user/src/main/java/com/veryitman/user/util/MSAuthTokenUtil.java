package com.veryitman.user.util;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public class MSAuthTokenUtil {
    private static final String JWTSID = "restful_api";
    private static final String SUBJECT = "admin";
    private static final String AUDIENCE = "client";
    private static final long TOKEN_EXPIRE_TIME = 24 * 60 * 60 * 1000; //token 过期时间24小时
    private static final String TOKEN_SECRET = "token_veryitman_blog";  //密钥盐

    private static final String CLAIMS_USERID = "userID";

    public static String generateToken(String userID) {
        String token = "";

        if (null != userID || userID.length() > 0) {
            Date date = new Date();
            Date expireDate = new Date(System.currentTimeMillis() + TOKEN_EXPIRE_TIME);

            token = Jwts.builder().setId(JWTSID)
                    .setSubject(SUBJECT)
                    .setAudience(AUDIENCE)
                    .setIssuedAt(date)
                    .setExpiration(expireDate)
                    .claim(CLAIMS_USERID, userID)
                    .signWith(SignatureAlgorithm.HS256, TOKEN_SECRET)
                    .compact();

            log.info("generateToken token: " + token);
        }

        return token;
    }

    public static Claims parseToken(String token) {
        if (null == token) {
            token = "";
        }
        JwtParser jwtParser = Jwts.parser().setSigningKey(TOKEN_SECRET);
        Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);
        Claims claims = claimsJws.getBody();

        log.info("id:" + claims.getId() + ", audience: " + claims.getAudience());
        log.info("claims.get(userID): " + claims.get(CLAIMS_USERID));
        log.info("subject:" + claims.getSubject());
        log.info("IssuedAt:" + claims.getIssuedAt());

        return claims;
    }

    public static String userIDfromToken(String token) {
        String userID = "";

        if (null != token && token.length() > 0) {
            Object uidObj = parseToken(token).get(CLAIMS_USERID);
            if (null != uidObj) {
                userID = uidObj.toString();
            }
        }

        log.info("UserID from parse token: " + userID);

        return userID;
    }

    public static boolean verifyToken(String token) {
        if (null == token || token.length() <= 0) {
            return false;
        }

        // token 中保存的userID不能为空
        String userIDfromToken = userIDfromToken(token);
        if (null == userIDfromToken || userIDfromToken.length() <= 0) {
            return false;
        }

        Claims claims = parseToken(token);

        // token是否过期
        Date expirationDate = claims.getExpiration();
        Date curDate = new Date();
        if (expirationDate.before(curDate)) {
            log.info("The token expiration");
            return false;
        } else {
            log.info("The token no expiration");
        }

        return true;
    }

    public static String refreshToken(String token) {
        String refreshedToken = "";

        if (null == token || token.length() <= 0) {
            return refreshedToken;
        }

        Date a = new Date();
        Date expireDate = new Date(System.currentTimeMillis() + TOKEN_EXPIRE_TIME);
        try {
            final Claims claims = parseToken(token);
            claims.setIssuedAt(a);
            refreshedToken = Jwts.builder().setClaims(claims)
                    .setIssuedAt(a)
                    .setExpiration(expireDate)
                    .signWith(SignatureAlgorithm.HS256, TOKEN_SECRET)
                    .compact();
        } catch (Exception e) {
            log.error("Could not generate Refresh Token from passed token");
        }
        return refreshedToken;
    }
}
