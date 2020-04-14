package duong.thuy.parking.untils;


import duong.thuy.parking.entities.Credentials;
import duong.thuy.parking.security.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

public class JwtUltis {


    public static Claims verifyToken(HttpServletRequest request) {
        String token = request.getHeader(SecurityConstants.HEADER);
        if (token == null || !token.startsWith(SecurityConstants.PREFIX)) return null;
        // Decode
        return Jwts.parser()
                .setSigningKey(SecurityConstants.SECRET)
                .parseClaimsJws(token.replace(SecurityConstants.PREFIX, ""))
                .getBody();
    }

    public static String generateToken(Credentials user, List<String> roles) {
        Claims claims = Jwts.claims().setSubject(user.getUserName());
        claims.put("roles", roles);
        claims.put("userid",user.getId());
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + SecurityConstants.EXPIRATION);
        // Encode
        String token = Jwts.builder()
                .setClaims(claims)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET)
                .compact();
        return SecurityConstants.PREFIX + token;
    }
}
