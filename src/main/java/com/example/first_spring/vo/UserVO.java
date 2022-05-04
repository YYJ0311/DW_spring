package com.example.first_spring.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data //자동으로 getter,setter 만들어 줌
@AllArgsConstructor //자동으로 생성자 만들어 줌
// 단 모든 변수를 이용한 생성자만 만들어 준다. 특정 변수만 이용한 생성자는 따로 생성해줘야 함
public class UserVO {
	private String name;
	private int age;
	private String addr;
}