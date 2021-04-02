/*
package com.suqiu.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthorizeFilter implements GlobalFilter, Ordered {

    //令牌头名字
    private static final String AUTHORIZE_TOKEN = "Authorization";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        //获取用户令牌信息:头文件中，参数中，cookie中

        String uri = request.getURI().toString();
        if (URLFilter.hasAuthorize(uri)) {
            return chain.filter(exchange);
        }


        //头文件中
        String token = request.getHeaders().getFirst(AUTHORIZE_TOKEN);
        boolean hasToken = true;

        //参数中
        if (StringUtils.isEmpty(token)) {
            token = request.getQueryParams().getFirst(AUTHORIZE_TOKEN);
            hasToken = false;
        }

        //cookie中
        if (StringUtils.isEmpty(token)) {
            HttpCookie cookie = request.getCookies().getFirst(AUTHORIZE_TOKEN);

            if (cookie != null) {
                token = cookie.getValue();
            }
        }

        //没有令牌，则拦截
        if (StringUtils.isEmpty(token)){
            //设置没有权限的状态码：401
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            //响应空数据
            return response.setComplete();
        } else {
            if(!hasToken) {
                if (!token.startsWith("bearer ") && !token.startsWith("Bearer ")) {
                    token = "bearer " + token;
                    request.mutate().header(AUTHORIZE_TOKEN,token);

                }

            }
            //有效就放行
            return chain.filter(exchange);
        }




    }

    @Override
    public int getOrder() {
        return 0;
    }
}
*/
