package com.neusoft.issure;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Serializer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
    public static final long EXPIRATION = 60*60*24*1000;
    private static final String SECRET_KEY = "1b74ac81c943fa71cfa2ed328e4ef6b4fe43545dfgdffw3";



    @Test
    public void contextLoads() {
    }

    @Test
    public void get(){
        Map<String,Object> payloadMap = new HashMap<>();
        JwtBuilder jwtBuilder = Jwts.builder();
        // 设置过期时间
        jwtBuilder.setExpiration(new Date(System.currentTimeMillis()+EXPIRATION));
        // 当前时间之前的token都为非法token
        jwtBuilder.setNotBefore(new Date(System.currentTimeMillis()));
        // 添加主体信息
        for (String key : payloadMap.keySet()) {
            jwtBuilder.claim(key,payloadMap.get(key));
        }
        //创建时间
        jwtBuilder.setIssuedAt(new Date());
        // 签名及加盐
        jwtBuilder.signWith(SignatureAlgorithm.HS256, SECRET_KEY);
        jwtBuilder.serializeToJsonWith((Serializer<Map<String, ?>>) payloadMap);
        // 生成token
        String accessToken = jwtBuilder.compact();
    }

}
