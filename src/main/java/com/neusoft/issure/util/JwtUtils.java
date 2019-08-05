package com.neusoft.issure.util;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author Ni9ht
 * @date 2019-01-07 11:00
 */
public class JwtUtils {
    // 签名加盐
    private static final String SECRET_KEY = "1b74ac81c943fa71cfa2ed328e4ef6b4fe43545dfgdffw3";

    // 过期时间1小时
    public static final long EXPIRATION = 60*60*24*1000;

    /**
     * 新建token，参数为主体信息map
     * @param payloadMap
     * @return
     */
    public static String generateToken(Map<String,Object> payloadMap){
        Long userId = (Long) payloadMap.get("userId");
        String token = JWT.create().withClaim("userId", userId).sign(Algorithm.HMAC256(SECRET_KEY));
        return token;

    }

    /**
     * 根据token获取主体信息,若为非法token则返回null
     * @param token
     * @return
     */
    public static Claims getClaims(String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        return claims;
    }

    /**
     * 从token中取userId
     * @param request
     */
    public static Long getUserId(HttpServletRequest request){
       String authorization = request.getHeader("Authorization");
       String token = authorization.substring(7);
       System.out.println("------------token:"+token);
       Claims claims = getClaims(token);
       Integer userId =(Integer) claims.get("userId");
       Long id = Long.parseLong(userId.toString());
       return id;
    }

    public static void main(String[] args) {
//        Map<String,Object> payloadMap = new HashMap<>();
//        payloadMap.put("id",1);
//        String token = JwtUtils.generateToken(payloadMap);
//        System.out.println("-------------token:{}"+token);
        Claims claims = JwtUtils.getClaims("eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NTgwNzI4MjMsIm5iZiI6MTU1Nzk4NjQyMywidXNlcklkIjoxMSwiaWF0IjoxNTU3OTg2NDIzfQ.pTRIkT2gz6bl8adHr5NTleaUR9WtD86fVRfmGcuS-tw");
        Integer userId =(Integer) claims.get("userId");
        Long id = Long.parseLong(userId.toString());
        System.out.println("--------------claim:{}"+id);

    }


}
