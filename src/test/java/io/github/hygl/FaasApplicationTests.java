package io.github.hygl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FaasApplicationTests {

	@Autowired
	private StringRedisTemplate template;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testRedis() {
		ValueOperations<String, String> ops = this.template.opsForValue();
		String key = "spring.boot.redis.test";
		if (!this.template.hasKey(key)) {
			ops.set(key, "foo");
		}
		System.out.println("Found key " + key + ", value=" + ops.get(key));
	}
}