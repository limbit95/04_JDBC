package edu.kh.emp.model.dao;

import java.io.Closeable;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.emp.model.vo.Employee;
import static edu.kh.emp.common.JDBCTemplate.*;

public class EmployeeDAO {

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;
	
	
	public EmployeeDAO() {
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("query.xml"));			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	

	/** 전체 사원 조회
	 * @param conn
	 * @return
	 */
	public List<Employee> selectAll(Connection conn) {

		List<Employee> list = new ArrayList<Employee>();
		
		try {
			String sql = prop.getProperty("select");
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Employee emp = new Employee();
				
				emp.setEmpId(rs.getInt("EMP_ID"));
				emp.setEmpName(rs.getString("EMP_NAME"));
				emp.setEmpNo(rs.getString("EMP_NO"));
				emp.setEmail(rs.getString("EMAIL"));
				emp.setPhone(rs.getString("PHONE"));
				emp.setDepartmentTitle(rs.getString("DEPT_TITLE"));
				emp.setJobName(rs.getString("JOB_NAME"));
				emp.setSalary(rs.getInt("SALARY"));
				
				
				emp.setDeptCode(rs.getString("DEPT_CODE"));
				emp.setJobCode(rs.getString("JOB_CODE"));
				emp.setSalLevel(rs.getString("SAL_LEVEL"));
				emp.setBonus(rs.getDouble("BONUS"));
				emp.setManagerId(rs.getInt("MANAGER_ID"));
				
				list.add(emp);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
		}
		
		return list;
	}


	/** 사원 등록 메서드
	 * @param conn
	 * @param emp
	 * @return
	 */
	public int insert(Connection conn, Employee emp) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("insert");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, emp.getEmpId());
			pstmt.setString(2, emp.getEmpName());
			pstmt.setString(3, emp.getEmpNo());
			pstmt.setString(4, emp.getEmail());
			pstmt.setString(5, emp.getPhone());
			pstmt.setString(6, emp.getDeptCode());
			pstmt.setString(7, emp.getJobCode());
			pstmt.setString(8, emp.getSalLevel());
			pstmt.setInt(9, emp.getSalary());
			pstmt.setDouble(10, emp.getBonus());
			pstmt.setInt(11, emp.getManagerId());
			
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

 
	/** 사원 삭제
	 * @param conn
	 * @param empNo
	 * @return
	 */
	public int delete(Connection conn, int empId) throws Exception{

		int result = 0;
		
		try {
			String sql = prop.getProperty("delete");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, empId);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	
	
}