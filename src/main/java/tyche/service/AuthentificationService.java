/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tyche.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import tyche.config.TokenKey;
import tyche.dao.LoginDao;
import tyche.exception.NotAdminException;
import tyche.model.Login;

import java.security.Key;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author michael
 */
@Service("authentificationService")
public class AuthentificationService {

    @Autowired
    LoginDao loginDao;
    
    public void checkAdminToken(Claims tokenDecrypte) throws NotAdminException {
        boolean admin = (boolean) tokenDecrypte.get("admin");
        if (!admin) {
            throw new NotAdminException();
        }
    }

    public void checkAdminDansHeader(String authorizationHeader) throws NotAdminException {
        Claims claims = verifierBearerTokenDansHeader(authorizationHeader);
        boolean admin = (boolean) claims.get("admin");
        if (!admin) {
            throw new NotAdminException();
        }
    }

    public Login findLogin(String username, String password) {
        return loginDao.findLogin(username, password);
    }

    public String genererJwt(String sujet, long dureeToken) {

        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(TokenKey.SERVER_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder()
                .setIssuedAt(now)
                .setSubject(sujet).signWith(signingKey, signatureAlgorithm);

//        if it has been specified, let's add the expiration
        if (dureeToken >= 0) {
            long expMillis = nowMillis + dureeToken;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        return builder.compact();
    }

    public Claims verifierBearerTokenDansHeader(String authorizationHeader) {
        String token = authorizationHeader.substring("Bearer".length() + 1);
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(TokenKey.SERVER_KEY))
                .parseClaimsJws(token).getBody();

        return claims;
    }

    public Claims verifierBearerToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(TokenKey.SERVER_KEY))
                .parseClaimsJws(token).getBody();

        return claims;
    }
}
