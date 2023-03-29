package com.dev.backend.security;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dev.backend.model.Person;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtUtil {

  private String secretKey = "secretKeyToGenerateToken";
  private int validateToken = 900000;
  private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

  public String generateUsernameToken(Person person) {
    return Jwts.builder().setSubject(person.getUsername()).setIssuedAt(new Date())
        .setExpiration(new Date(new Date().getTime() +
            validateToken))
        .signWith(SignatureAlgorithm.HS512, secretKey)
        .compact();
  }

  public String getEmailToken(String token) {
    return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
  }

  public boolean validateToken(String token, HttpServletRequest request) {
    try {
      Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
      return true;
    } catch (SignatureException e) {
      logger.error("Invalid signature", e.getMessage());
    } catch (ExpiredJwtException e) {
      logger.error("Expired token", e.getMessage());
      request.setAttribute("tokenValidation", "Expired token");
    } catch (UnsupportedJwtException e) {
      logger.error("Token do not supported", e.getMessage());
    }
    return false;
  }
}
