package org.zerock.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.Member;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/model/*")
@Log4j
public class ModelController {

	@RequestMapping("/ex1")
	public void method1(Model model) {
		log.info("method1");
		
		//request.setAttribute("abc", abc);
		model.addAttribute("name", "java");
	}
	
	@RequestMapping("/ex2")
	public void method2(@ModelAttribute String name/* , Model model */) {
		log.info("method2");
//		model.addAttribute("name", name);
		//어노테이션 @ModelAttribute를 사용하여 간단하게 사용 가능함.
	}
	
	@RequestMapping("/ex3")
	public void method3(@ModelAttribute("name") String name,
			@ModelAttribute("age") int age) {
		log.info("method3");
		log.info(name);
		log.info(age);
	}
	
	@RequestMapping("/ex4")
	public void method4(@ModelAttribute("member") Member member) {
		log.info("method4");
		log.info(member);
		
//		model.addAttribute("member", member);
		//@MovelAttribute 어노테이션을 붙여주면 생략 가능
		
		//보통 타입명, 변수명, 어트리뷰트명이 일치
	}
	
	@RequestMapping("/ex5")
	public void method5(@ModelAttribute Member member) {
		//보통 타입명, 변수명, 어트리뷰트명이 일치함으로
		//model "member"어트리뷰트 명은 생략 가능. 소문자로 바꾼 타입명을 따라감
		log.info("method5");
		log.info(member);
	}
	
	@RequestMapping("/ex6")
	//@ModelAttribute 생략해도 가능
	public String method6(Member member) {
		log.info("method6");
		log.info(member);
		
		return "model/ex4";

		//String.io 메뉴얼에보면,
		//Bean어쩌구저쩌구가 아니면 생략해도 기본적으로 @ModelAttribute  
		//[String.io 내에서] Any other argument
		//determined by BeanUtils#isSimpleProperty, or as an @ModelAttribute otherwise.
	}
	
	@RequestMapping("/ex7")
	public String method7(Model model, HttpSession session, RedirectAttributes rattr) {
		log.info("method7");
		model.addAttribute("myattr1", "myvalue1");
		//처음 요청에 생성되었다가 redirect의 두번째 요청이 진행되면서 사라짐
		session.setAttribute("myAttr2", "myvalue2");
		//redirect요청에 session은 함께 이동
		rattr.addFlashAttribute("myAttr3", "myValue3");
		//redirect되면서 myAttr3은 ex8의 model로 들어감
		
		return "redirect:ex8";
	}
	
	@RequestMapping("/ex8")
	public String method8(Model model) {
		log.info("method8");
		
		Map<String, Object> map = model.asMap();
		log.info(map.get("myattr1"));
		log.info(map.get("myAttr2"));
		log.info(map.get("myAttr3"));

		return "redirectex1";
	}
}
