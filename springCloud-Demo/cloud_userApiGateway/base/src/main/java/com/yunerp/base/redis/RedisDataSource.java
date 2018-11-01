package com.yunerp.base.redis;

import redis.clients.jedis.ShardedJedis;

/**
 * Created by shican on 2016-10-27.
 */
public abstract class RedisDataSource {
    public abstract ShardedJedis getRedisClient();

    public abstract void returnResource(ShardedJedis shardedJedis);

    public abstract void returnResource(ShardedJedis shardedJedis, boolean broken);
}

/*
    getRedisClient() ： 取得redis的客户端，可以执行命令了。
    returnResource(ShardedJedis shardedJedis) ： 将资源返还给pool
    returnResource(ShardedJedis shardedJedis, boolean broken) : 出现异常后，将资源返还给pool （其实不需要第二个方法）


整个的测试代码

    public static void main(String[] args) {
        ApplicationContext ac =  new ClassPathXmlApplicationContext("classpath:/data-source.xml");
        RedisClientTemplate redisClient = (RedisClientTemplate)ac.getBean("redisClientTemplate");
        redisClient.set("a", "abc");
        System.out.println(redisClient.get("a"));
    }


*/

