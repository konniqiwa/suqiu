package com.suqiu.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;

import java.util.Date;

public class JwtTest {

    /**
     * 生成令牌
     */
    @Test
    public void testCreateJwt() {
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setIssuer("itheima");    //颁发者
        jwtBuilder.setIssuedAt(new Date());
        jwtBuilder.setSubject("令牌测试");  //主题信息
        jwtBuilder.signWith(SignatureAlgorithm.HS256,"itcast");
        jwtBuilder.setExpiration(new Date(System.currentTimeMillis() + 30000)); //设置过期时间
        String token = jwtBuilder.compact();


        System.out.println(token);
    }

    /**
     * 令牌解析
     */
    @Test
    public void parseToken(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJpdGhlaW1hIiwiaWF0IjoxNTk1OTIzODk0LCJzdWIiOiLku6TniYzmtYvor5UifQ.eYFZ3Q1rdbuge3BG2QXAYXpG9Xds5uQdDBTy5fAiq5w";
        //解析后的令牌数据
        Claims claims = Jwts.parser().setSigningKey("itcast") //密钥（盐）
                .parseClaimsJws(token)
                .getBody();

        System.out.println(claims);

    }
}
