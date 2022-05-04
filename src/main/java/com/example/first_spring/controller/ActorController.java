package com.example.first_spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.first_spring.service.MainService;
import com.example.first_spring.vo.UserVO;

@RestController
public class ActorController {
	@Autowired
	private MainService service;
	//문제 1. 나이가 60 이하인 배우만 리턴하시오.
	@GetMapping("/actor/list")
	public List<UserVO> callUserList(HttpServletRequest request){
		String ip = request.getHeader("X-forwarded-For");
		if(ip == null) ip = request.getRemoteAddr();
		System.out.println("IP ===>"+ip);
		// 사이트에 접속하는 ip를 수집할 수 있다.
		return service.getUserList();
	}
	//문제 2. 나이가 가장많은 배우의 나이를 리턴하시오.
	@GetMapping("/actor/age")
	public int callUserAgeMax(){
		return service.getUserAgeMax();
	}
	//문제 3. list에 배우이름 배성우가 있다면 리턴하시오.
	@GetMapping("/actor")
	public UserVO callUser(){
		return service.getUser();
	}
	//문제 4. list에 성이 이씨인 배우가 몇명인지 리턴하시오.
	@GetMapping("/actor/count")
	public int callUserCount(){
		return service.getUserCount();
	}
}