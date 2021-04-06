package cc.valuenet.wladmin.jwtT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * @author Zcj
 */
@Slf4j
public class JwtUtils {

    //token密钥
    public static  final String SECRET="toto";
    //token过期时间
    public static final long EXPIRE=1000*60*60*2;

    private static final String TOKEN_PREFIX="TO_";

    private static final String SUBJECT="TOTO";

    public static  String createToken(Long userid) {

    String token=  Jwts.builder().setSubject(SUBJECT).claim("head_img","xxx")
                .claim("id","12345")
                .claim("name","李志")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRE))
                .signWith(SignatureAlgorithm.HS256,SECRET)
                .compact();
    token=TOKEN_PREFIX+token;
    return token;
    }

    public static Claims checkToken(String token){
      Claims claims=  Jwts.parser().setSigningKey(SECRET)
                .parseClaimsJws(token.replace(TOKEN_PREFIX,""))
                .getBody();
      return claims;
    }
}
