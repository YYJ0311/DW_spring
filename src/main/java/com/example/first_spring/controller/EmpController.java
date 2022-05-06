package com.example.first_spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.first_spring.service.EmpService;
import com.example.first_spring.vo.EmpVO;

@RestController
public class EmpController {
	@Autowired
	private EmpService empService;
	@GetMapping("/emp")
	public List<EmpVO> callEmpList(){
		return empService.getAllempList();
	}
	@GetMapping("/emp/no/{empNo}")
	// {}의 의미 : 중괄호 안에 있는 걸 파라미터로 넘기겠다!
	public EmpVO callEmp(@PathVariable("empNo") int empNo) {
		// "" 안의 이름과 {} 안의 이름은 일치해야 함
		return empService.getEmp(empNo);
	}
	
	@GetMapping("/emp/name")
	public List<EmpVO> callEmpNameAndJob(){
		return empService.getEmpNameAndJob();
	}
	@GetMapping("/emp/comm")
	public List<EmpVO> callEnameAndComm(){
		return empService.getEnameAndComm();
	}
	@GetMapping("/emp/hiredate")
	public List<EmpVO> callEnameAndHiredate(){
		return empService.getEnameAndHiredate();
	}
	
	// job이 MANAGER, sal이 2000 이상인 사원 이름 조회 : 비즈니스 계층
	// 만약 job이 SALESMAN 이라면 return null; : 퍼시스턴스 계층
	@GetMapping("/emp/job/{job}/sal/{sal}")
	public List<EmpVO> callEmpName(@PathVariable("job") String job, @PathVariable("sal") int sal) {
		return empService.getEmpName(job, sal);
	}
	
	// 숙제 0번
	@GetMapping("/emp/sal/{sal}")
	public List<EmpVO> callNameAndComm(@PathVariable("sal") int sal){
		return empService.getNameAndComm(sal);
	}
	// 숙제 1번
	@GetMapping("/emp/mgr")
	public List<EmpVO> callIfAll(){
		return empService.getIfAll();
	}
	// 숙제 2번
	@GetMapping("/emp/hiredate/year/{year}")
	public List<EmpVO> callYearEmp(@PathVariable("year") String year){
		return empService.getYearEmp(year);
	}
	// 숙제 3번
	@GetMapping("/emp/hiredate/month/{month}")
	public EmpVO callMonthEmp(@PathVariable("month") String month){
		return empService.getMonthEmp(month);
	}
}
