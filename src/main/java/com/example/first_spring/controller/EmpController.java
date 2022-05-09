package com.example.first_spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
//	위 문제에 아래 로직을 service에 추가
//	job이 manager이고 sal이 2500이상 받는 사원 comm 500으로 업데이트하고 해당 사원 이름, 직업, 커미션 조회
	
	
	// 숙제 0번. 급여 1500을 파라미터로 받고 부서가 10, 30에 속하는 사원 중 급여가 1500을 넘는 사원의 이름 및 급여 조회.
	@GetMapping("/emp/sal/{sal}")
	public List<EmpVO> callNameAndComm(@PathVariable("sal") int sal){
		return empService.getNameAndComm(sal);
	}
	// 숙제 1번. emp에서 사수가 없는 사원 조회
	@GetMapping("/emp/mgr")
	public List<EmpVO> callIfAll(){
		return empService.getIfAll();
	}
	// 숙제 2번. 1987년도를 파리미터로 받고 해당 년도에 입사한 사원 조회
	@GetMapping("/emp/hiredate/year/{year}")
	public List<EmpVO> callYearEmp(@PathVariable("year") String year){
		return empService.getYearEmp(year);
	}
	// 숙제 3번. 12월을 파라미터로 받고 해당 월에 입사한 사원 중 급여가 가장 많은 사원 조회
	@GetMapping("/emp/hiredate/month/{month}")
	public List<EmpVO> callMonthEmp(@PathVariable("month") String month){
		return empService.getMonthEmp(month);
	}
	// 숙제 4번. MANAGER를 파라미터로 받고 job이 MANAGER 중 입사날짜가 가장 빠른 사원의 이름, 입사날짜, 급여 조회
	@GetMapping("/emp/job/{jobName}")
	public List<EmpVO> callFastestHiredateEmp(@PathVariable("jobName") String job){
		return empService.getFastestHiredateEmp(job);
	}
	// 숙제 5번. 사원번호 7782를 파라미터로 받고 해당 사원의 모든 정보(부서번호, 부서이름, 부서위치 포함) 조회
	@GetMapping("/emp/empno/{empno}")
	public List<EmpVO> callAllEmpInfo(@PathVariable("empno") int empno){
		return empService.getAllEmpInfo(empno);
	}
	
	
	// emp테이블에 insert해보기
	// PostMapping : 중요한 정보를 보내거나, 데이터를 보낼 때 post 사용
	// 대표적인 예 : 회원가입
	// @RequestBody가 파라미터로 넘어오는 VO클래스를 대신 new 해줌
	@PostMapping("/emp")
	public int callEmpSet(@RequestBody EmpVO empVO) {
//		System.out.println("사원 이름은 : "+empVO.getEname());
//		System.out.println("사원 번호는 : "+empVO.getEmpno());
//		System.out.println("상사 번호는 : "+empVO.getMgr());
		return empService.setEmp(empVO);
	}
	@DeleteMapping("/emp/empno/{empno}")
	public int callEmpRemove(@PathVariable("empno") int empno) {
		return empService.getEmpRemoveCount(empno);
	}
	@PatchMapping("/emp")
	public int callEmpUpdate(@RequestBody EmpVO empVO) {
		return empService.getEmpUpdateCount(empVO);
	}
}
