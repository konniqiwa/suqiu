package com.suqiu.token;

import org.junit.Test;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;

/*****
 * @Author: www.itheima
 * @Date: 2019/7/7 13:48
 * @Description: com.changgou.token
 *  使用公钥解密令牌数据
 ****/
public class ParseJwtTest {

    /***
     * 校验令牌
     */
    @Test
    public void testParseToken(){
        //令牌
        String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzY29wZSI6WyJhcHAiXSwibmFtZSI6bnVsbCwiaWQiOm51bGwsImV4cCI6MjAzMzczMzQwMiwiYXV0aG9yaXRpZXMiOlsic2Vja2lsbF9saXN0IiwiZ29vZHNfbGlzdCJdLCJqdGkiOiIzNjk3YWRlMC1hNDJlLTQ4ZmUtOWJjYy0xZWUzYWI3NjE1MjQiLCJjbGllbnRfaWQiOiJjaGFuZ2dvdSIsInVzZXJuYW1lIjoic3ppdGhlaW1hIn0.VfIXiHOk5Tz3G7j2Wh_fDwWuThxTfqlGUpMF8L4vG3BWvbmuLgblkEw0W37-Q6cx85bT1SoaLLeScGJfJkIQfWqsPqspyNvFQcqgMYV2vAhmuFUHoVMaExbsf4J7R3CdZFBWQ3RlDIA492B9fiTYfg2l4SzPGx1k-h-N0BPO45yLyON3evnrbxDPorY03Peu8hfkPMx8_AfL9ImYfMbped4X9XqPcJftZI0hzlDjXpMVulK7RD8vIneEFNDVGM76VDic_MPN75b66l7DxofrbJ5lNpvtq7hRWFdHqKeIJurmTFbnSQsg2CQNdHT4tjmkzM_MJnp1WD7dTcf6_jnGnw";
        //公钥
        String publickey = "-----BEGIN PUBLIC KEY-----MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAi7Drp7TubteIxAM71vQfH1trXsobxVrCAdONO3Moh6e+St0pP1IcLXBS5QtwF3dCIeCp9h9Tug0WZ3NRPJxBOl+h23nKgfnBpbqjQRa4/pZty4T4R9pqeVQtXpyUD1SyDCfy8hqVbd5wX+3l8+zHgKf3DmpEvfRxh0eRXcRV/5luU6T7Cu+7fu0eTbQpKT7gwDFRNRwhDIe+1uLgzmn/9ZpwtM7f3aumN97wFltsTMFlVFCr/3UDJXRt8opm2Qm3Z+vDA4x7qFgW5dVmXU3nCp7pjBK1zRMDnemRjiizo3Ha1mR9SJBA6zYgt1ZV71kndOjn5pPnq9f3RIZIAMgDyQIDAQAB-----END PUBLIC KEY-----";

        //校验Jwt
        Jwt jwt = JwtHelper.decodeAndVerify(token, new RsaVerifier(publickey));

        //获取Jwt原始内容 载荷
        String claims = jwt.getClaims();
        System.out.println(claims);
        //jwt令牌
        String encoded = jwt.getEncoded();
        System.out.println(encoded);
    }
}
