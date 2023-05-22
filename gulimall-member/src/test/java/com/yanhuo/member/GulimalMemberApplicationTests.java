package com.yanhuo.member;

import com.yanhuo.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GulimalMemberApplicationTests {

	@Autowired
	MemberService memberService;
	@Test
	void contextLoads() {
		int count = memberService.count();
		System.out.println(count);
	}

}
