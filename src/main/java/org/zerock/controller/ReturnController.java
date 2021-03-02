package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zerock.domain.Member;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/return/*")
@Log4j
public class ReturnController {

	@GetMapping("/ex1")
	public String method1() {
		log.info("method2");
		return "returnView1";
	}
	
	@RequestMapping("/ex2")
	public String method2() {
		log.info("method2");
		
		// 일을 마치면
		// forward or redirect
		return "redirect:/sample/";
	//contextPath 없이도 연결 가능!
	}
	
	@RequestMapping("/ex3")
	public @ResponseBody String method3() {
		log.info("method3");
		
		return "returnValues3 hello world!!";
				//텍스트 값이 응답 자체가 됨
	}
	
	@RequestMapping("/ex4")
	public void method4() {
		log.info("method4");
		//void일 경우에는 요청 경로가 jsp가 됨. 바로 이동함.
	}
	
	@RequestMapping("/ex5")
	public @ResponseBody Member method5() {
		log.info("method5");
		Member member = new Member();
		member.setName("donald");
		member.setAge(33);
		
		//json 이렇게 생김 {"name":"donald", "age":33}
		
		return member;
		
	}
	
}
