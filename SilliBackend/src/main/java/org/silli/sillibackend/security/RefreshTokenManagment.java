package org.silli.sillibackend.security;

import jakarta.servlet.http.Cookie;
import org.silli.sillibackend.services.RefreshTokenKeysService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.logging.Logger;

@Component
public class RefreshTokenManagment {
    private final RefreshTokenKeysService refreshTokenKeysService;

    public RefreshTokenManagment(RefreshTokenKeysService refreshTokenKeysService) {
        this.refreshTokenKeysService = refreshTokenKeysService;
    }

    public String signTokenContent(String refreshTokenContent) throws Exception {
        Signature signature = Signature.getInstance("SHA256WithRSA");

        signature.initSign(refreshTokenKeysService.getPrivateKey());

        signature.update(refreshTokenContent.getBytes());

        byte[] signatureBytes = signature.sign();

        return Base64.getEncoder().encodeToString(signatureBytes);
    }

    public Boolean verifyTokenContent(String refreshTokenContent, String signatureContent) throws Exception {
        Signature signature = Signature.getInstance("SHA256WithRSA");
        signature.initVerify(refreshTokenKeysService.getPublicKey());
        signature.update(signatureContent.getBytes());
        return signature.verify(Base64.getDecoder().decode(signatureContent));
    }
}
