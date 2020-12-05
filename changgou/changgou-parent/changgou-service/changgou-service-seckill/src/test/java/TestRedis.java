import com.changgou.seckill.pojo.SeckillGoods;
import org.junit.Test;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import java.util.List;


public class TestRedis {

    @Autowired
    private RedisTemplate redisTemplate;



    @Test
    public void test() {
        List values = redisTemplate.boundHashOps("SeckillGoods_" + 2020100906).values();
        System.out.println(values);
    }
}
