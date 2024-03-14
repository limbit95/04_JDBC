package edu.kh.emp.model.service;

import java.sql.Connection;
import java.util.List;

import static edu.kh.emp.common.JDBCTemplate.*;
import edu.kh.emp.model.dao.EmployeeDAO;
import edu.kh.emp.model.vo.Employee;

public class EmployeeService {

	private EmployeeDAO empDAO = new EmployeeDAO();
	
	/** 전체 사원 조회 메서드
	 * 
	 */
	public void selectAll() {
		Connection conn = getConnection();
		
		List<Employee> list = null;
		
		list = empDAO.selectAll(conn);
		
		for(Employee emp : list) {
			System.out.println(emp);
		}
		
		close(conn);
	}

	
	
	
	/** 사원 등록 메서드
	 * @param emp
	 * @return
	 */
	public int insert(Employee emp) throws Exception{
		Connection conn = getConnection();
		
		int result = empDAO.insert(conn, emp); 
		
		if(result > 0) {
			System.out.println("commit 됨");
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
}