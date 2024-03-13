package edu.kh.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import edu.kh.jdbc1.model.vo.EMP;

public class JDBCExample3 {

	public static void main(String[] args) {
		
		// 부서명을 입력받아 같은 부서에 있는 사원의
		// 사원명, 부서명, 급여 조회
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		Scanner sc = new Scanner(System.in);
		
		try {
			
			System.out.print("부서명 입력 : ");
			String input = sc.next();
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String type = "jdbc:oracle:thin:@";
			String ip = "localhost";
			String port = ":1522"; 
			String sid = ":XE";
			String user = "kh_lsh";
			String pw = "kh1234";
			
			conn = DriverManager.getConnection(type + ip + port + sid, user, pw);
			
			stmt = conn.createStatement();
			
			String sql = "SELECT EMP_ID, EMP_NAME, NVL(DEPT_TITLE, '부서없음') AS DEPT_TITLE, SALARY FROM EMPLOYEE "
					   + "LEFT JOIN DEPARTMENT ON(DEPT_CODE = DEPT_ID) "
					   + "WHERE NVL(DEPT_TITLE, '부서없음') = '" + input + "'";
			// Java에서 작성되는 SQL에
			// 문자열 변수 추가할 경우
			// '' (DB 문자열 리터럴) 이 누락되지 않도록 신경써야함
			// 만약 '' 미작성 시 String 값은 컬럼명으로 인식되어
			// 부적합한 식별자 오류가 발생함
			
			rs = stmt.executeQuery(sql);
			
			// 조회 결과(rs)를 List 옮겨담기
			List<EMP> list = new ArrayList<EMP>();
			
			while(rs.next()) {
				// 현재 행에 존재하는 컬럼값 얻어오기
				String empName = rs.getString("EMP_NAME");
				String deptTitle = rs.getString("DEPT_TITLE");
				int salary = rs.getInt("SALARY");
				
				// EMP 객체를 생성하여 컬럼값 담기
				EMP emp = new EMP(empName, deptTitle, salary);
				
				// 생성된 EMP 객체를 list에 추가
				list.add(emp);
			}
			
			// 만약에 list에 추가된 EMP 객체가 없다면 "조회 결과 없음"
			// 있다면 순차적으로 출력
			if(list.isEmpty()) {
				System.out.println("조회 결과 없음");
			} else {
				for(EMP emp : list) {
					System.out.println(emp);
				}
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
	}
}