package com.changgou.base64;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class Base64Test {
    @Test
    public void testEncode() throws UnsupportedEncodingException {
        byte[] encode = Base64.getEncoder().encode("abcdefg".getBytes());
        String encondeStr = new String(encode, "utf-8");
        System.out.println(encondeStr);
    }
}
