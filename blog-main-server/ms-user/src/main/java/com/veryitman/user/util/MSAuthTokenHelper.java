package com.veryitman.user.util;

import com.veryitman.user.model.MSAuthTokenPropertyConfig;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class MSAuthTokenHelper {
    private static final String CLAIMS_USERID = "userID";

    private MSAuthTokenPropertyConfig authTokenPropertyConfig;

    @Autowired
    public void setAuthTokenPropertyConfig(MSAuthTokenPropertyConfig authTokenPropertyConfig) {
        this.authTokenPropertyConfig = authTokenPropertyConfig;
    }

    public String generateToken(String userID) {
        String token = "";

        long tokenExpireTime = authTokenPropertyConfig.getToken_expire_time();
        String jwtsid = authTokenPropertyConfig.getClaims_jwtsid();
        String subject = authTokenPropertyConfig.getClaims_subject();
        String audience = authTokenPropertyConfig.getClaims_audience();
        String tokenSecret = authTokenPropertyConfig.getToken_secret();

        if (null != userID || userID.length() > 0) {
            Date date = new Date();
            Date expireDate = new Date(System.currentTimeMillis() + tokenExpireTime);

            token = Jwts.builder().setId(jwtsid)
                    .setSubject(subject)
                    .setAudience(audience)
                    .setIssuedAt(date)
                    .setExpiration(expireDate)
                    .claim(CLAIMS_USERID, userID)
                    .signWith(SignatureAlgorithm.HS256, tokenSecret)
                    .compact();

            log.info("generateToken token: " + token);
        }

        return token;
    }

    public Claims parseToken(String token) {
        if (null == token) {
            token = "";
        }

        String tokenSecret = authTokenPropertyConfig.getToken_secret();
        JwtParser jwtParser = Jwts.parser().setSigningKey(tokenSecret);
        Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);
        Claims claims = claimsJws.getBody();

        log.info("id:" + claims.getId() + ", audience: " + claims.getAudience());
        log.info("claims.get(userID): " + claims.get(CLAIMS_USERID));
        log.info("subject:" + claims.getSubject());
        log.info("IssuedAt:" + claims.getIssuedAt());

        return claims;
    }

    public String userIDfromToken(String token) {
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

    public String refreshToken(String token) {
        String refreshedToken = "";

        if (null == token || token.length() <= 0) {
            return refreshedToken;
        }

        Date a = new Date();
        long tokenExpireTime = authTokenPropertyConfig.getToken_expire_time();
        Date expireDate = new Date(System.currentTimeMillis() + tokenExpireTime);
        try {
            final Claims claims = parseToken(token);
            String tokenSecret = authTokenPropertyConfig.getToken_secret();
            claims.setIssuedAt(a);
            refreshedToken = Jwts.builder().setClaims(claims)
                    .setIssuedAt(a)
                    .setExpiration(expireDate)
                    .signWith(SignatureAlgorithm.HS256, tokenSecret)
                    .compact();
        } catch (Exception e) {
            log.error("Could not generate Refresh Token from passed token");
        }
        return refreshedToken;
    }

    public boolean verifyToken(String token) {
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
}
