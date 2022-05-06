package com.example.first_spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.first_spring.vo.DeptVO;
import com.example.first_spring.vo.EmpVO;

@Mapper
public interface EmpMapper {
	// comment : emp테이블 전체사원 조회
	public List<EmpVO> getEmpList();
	public EmpVO getEmp(int empNo);
	
	public List<DeptVO> getDeptList();
	
	// 1번
	public List<EmpVO> selectEmpNameAndJob();
	// 2번
	public List<EmpVO> selectEnameAndComm();
	// 3번
	public List<EmpVO> selectEnameAndHiredate();
	
	public List<EmpVO> selectEmpName(
			@Param("job") String job,
			@Param("sal") int sal);
	
	// 숙제 0. 급여 1500을 파라미터로 받고 부서가 10, 30에 속하는 사원 중 급여가 1500을 넘는 사원의 이름 및 급여 조회.
	public List<EmpVO> selectNameAndComm(int sal);
	// 숙제 1. emp에서 사수가 없는 사원 조회
	public List<EmpVO> selectIfAll();
	// 숙제 2. 1987년도를 파리미터로 받고 해당 년도에 입사한 사원 조회 
	public List<EmpVO> selectYearEmp(String year);
	// 숙제 3. 12월을 파라미터로 받고 해당 월에 입사한 사원 중 급여가 가장 많은 사원 조회
	public EmpVO selectMonthEmp(String month);
}
