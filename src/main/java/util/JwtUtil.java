package util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

/**
 * 用于生成和解析JSON Web Token
 */
@Component
public class JwtUtil {
    private static String sercetKey = "lingxiao";
    private static Key key = MacProvider.generateKey();
    private final static long keeptime = 30 * 60 * 1000; // 30分钟后过期

    public static String generateToken(String username) {
        long expired = System.currentTimeMillis() + keeptime;
        String jwt = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(expired))
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
        return jwt;
    }

    private static String generToken(String id, String issuer, String subject) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(sercetKey);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        JwtBuilder builder = Jwts.builder().setId(id).setIssuedAt(now);
        if (subject != null) {
            builder.setSubject(subject);
        }
        if (issuer != null) {
            builder.setIssuer(issuer);
        }
        builder.signWith(signatureAlgorithm, signingKey);

        long expMillis = nowMillis + keeptime;
        Date exp = new Date(expMillis);
        builder.setExpiration(exp);
        return builder.compact();
    }
    public String updateToken(String token) {
        try {
            Claims claims = verifyToken(token);
            String id = claims.getId();
            String subject = claims.getSubject();
            String issuer = claims.getIssuer();
            Date date = claims.getExpiration();
            return generToken(id, issuer, subject);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "0";
    }

    public String updateTokenBase64Code(String token) {
        BASE64Encoder base64Encoder = new BASE64Encoder();
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            token = new String(decoder.decodeBuffer(token), StandardCharsets.UTF_8);
            Claims claims = verifyToken(token);
            String id = claims.getId();
            String subject = claims.getSubject();
            String issuer = claims.getIssuer();
            Date date = claims.getExpiration();
            String newToken = generToken(id, issuer, subject);
            return base64Encoder.encode(newToken.getBytes());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "0";
    }

    private static Claims verifyToken(String token) {
        return Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(sercetKey))
                .parseClaimsJws(token).getBody();
    }

    /**
     * 解析JWT
     */
    public static String parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}