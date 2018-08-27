import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ResourceLoader;
import redis.clients.jedis.*;

import java.util.HashSet;
import java.util.Set;

public class RedisTest {

	// 单实例连接redis
	@Test public void testJedisSingle() {

		Jedis jedis = new Jedis("192.168.149.129", 6379);
		jedis.set("name", "bar");
		String name = jedis.get("name");
		System.out.println(name);
		jedis.close();

	}

	@Test public void pool() {
		JedisPoolConfig config = new JedisPoolConfig();
		//最大连接数
		config.setMaxTotal(30);
		//最大连接空闲数
		config.setMaxIdle(2);

		JedisPool pool = new JedisPool(config, "192.168.86.129", 6379);
		Jedis jedis = null;

		try {
			jedis = pool.getResource();

			jedis.set("name", "lisi");
			String name = jedis.get("name");
			System.out.println(name);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (jedis != null) {
				//关闭连接
				jedis.close();
			}
		}

	}

	// 连接redis集群
	@Test public void testJedisCluster() {

		JedisPoolConfig config = new JedisPoolConfig();
		// 最大连接数
		config.setMaxTotal(30);
		// 最大连接空闲数
		config.setMaxIdle(2);

		//集群结点
		Set<HostAndPort> jedisClusterNode = new HashSet<HostAndPort>();
		jedisClusterNode.add(new HostAndPort("192.168.149.129", 6380));
		jedisClusterNode.add(new HostAndPort("192.168.149.129", 6381));
		jedisClusterNode.add(new HostAndPort("192.168.149.129", 6382));
		jedisClusterNode.add(new HostAndPort("192.168.149.129", 6383));
		jedisClusterNode.add(new HostAndPort("192.168.149.129", 6384));
		jedisClusterNode.add(new HostAndPort("192.168.149.129", 6385));
		JedisCluster jcd = new JedisCluster(jedisClusterNode, config);

		jcd.set("name", "zhangsan");
		String value = jcd.get("name");
		System.out.println(value);
	}

	@Test public void testSingleWithSpring() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				ResourceLoader.CLASSPATH_URL_PREFIX + "spring/application*.xml");
		JedisPool jedisPool = (JedisPool) applicationContext.getBean("jedisClient");
		Jedis jedis = jedisPool.getResource();
		String a = jedis.get("a");
		System.out.println(a);
	}

	@Test public void testClusterWithSpring() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				ResourceLoader.CLASSPATH_URL_PREFIX + "spring/application*.xml");
		JedisCluster jedisCluster = (JedisCluster) applicationContext.getBean("jedisClient2");
		String a = jedisCluster.get("a");
		jedisCluster.set("name", "xiaoyue");
		System.out.println(a);
	}

}
