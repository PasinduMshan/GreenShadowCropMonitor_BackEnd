package lk.ijse.GreenShadowCropMonitor_BackEnd.Service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lk.ijse.GreenShadowCropMonitor_BackEnd.Service.JWTService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JWTServiceImpl implements JWTService {
    @Value("${spring.jwtKey}")
    private String jwtKey;

    // extract Email
    @Override
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    // extract All Claims and return
    public <T> T extractClaim(String token, Function<Claims,T> claimResolve){
        final Claims claims = extractAllClaims(token);
        return claimResolve.apply(claims);
    }
    // extract All the parts in JWT Token
    public Claims extractAllClaims(String token){
        return Jwts
                .parser()
                .setSigningKey(getSigninKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    // get secret key and convert it as Base64 key
    private Key getSigninKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    //check validation of the token
    @Override
    public boolean isTokenValid(String token, UserDetails userDetails ){
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    //check token is Expired
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    //extract token and return Expiration date
    private Date extractExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }

}
