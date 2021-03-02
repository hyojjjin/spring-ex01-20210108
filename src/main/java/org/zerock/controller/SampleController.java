package org.zerock.controller;

import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.SampleDTOList;
import org.zerock.domain.TodoDTO;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/sample/*")
//뒤에 *이 붙은 거랑
//아래 어노테이션 괄호 안의 값이 같으면! 아래가 일을 시작
@Log4j
public class SampleController {
	
	@RequestMapping("")
	public void basic() {
		log.info("basic...");
	}

	@GetMapping("/ex02")
	public String ex02(@RequestParam("name") String name,
			@RequestParam("age") int age) {
	
		log.info("sample ex02");
		log.info(name);
		log.info(age);
		
		return "ex02";
	}
	
	//133page
	@GetMapping("/ex02Array")
	public String ex02Array(@RequestParam("ids") String[] ids) {
		
		log.info("array ids: "+ Arrays.deepToString(ids));
		return "ex02Array";
	}
	
	//130쪽
	@GetMapping("/ex01")
	public String ex01(SampleDTO dto) {
		log.info("dto :" + dto);
		return "ex01";
	}
	
	//134쪽
	// http://localhost:8080/sample/ex02Bean?list[0].name=aaa&list[1].name=bbb
		// http://localhost:8080/sample/ex02Bean?list%5B0%5D.name=aaa&list%5B1%5D.name=bbb
	@GetMapping("/ex02Bean")
	public String ex02Bean(SampleDTOList list) {
		log.info("list dtos: " + list);
		return "ex02Bean";
	}
	
	//137쪽
//	@InitBinder
//	public void initBinder(WebDataBinder binder) {
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
//		binder.registerCustomEditor(java.util.Date.class, 
//				new CustomDateEditor(dateFormat, false));
//	}
//	
	@GetMapping("/ex03")
	// /sample/ex03?title=test&dueDate=2018-01-01
	//initBider의 주석도 풀고 TotoDTO의   @DateTimeFormat(pattern = "yyyy/mm/dd")을 지워줘야함
	
	// /sample/ex03?title=test&dueDate=2018/01/01
	public String ex03(TodoDTO todo) {
		log.info("todo: " + todo);
		return "ex03";
	
	}
	
	@GetMapping("/ex04")
	public String ex04(SampleDTO dto, @ModelAttribute("page") int page) {
	//@ModelAttribute는 bean에서는 생략이 가능하지만, int, String double 등 생략안됨
		
		log.info("dto: " + dto);
		log.info("page: " + page);
		
		return "/sample/ex04";
	}
	
	@GetMapping("/ex05")
	public void ex05() {
		log.info("/ex05.....");
	//리턴타입이 void인 경우 url 경로가 됨
	}
	
	
	
	@GetMapping("/ex06")
	public @ResponseBody SampleDTO ex06() {
		log.info("/ex06..........");
		
		SampleDTO dto = new SampleDTO();
		dto.setAge(10);
		dto.setName("홍길동");
	
		return dto;
	}
	
	
}
