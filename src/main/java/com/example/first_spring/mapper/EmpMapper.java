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
	
	// 숙제 0
	public List<EmpVO> selectNameAndComm(int sal);
	// 숙제 1
	public List<EmpVO> selectIfAll();
	// 숙제 2
	public List<EmpVO> selectYearEmp(String year);
	// 숙제 3
	public List<EmpVO> selectMonthEmp(String month);
	// 숙제 4
	public List<EmpVO> selectHiredateEmp(String job);
	// 숙제 5
	public List<EmpVO> selectAllEmpInfo(int empno);
	
	
	public int insertEmp(EmpVO empVO); // 데이터 삽입
	public int deleteEmp(int empno); // 데이터 삭제
	public int updateEmp(EmpVO empVO); // 데이터 수정
}
