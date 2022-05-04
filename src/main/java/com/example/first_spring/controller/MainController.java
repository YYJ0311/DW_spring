package com.example.first_spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.first_spring.service.MainService;
import com.example.first_spring.vo.UserVO;


class ProductVO{
	private String productName;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
}
@RestController
// 아직 템플릿엔진을 사용하지 않아서 Rest를 붙여줌(Rest API를 의미)
// 템플릿엔진을 사용할 거면 @Controller, 안 쓰면 @RestController 로 사용한다.
// url을 요청받는 곳이야~ 라고 컴퓨터가 인식함
public class MainController {
	
	@Autowired
	private MainService service;
	// @Autowired로 @Service가 붙은 MainService와 연결해줌
	// MainService도 import 필요. MainService를 @Service라고 표시해줬기 때문에 new를 쓰지 않고 불러올 수 있다
	// = IoC(Inversion of Control) 제어의 역전. 내가 제어하는 게 아닌 Spring이 제어함
	
	@GetMapping("/index")
	// "/"라는 url이 들어오면 아래 메소드를 실행할게!
	public String call() {
		String word = "유영준 Hello World!";
		return word;
	}
	@GetMapping("/sum")
	public int callSum() {
		int x = 50;
		int y = 20;
		return x+y;
	}
	@GetMapping("/list")
	public List<ProductVO> getProductList() {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO vo1 = new ProductVO();
		ProductVO vo2 = new ProductVO();
		ProductVO vo3 = new ProductVO();
		vo1.setProductName("콩나물국밥");
		vo2.setProductName("짬뽕");
		vo3.setProductName("핫크리스피버거");
		list.add(vo1);
		list.add(vo2);
		list.add(vo3);
		for(ProductVO vo : list) {
			System.out.println(vo.getProductName());
		}
		return list;
	}
	@GetMapping("/arr")
	public int[] getArray() {
		int arr[] = {1,2,3};
		return arr;
	}
	@GetMapping("/user")
	public UserVO callUser() {
		// controller에서 함수를 만들 때 call을 사용함(회사마다 다르긴 함)
		UserVO vo = new UserVO("d",30,"대전");
		vo.setName("홍길동");
		vo.setAge(20);
		return vo;
	}
	
	@GetMapping("/userList")
	public List<UserVO> callUserList(){
		return service.getUserList();
		// @Autowired로 연결된 MainService의 service를 바로 사용할 수 있다.
	}
	
	// Controller는 url 요청받는게 목적임
}