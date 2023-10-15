package com.gaseng.jwt.util;

import com.gaseng.global.exception.BaseException;
import com.gaseng.global.exception.GlobalErrorCode;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.Date;

@Slf4j
@Component
public class JwtTokenProvider {
    private final SecretKey secretKey;
    private final long accessTokenValidityInMilliseconds;
    private final long refreshTokenValidityInMilliseconds;

    public JwtTokenProvider(@Value("${jwt.secret}") final String secretKey,
                            @Value("${jwt.access-token-validity}") final long accessTokenValidityInMilliseconds,
                            @Value("${jwt.refresh-token-validity}") final long refreshTokenValidityInMilliseconds) {
        this.secretKey = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        this.accessTokenValidityInMilliseconds = accessTokenValidityInMilliseconds;
        this.refreshTokenValidityInMilliseconds = refreshTokenValidityInMilliseconds;
    }

    public String generateAccessToken(Long memId) {
        return generateToken(memId, accessTokenValidityInMilliseconds);
    }

    public String generateRefreshToken(Long memId) {
        return generateToken(memId, refreshTokenValidityInMilliseconds);
    }

    private String generateToken(Long memId, long validityInMilliseconds) {
        Claims claims = Jwts.claims();
        claims.put("id", memId);

        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime tokenValidity = now.plusSeconds(validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(Date.from(now.toInstant()))
                .setExpiration(Date.from(tokenValidity.toInstant()))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public Long getId(String token) {
        return parseClaims(token)
                .getBody()
                .get("id", Long.class);
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = parseClaims(token);
            Date expiredDate = claims.getBody().getExpiration();
            Date now = new Date();
            return expiredDate.after(now);
        } catch (ExpiredJwtException e) {
            throw BaseException.type(GlobalErrorCode.EXPIRED_TOKEN);
        } catch (SecurityException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException e) {
            throw BaseException.type(GlobalErrorCode.INVALID_JWT);
        }
    }

    private Jws<Claims> parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token);
    }
}