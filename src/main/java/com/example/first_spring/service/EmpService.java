package com.example.first_spring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.first_spring.mapper.EmpMapper;
import com.example.first_spring.vo.EmpVO;

@Service
public class EmpService {
	@Autowired
	private EmpMapper empMapper;
	
	public List<EmpVO> getAllempList(){
		return empMapper.getEmpList();
	}
	public EmpVO getEmp(int empNo) {
		return empMapper.getEmp(empNo);
	}
	
	public List<EmpVO> getEmpNameAndJob(){
		return empMapper.selectEmpNameAndJob();
	}
	public List<EmpVO> getEnameAndComm(){
		return empMapper.selectEnameAndComm();
	}
	public List<EmpVO> getEnameAndHiredate(){
		return empMapper.selectEnameAndHiredate();
	}
	
	// 숙제 비즈니스 로직 구현
	// 숙제 0번
	public List<EmpVO> getNameAndComm(int sal){
		return empMapper.selectNameAndComm(sal);
	}
	// 숙제 1번
	public List<EmpVO> getIfAll(){
		return empMapper.selectIfAll();
	}
	// 숙제 2번. 1987년에 입사한 사원 수가 3명 이하면 1981년에 입사한 사원으로 조회하시오. 
	public List<EmpVO> getYearEmp(String year){
		List<EmpVO> list = new ArrayList<EmpVO>();
		list = empMapper.selectYearEmp(year);
		int count = 0;
		for(EmpVO vo : list) {
			if(vo.getHiredate().split("-")[0].equals(year)) {
				count++;
			}
		}
		if(count <= 3) {
			return empMapper.selectYearEmp("1981");
		}
		return empMapper.selectYearEmp(year);
	}
	// 숙제 3번. 급여가 가장 높은 사원 조회
	public List<EmpVO> getMonthEmp(String month){
		List<EmpVO> list = new ArrayList<EmpVO>();
		list = empMapper.selectMonthEmp(month);
		int number = 0;
		// 처음에 첫 비교 숫자(number)를 0번째 급여(list.get(0).getSal();)로 했더니 입력한 month가 db에 존재하지 않을 때 오류가 발생함.
		// 따라서 데이터가 없는 경우, number를 0으로 해서 아무것도 실행하지 않고 데이터가 없는 list를 그대로 반환시켜줌 
		for(int i=0; i<list.size(); i++) {
			if(list.get(i).getSal() > number) {
				number = list.get(i).getSal();
				if(i>0) {
					list.remove(i-1);
					i--;					
				}
			}
			if(list.get(i).getSal() < number) {
				list.remove(i);
				i--;
			}
		}
		return list;
	}
	// 숙제 4번. 입사날짜 빠른 사원 조회 (쿼리 2개필요 OR 쿼리하나로 해결 가능)
	public List<EmpVO> getFastestHiredateEmp(String job){
		List<EmpVO> list = new ArrayList<EmpVO>();
		list = empMapper.selectHiredateEmp(job);
		int date = 99999999; // 날짜 최대값
		for(int i=0; i<list.size(); i++) {
			if(Integer.parseInt(list.get(i).getHiredate().replace("-", "")) < date){
				date = Integer.parseInt(list.get(i).getHiredate().replace("-", ""));
				if(i>0) {
					list.remove(i-1);
					i--;
				}
			}
			if(Integer.parseInt(list.get(i).getHiredate().replace("-", "")) > date) {
				list.remove(i);
				i--;
			}
		}
		return list;
	}
	// 숙제 5번
	public List<EmpVO> getAllEmpInfo(int empno){
		return empMapper.selectAllEmpInfo(empno);
	}
	
	
	// 05.09 수업 내용
	@Transactional(rollbackFor = {Exception.class})
//	아래 메소드에서 에러(Exception)가 나면 이전시점 커밋으로 돌리겠다!
//	update, delete, insert하는 메소드에 꼭 필요하다!
	public int getEmpUpdateCount(EmpVO vo) { // 몇행이 update 되었는지 리턴
		int rows = empMapper.updateEmp(vo);
		
//		UserVO user = null; // null때문에 오류남! new로 불러와야 한다.
//		String name = user.getName();
//		System.out.println(name);
//		==> 오류나지만 위에 있는 update 로직은 실행된다. => @Transactional로 롤백시킴.
		
		return rows;
	}
	
