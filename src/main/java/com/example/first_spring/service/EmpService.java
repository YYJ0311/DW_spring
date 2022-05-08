package com.example.first_spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	public List<EmpVO> getEmpName(String job, int sal) {
		if(job.equals("SALESMAN") || job.equals("salesman")) { // 대소문자 구분함
			return null;
		}
		return empMapper.selectEmpName(job, sal);
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
}
