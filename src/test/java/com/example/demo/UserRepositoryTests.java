package com.example.demo;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.demo.dao.UserRepository;
import com.example.demo.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserRepositoryTests {

	@Autowired
	private UserRepository userRepository;
	@Autowired
    private StringRedisTemplate stringRedisTemplate;
    
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Test
	public void test() throws Exception {
		/*Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);        
		String formattedDate = dateFormat.format(date);
		
		userRepository.save(new User("aa1", "aa@126.com", "aa", "aa123456",formattedDate));
		userRepository.save(new User("bb2", "bb@126.com", "bb", "bb123456",formattedDate));
		userRepository.save(new User("cc3", "cc@126.com", "cc", "cc123456",formattedDate));*/

		Assert.assertEquals(3, userRepository.findAll().size());
		//Assert.assertEquals("bb", userRepository.findByUserNameOrEmail("bb", "cc@126.com").getNickName());
		userRepository.delete(userRepository.findByUserName("aa1"));
	}
	
	@Test
	public void testStringredis() {
		stringRedisTemplate.opsForValue().set("aaa", "111");
        Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
	}
	
	
	  @Test
	    public void testObj() throws Exception {
	        User user=new User("aa@126.com", "aa", "aa123456", "aa","123");
	        ValueOperations<String, Object> operations=redisTemplate.opsForValue();
	        operations.set("com.neox", user);
	        operations.set("com.neo.f", user,1,TimeUnit.SECONDS);
	        Thread.sleep(1000);
	        //redisTemplate.delete("com.neo.f");
	        boolean exists=redisTemplate.hasKey("com.neo.f");
	        if(exists){
	        	System.out.println("exists is true");
	        }else{
	        	System.out.println("exists is false");
	        }
	       // Assert.assertEquals("aa", operations.get("com.neo.f").getUserName());
	    }
	  
}