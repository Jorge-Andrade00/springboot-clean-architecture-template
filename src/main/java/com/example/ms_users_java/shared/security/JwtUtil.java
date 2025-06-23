package com.example.ms_users_java.shared.security;

import com.example.ms_users_java.shared.exception.AppException;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    @Value("${SPRING_JWT_SECRET}")
    private String jwtSecret;

    public String generateToken(String email) {
        try {
            // Create payload object
            Map<String, Object> userData = new HashMap<>();

            userData.put("email", email);
            userData.put("roles", "USER"); // Dummy role for example

            // Set token payload
            JWTClaimsSet payload = new JWTClaimsSet.Builder()
                    .subject(email)
                    .issuer("ms_users_java") // TODO: Get from application properties or environment
                    .expirationTime(
                            new java.util.Date(System.currentTimeMillis() + 3600000) // 1 hour expiration
                    )
                    .claim("data", userData)
                    .build();

            // Set token header
            JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);

            // Create the JWT token
            SignedJWT token = new SignedJWT(header, payload);

            // Sign the token with the secret key
            JWSSigner signer = new MACSigner(jwtSecret);

            // Sign the JWT
            token.sign(signer);

            // Return the serialized token
            return token.serialize();
        } catch (JOSEException e) {
            throw new AppException("Error generating JWT token", e);
        }
    }
}
