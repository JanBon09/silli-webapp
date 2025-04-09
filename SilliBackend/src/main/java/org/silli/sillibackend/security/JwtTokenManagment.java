package org.silli.sillibackend.security;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.logging.Logger;

@Component
public class JwtTokenManagment {
    @Value("${secretKey}")
    private String secretKey;

    @Value("${currentEnvironment}")
    private String currentEnvironment;

    public JwtEncoder jwtEncoder(){
        SecretKey secretKeyLocal = new SecretKeySpec(secretKey.getBytes(), "hmacSHA256");
        JWKSource<SecurityContext> source = new ImmutableSecret<SecurityContext>(secretKeyLocal);

        return new NimbusJwtEncoder(source);
    }

    public JwtDecoder jwtDecoder(){
        SecretKey originalKey = new SecretKeySpec(secretKey.getBytes(), "hmacSHA256");

        return NimbusJwtDecoder.withSecretKey(originalKey).build();
    }

    public JwtClaimsSet jwtClaimSet(String username){

        return JwtClaimsSet.builder()
                .subject(username)
                .issuer(currentEnvironment)
                .expiresAt(Instant.now().atZone(ZoneId.of("Europe/Warsaw")).toInstant().plus(5, ChronoUnit.MINUTES))
                .build();
    }

}
