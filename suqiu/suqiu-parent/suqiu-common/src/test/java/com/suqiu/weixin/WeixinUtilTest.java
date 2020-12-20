package com.suqiu.weixin;

import com.github.wxpay.sdk.WXPayUtil;
import org.junit.Test;

public class WeixinUtilTest {

    @Test
    public void testWeixin() {
        String s = WXPayUtil.generateNonceStr();
        System.out.println("随机字符串："+s);
    }
}
