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
	
	// 숙제 0번
	public List<EmpVO> getNameAndComm(int sal){
		return empMapper.selectNameAndComm(sal);
	}
	// 숙제 1번
	public List<EmpVO> getIfAll(){
		return empMapper.selectIfAll();
	}
	// 숙제 2번
	public List<EmpVO> getYearEmp(String year){
		List<EmpVO> list = new ArrayList<EmpVO>();
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
	// 숙제 3번 (수정 필요!!)
	public EmpVO getMonthEmp(String month){
		List<EmpVO> list = new ArrayList<EmpVO>();
		for(EmpVO vo : list) {
			if(vo.getHiredate().split("-")[1].equals(month)) {
				
				return empMapper.selectMonthEmp(month);				
			}
		}
		return null;
	}
}
