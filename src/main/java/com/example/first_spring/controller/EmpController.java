package com.example.first_spring.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
	
//	@GetMapping("/emp/name")
//	public List<EmpVO> callEmpNameAndJob(){
//		return empService.getEmpNameAndJob();
//	}
	@GetMapping("/emp/comm")
	public List<EmpVO> callEnameAndComm(){
		return empService.getEnameAndComm();
	}
	@GetMapping("/emp/hiredate")
	public List<EmpVO> callEnameAndHiredate(){
		return empService.getEnameAndHiredate();
	}	
	
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
	
	
//	05.09 수업
	// emp테이블에 insert해보기
	// PostMapping : 중요한 정보를 보내거나, 데이터를 보낼 때 post 사용
	// 대표적인 예 : 회원가입
	// @RequestBody가 파라미터로 넘어오는 VO클래스를 대신 new 해줌
	@PatchMapping("/emp")
	public int callEmpUpdate(@RequestBody EmpVO empVO) {
		return empService.getEmpUpdateCount(empVO);
	}
	
	// job이 MANAGER, sal이 2000 이상인 사원 이름 조회 : 비즈니스 계층
	// 만약 job이 SALESMAN 이라면 return null; : 퍼시스턴스 계층
	@GetMapping("/emp/job/{job}/sal/{sal}")
	public List<EmpVO> callEmpName(@PathVariable("job") String job, @PathVariable("sal") int sal) {
		return empService.getEmpName(job, sal);
	}
//	위 문제에 아래 로직을 service에 추가
//	job이 manager이고 sal이 2500이상 받는 사원 comm 500으로 업데이트하고 해당 사원 이름, 직업, 커미션 조회
	
	
	// 5.10 수업
//	쿼리스트링으로 getmapping
	//tire?region=kr ===> region에 kr이 들어있음
	@GetMapping("/tier")
	public String callTier(@RequestParam("region") String region) {
		return region;
	}
//	포스트맨에 http://localhost:8080/tier?region=kr 보내면 kr 나옴
//	region에 kr을 넣고 region을 출력한 것
//	@GetMapping("/tier")
//	public String callTier(@RequestParam("region") String region, @RequestParam("name") String name) {
//		return region+", "+name;
//	}
//	http://localhost:8080/tier?region=kr&name=youngjoon 보내면 kr, youngjoon 출력됨
	
	
//	게시판 주소 : 몇 페이지인지와 한 페이지에 몇 행을 보여주고 있는지가 와야 함
//	board?page=1&pageSize=10
//	작성자 검색할 경우
//	board?page=1&pageSize=10&writer=유영준
	@GetMapping("/board")
	public int callBoard(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize, @RequestParam("writer") String writer) {
		System.out.println("현재 페이지는 : "+page);
		System.out.println("한 페이지에 보여주는 row 수는 : "+pageSize);
		System.out.println("작성자는 : "+writer);
		return 0;
	}

//	쿼리로 페이지수 정하는 방법 => limit
//	결과를 전부 찾은 다음에 limit으로 자르는 것! => 데이터가 많아지면 limit을 쓰면 안 됨(웬만해선 limit으로 쓰긴 함)
//	==> between을 사용한다.(대신 로직이 길어진다. 따라서 처음엔 데이터가 작기 때문에 limit으로 빠르게 짠 다음, 나중에 데이터가 많아지면 between으로 수정한다.)
//	between은 제이쿼리 작성도 복잡하다.
//	from - where - group by - having - select - order by - limit
	
	
//	아래에 있는 기존 문제에 비즈니스 로직, 퍼시스턴스 쿼리 추가
//	1. dept와 연결된 emp에서 없는 부서번호를 찾아서 사원이 새로 insert 될 때 그 부서번호로 insert되게 만들기
	@PostMapping("/emp")
	public int callEmpSet(@RequestBody EmpVO empVO) {
		return empService.setEmp(empVO);
	}
//	2. 급여가 3000 이상인 사원만 삭제(3000이 안 되는 사원은 return 0)
	@DeleteMapping("/emp/empno/{empno}")
	public int callEmpRemove(@PathVariable("empno") int empno) {
		return empService.getEmpRemoveCount(empno);
	}
//	3. A로 시작하는 사람 수 구하기
	@GetMapping("/emp/name")
	public int callEmpCount(@RequestParam("search") String first) {
		return empService.getEmpCount(first);
	}
	
	
	// 5.11 수업
//	[문제 0. 사수가 있는 사원과 없는 사원 조회]
	@GetMapping("/emp/mgr/{isMgr}")
	public List<EmpVO> callEmpMgrList(@PathVariable("isMgr") String isMgr){
		return empService.getEmpIsMgrList(isMgr);
	}
//	isMgr 자리에 sql쿼리에서 사용한 if로 인해 true 나 false가 온다.
	
//	[문제 1. 사원번호가 7902번인 사원 job을 SALESMAN, sal을 3500으로 수정하시오.]
//	@PatchMapping("/emp/no/{empno}/job/{job}/sal/{sal}")
//	위처럼 풀면 주소가 너무 길어짐. 저렇게 풀면 안 됨.
	@PatchMapping("/emp/no")
	public int callEmpJobSalUpdate(@RequestBody EmpVO vo) {
//		return empService.getEmpJobSalUpdateCount(vo);
		return 0;
	}
//	위 방법은 empno, job, sal 모두 json으로 담아서 수정하는 쿼리이다.
//	위 방법보단 empno를 파라미터로 받고 나머지 job과 sal을 json으로 받아서 수정하는 쿼리를 작성해보자
//	선생님 풀이
//	@PatchMapping("/emp/empno")
//	public int callEmpSalJobUpdate(@RequestBody EmpVO vo) {
//		return
//	} 로 풀어도 됨
	@PatchMapping("/emp/{empno}")
	public int callEmpSalJobUpdate(@PathVariable("empno") int empno, @RequestBody EmpVO vo) {
		return empService.updateEmpJobSal(vo, empno);
	}
//	=> 이렇게 풀면 empno를 파라미터로 받고 body에 json 입력해서 send하면 됨

//	[문제2. 사원번호가 7844번인 사원의 COMM이 0이거나 null이면 기존 급여에서 500을 추가(수정)하시오.]
//	선생님 풀이
//	@PatchMapping("/emp/empno/{empno}/{sal}")
	@PatchMapping("/emp/empno/{empno}")
	public int callEmpSalUpdate(@PathVariable("empno") int empno) {
		return empService.getEmpCommSal(empno);
	}
	
//	5.12
	@GetMapping("/emp/map/list")
	public List<Map<String, Object>> callEmpMapList(){
		return empService.getEmpMapList();
	}
}
