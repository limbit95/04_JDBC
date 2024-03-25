package edu.kh.jdbc.main.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static edu.kh.jdbc.common.JDBCTemplate.*;
import edu.kh.jdbc.member.model.dto.Member;

public class MainDAO {

	// JDBC 객체 참조 변수
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;
	
	// 기본 생성자 DAO 객체가 생성될 때 xml 파일 읽어와 Properties 객체 저장
	public MainDAO() {
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("main-sql.xml"));				
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	/** 로그인 DAO
	 * @param conn
	 * @param memberId
	 * @param memberPw
	 * @return
	 */
	public Member login(Connection conn, String memberId, String memberPw) throws Exception{
		Member mem = null;
		
		try {
			String sql = prop.getProperty("login");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPw);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				mem = new Member();
				
				mem.setMemberNo(rs.getInt("MEMBER_NO"));
				mem.setMemberId(memberId);
				mem.setMemberName(rs.getString("MEMBER_NM"));
				mem.setMemberGender(rs.getString("MEMBER_GENDER"));
				mem.setEnrollDate(rs.getString("ENROLL_DT"));
			}
			
		} finally {
			rs.close();
			pstmt.close();
		}
		
		return mem;
	}


	/** 아이디 중복 검사 SQL 수행 DAO
	 * @param conn
	 * @param memberId
	 * @return
	 */
	public int idDuplicationCheck(Connection conn, String memberId) throws Exception{
		int result = 0;
		
		try {
			String sql = prop.getProperty("idDuplicationCheck");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return result;
	}
	
	

	/** 회원가입 SQL 수행 DAO (INSERT)
	 * @param conn
	 * @param mem
	 * @return
	 */
	public int signUp(Connection conn, Member mem) throws Exception{
		int result = 0;
		
		try {
			String sql = prop.getProperty("signUp");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, mem.getMemberId());
			pstmt.setString(2, mem.getMemberPw());
			pstmt.setString(3, mem.getMemberName());
			pstmt.setString(4, mem.getMemberGender());
			
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	
	
}