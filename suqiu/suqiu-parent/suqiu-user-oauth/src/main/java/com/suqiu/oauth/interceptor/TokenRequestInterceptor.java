package com.suqiu.oauth.interceptor;

import com.suqiu.oauth.util.AdminToken;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TokenRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {

        String token = AdminToken.adminToken();

        requestTemplate.header("Authorization","bearer " + token);

    }
}
