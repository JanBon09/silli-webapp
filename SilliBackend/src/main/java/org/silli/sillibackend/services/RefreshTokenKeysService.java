package org.silli.sillibackend.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.logging.Logger;

@Service
@ApplicationScope
public class RefreshTokenKeysService {

    private final KeyPair keyPair;

    public RefreshTokenKeysService() throws NullPointerException{
        String refreshKeyPrivate = System.getenv("REFRESHKEYPRIVATE");
        String refreshKeyPublic = System.getenv("REFRESHKEYPUBLIC");
        keyPair = this.retrieveKeyPair(refreshKeyPrivate, refreshKeyPublic);

        if(keyPair == null){
            throw new NullPointerException("KeyPair is null");
        }
    }

    public KeyPair retrieveKeyPair(String refreshKeyPrivate, String refreshKeyPublic) {


        byte[] encodedRefreshKeyPrivate = Base64.getDecoder().decode(refreshKeyPrivate);
        PKCS8EncodedKeySpec keySpecPrivate = new PKCS8EncodedKeySpec(encodedRefreshKeyPrivate);

        byte[] encodedRefreshKeyPublic = Base64.getDecoder().decode(refreshKeyPublic);
        PKCS8EncodedKeySpec keySpecPublic = new PKCS8EncodedKeySpec(encodedRefreshKeyPublic);
        KeyFactory keyFactory;

        try{
             keyFactory = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e){
            return null;
        }

        PrivateKey privateKey;
        PublicKey publicKey;

        try{
            privateKey = keyFactory.generatePrivate(keySpecPrivate);
            publicKey = keyFactory.generatePublic(keySpecPublic);
        } catch (InvalidKeySpecException e){
            return null;
        }

        return new KeyPair(publicKey, privateKey);
    }

    public KeyPair getKeyPair(){
        return keyPair;
    }

    public PrivateKey getPrivateKey(){
        return keyPair.getPrivate();
    }

    public PublicKey getPublicKey(){
        return keyPair.getPublic();
    }
}
