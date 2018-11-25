package cn.jarod.mjollnir.core.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.Map;

public class JavaWebTokenUtil {

    //该方法使用HS256算法和Secret:bankgl生成signKey
    private static Key getKeyInstance() {
        //We will sign our JavaWebToken with our ApiKey secret
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("token");
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        return signingKey;
    }

    //使用HS256签名算法和生成的signingKey最终的Token,claims中是有效载荷
    public static String createJavaWebToken(Map<String, Object> claims) {
        return   Jwts.builder()
                .setIssuer("Jersey-Security-Basic")//设置发行人
                .setSubject("subject")//设置抽象主题
                .setAudience("login")//设置角色
                .setExpiration(getDate())//过期时间
                .setIssuedAt(new Date())//设置现在时间
                .setClaims(claims)
                .signWith( SignatureAlgorithm.HS256,getKeyInstance())
                .compact();
    }

    //解析Token，同时也能验证Token，当验证失败返回null
    public static Map<String, Object> parserJavaWebToken(String jwt) {
        try {
            Map<String, Object> jwtClaims =
                    Jwts.parser().setSigningKey(getKeyInstance()).parseClaimsJws(jwt).getBody();
            return jwtClaims;
        } catch (Exception e) {
            return null;
        }
    }

    public static Date getDate(){
        long currentTime = System.currentTimeMillis() ;
        currentTime +=30*60*1000*2;
        Date date = new Date(currentTime);
        return date;

    }
}