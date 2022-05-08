package com.example.first_spring.vo;

import lombok.Data;

@Data
public class EmpVO {
	private int empno;
	private String ename;
	private String job;
	private int mgr;
	private String hiredate;
	private int sal;
	private int comm;
	private int deptno;
	
	private DeptVO deptVO;
//	slqmapper_emp.xml의 <collection property="deptVO" resultMap="DeptVO"/> 에서 property 이름과 변수를 같게 해줘야 함.
}