	// 비즈니스 로직 추가하기
	@Transactional(rollbackFor = {Exception.class})
	public List<EmpVO> getEmpName(String job, int sal) {
		if(job.equals("SALESMAN") || job.equals("salesman")) { // 대소문자 구분함
			return null;
		}
//		job이 manager이고 sal이 2500이상 받는 사원 comm 500으로 업데이트하고 해당 사원 이름, 직업, 커미션 조회
		List<EmpVO> list = empMapper.selectEmpName(job, sal);
		int comm = 500;
		int rows = 0;
		for(int i=0; i<list.size(); i++) {
//			추가 요청 ) 커미션을 기존거에 더해달라
//			int empComm = list.get(i).getComm();
//			list.get(i).setComm(empComm+comm);
			list.get(i).setComm(comm);
			EmpVO vo = list.get(i);
			vo.setComm(comm);
			rows += empMapper.updateEmp(vo);
		}
		if(rows > 0) {
			return empMapper.selectEmpName(job, sal);
		}
		return null;
	}
	
//	05.10 수업내용
	// sql 쿼리 추가
	@Transactional(rollbackFor = {Exception.class})
//	public int setEmp(EmpVO vo) {
//		int rows = empMapper.insertEmp(vo); // 몇행이 insert 되었는지 리턴
//		return rows;
//	}
	public int setEmp(EmpVO vo) {
		EmpVO empVO = empMapper.selectDeptNo();
		int deptNo = empVO.getDeptno();		
		vo.setDeptno(deptNo);
		
		int rows = empMapper.insertEmp(vo); // 몇행이 insert 되었는지 리턴
		return rows;
	}
	// 비즈니스 로직 추가
	@Transactional(rollbackFor = {Exception.class})
	public int getEmpRemoveCount(int empno) { // 몇행이 delete 되었는지 리턴
		// 1. 급여가 3000 이상인 사원 조회 쿼리 작성
		// 2. mapper 메소드 작성(리턴타입은 쿼리 결과에 따라)
		List<EmpVO> list = empMapper.AllEmpList();
		for(int i=0; i<list.size(); i++) {
			EmpVO e = list.get(i);
			if(e.getEmpno() == empno & e.getSal() < 2000) {
				return 0;
			}
		}
		int rows = empMapper.deleteEmp(empno);
		return rows;
	}
	public int getEmpCount(String first) {
		return empMapper.selectEmpStartsName(first);
	}
	
//	05.11 수업
//	0번
	@Transactional(rollbackFor = {Exception.class})
	public List<EmpVO> getEmpIsMgrList(String isMgr){
		return empMapper.selectEmpMgr(isMgr);
	}
//	1번(선생님 풀이)
	@Transactional(rollbackFor = {Exception.class})
	public int updateEmpJobSal(EmpVO vo, int empno) {
		vo.setEmpno(empno);
		return empMapper.updateEmpJobSalTeacher(vo);
//		vo에 empno를 넣어줬기 때문에 vo만 리턴함
	}
	
//	2번(선생님 풀이)
	@Transactional(rollbackFor = {Exception.class})
	public int getEmpCommSal(int empno) {
		// COMM이 0이거나 NULL인지 확인
		EmpVO vo = empMapper.selectEmpCommSal(empno);
		int comm = vo.getComm();
		if(comm == 0) {
//			comm이 null인 사람도 0으로 나와서 0만 비교해주면 됨
			int sal = vo.getSal();
			int bonus = 500;
			vo.setSal(sal+bonus);
			return empMapper.updateEmpSal(vo); // 업데이트 로직
		}
		return 0;
	}
	
//	5.12
	public List<Map<String, Object>> getEmpMapList(){
		return empMapper.selectEmpMapList(); 
	}
}
